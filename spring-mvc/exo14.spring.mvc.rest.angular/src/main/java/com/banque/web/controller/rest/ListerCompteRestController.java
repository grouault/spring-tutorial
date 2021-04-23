package com.banque.web.controller.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banque.entity.ICompteEntity;
import com.banque.service.ICompteService;
import com.banque.service.ex.FonctionnelleException;
import com.banque.web.controller.rest.ex.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Gestion du listage des comptes en rest. URL : /rest/comptes/lister/userId
 */
@RestController
@RequestMapping("/comptes")
public class ListerCompteRestController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private ICompteService compteService;

	/**
	 * Liste les comptes de l'utilisateur authentifie. <br>
	 * En get : /rest/comptes/ <br/>
	 *
	 * @param session
	 *            la session http
	 * @return un json object compose de la taille de la liste et d'une liste de
	 *         json objet, chacun etant un compte
	 */
	@RequestMapping(method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> listerCompte(HttpSession session) {

		// ---
		// IMPORTANT : On ne peut pas faire de GET avec un RequestBody (on ne
		// s'en sert pas ici donc tout est ok)
		// ---
		ResponseEntity<String> resu;

		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			resu = new ResponseEntity<String>(new JSONException("Vous devez vous authentifier").toJsonString(),
					HttpStatus.UNAUTHORIZED);
		} else {
			ListerCompteRestController.LOG.info("listage des comptes RS ({})", userId);
			try {
				List<ICompteEntity> comptes = this.compteService.selectAll(userId.intValue());
				ListerCompteRestController.LOG.debug("listage des comptes - trouve {} nb comptes pour {}",
						Integer.valueOf(comptes.size()), userId);
				JSONObject resultat = new JSONObject();
				JSONArray array = new JSONArray();
				for (ICompteEntity compte : comptes) {
					JSONObject obj = new JSONObject();
					obj.put("id", compte.getId());
					obj.put("libelle", compte.getLibelle());
					obj.put("solde", compte.getSolde());
					if (compte.getTaux() != null) {
						obj.put("taux", compte.getTaux());
					}
					if (compte.getDecouvert() != null) {
						obj.put("seuil", compte.getDecouvert());
					}
					array.add(obj);
				}
				resultat.put("taille", Integer.valueOf(array.size()));
				resultat.put("liste", array);
				resu = new ResponseEntity<String>(resultat.toString(), HttpStatus.OK);
				ListerCompteRestController.LOG.info("listage des comptes RS ({}) est un succes", userId);
			} catch (FonctionnelleException exp) {
				resu = new ResponseEntity<String>(
						new JSONException("Vous n'avez pas le droit de visualiter les comptes", exp).toJsonString(),
						HttpStatus.FORBIDDEN);
			} catch (Exception exp) {
				resu = new ResponseEntity<String>(new JSONException("Une erreur est survenue", exp).toJsonString(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return resu;
	}
}
