package com.banque.web.controller.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banque.entity.ICompteEntity;
import com.banque.service.ICompteService;
import com.banque.service.ex.FonctionnelleException;
import com.banque.web.controller.rest.ex.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Gestion du listage des comptes en rest. URL : /rest/comptes/lister?userId=xxx
 */
@RestController
@RequestMapping("/comptes")
public class ListerCompteRestController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private ICompteService compteService;

	/**
	 * Liste les comptes de l'utilisateur. <br>
	 * En put/pots/get : /rest/comptes/lister?userId=xxx <br/>
	 * Important, on ne gere pas ici la problematique d'authentification.
	 *
	 * @param userId
	 *            le user id
	 * @return un json object compose de la taille de la liste et d'une liste de
	 *         json objet, chacun etant un compte
	 */
	@RequestMapping(value = "/lister", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> listerCompte(@RequestParam(value = "userId", required = true) String userId) {
		ListerCompteRestController.LOG.info("listage des comptes RS ({})", userId);
		ResponseEntity<String> resu = null;
		// Spring s'assure que la valeur n'est pas nulle, mais elle
		// peut etre vide
		if (userId.trim().isEmpty()) {
			resu = new ResponseEntity<String>(new JSONException("Usage is /lister?userId=Xxxx").toJsonString(),
					HttpStatus.BAD_REQUEST);
		} else {
			int userIdInt = -1;
			try {
				userIdInt = Integer.parseInt(userId);
			} catch (Exception e) {
				// Ou pas un chiffre
				resu = new ResponseEntity<String>(
						new JSONException("Votre id n'est pas un chiffre valable.", e).toJsonString(),
						HttpStatus.BAD_REQUEST);
			}
			// On verifie que le tout est ok
			if (userIdInt > 0) {
				try {
					List<ICompteEntity> comptes = this.compteService.selectAll(userIdInt);
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
				} catch (FonctionnelleException exp) {
					resu = new ResponseEntity<String>(
							new JSONException("Vous n'avez pas le droit de visualiter les comptes", exp).toJsonString(),
							HttpStatus.FORBIDDEN);
				} catch (Exception exp) {
					resu = new ResponseEntity<String>(new JSONException("Une erreur est survenue", exp).toJsonString(),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return resu;
	}
}
