package com.banque.web.controller.rest;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banque.entity.IOperationEntity;
import com.banque.service.IOperationService;
import com.banque.service.ex.FonctionnelleException;
import com.banque.web.controller.rest.ex.JSONException;
import com.banque.web.model.VirementBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Realiser un virement entre deux comptes. <br/>
 * URL en put uniquement : /rest/virer/userId?compteSrc=xxx&compteDest=Yyyy
 * &montant=Zzzz <br/>
 */
@RestController
@RequestMapping("/virer")
public class VirerRestController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private IOperationService operationService;

	/**
	 * Constructeur de l'objet.
	 */
	public VirerRestController() {
		super();
	}

	/**
	 * Realiser un virement entre deux comptes. <br>
	 * En put uniquement : /rest/virer <br/>
	 *
	 * @param virement
	 *            l'objet de virement en json
	 * @param session
	 *            la session
	 *
	 * @return un json object compose de la taille de la liste, de l'id du
	 *         compte source et destination et d'une liste de json objet
	 *         operation (2 si tout va bien), chacun etant une operation
	 */
	@RequestMapping(method = {
			RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> virerPut(@RequestBody VirementBean virement, HttpSession session) {
		ResponseEntity<String> resu = new ResponseEntity<String>(
				new JSONException("Mauvais usage du service").toJsonString(), HttpStatus.INTERNAL_SERVER_ERROR);

		// ---
		// IMPORTANT : On ne peut pas faire de GET avec un RequestBody
		// ---

		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			resu = new ResponseEntity<String>(new JSONException("Vous devez vous authentifier").toJsonString(),
					HttpStatus.UNAUTHORIZED);
		} else {
			VirerRestController.LOG.info("virer RS (userId={}, compteSrc={}, compteDest={}, montant={})", userId,
					virement.getCptSrcId(), virement.getCptDstId(), virement.getMontant());

			// On verifie que le tout est ok
			if (virement.getCptSrcIdAsInt() != null && virement.getCptDstIdAsInt() != null
					&& virement.getMontantAsDouble() != null && userId.intValue() > 0
					&& virement.getCptSrcIdAsInt().intValue() > 0 && virement.getCptDstIdAsInt().intValue() > 0
					&& virement.getMontantAsDouble().intValue() > 0
					&& virement.getCptSrcIdAsInt().intValue() != virement.getCptDstIdAsInt().intValue()) {
				try {

					List<IOperationEntity> operations = this.operationService.faireVirement(userId.intValue(),
							virement.getCptSrcIdAsInt().intValue(), virement.getCptDstIdAsInt().intValue(),
							virement.getMontantAsDouble().doubleValue());
					JSONObject resultat = new JSONObject();
					JSONArray array = new JSONArray();
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					for (IOperationEntity operation : operations) {
						JSONObject obj = new JSONObject();
						obj.put("id", operation.getId());
						obj.put("compteId", operation.getCompteId());
						obj.put("libelle", operation.getLibelle());
						if (operation.getDate() != null) {
							obj.put("date", sdf2.format(operation.getDate()));
						}
						obj.put("montant", operation.getMontant());
						array.add(obj);
					}
					resultat.put("userId", userId);
					resultat.put("compteSrc", virement.getCptSrcIdAsInt());
					resultat.put("compteDest", virement.getCptDstIdAsInt());
					resultat.put("taille", Integer.valueOf(array.size()));
					resultat.put("liste", array);
					resu = new ResponseEntity<String>(resultat.toString(), HttpStatus.OK);
					VirerRestController.LOG.info(
							"virer RS (userId={}, compteSrc={}, compteDest={}, montant={}) est un succes", userId,
							virement.getCptSrcId(), virement.getCptDstId(), virement.getMontant());
				} catch (FonctionnelleException exp) {
					resu = new ResponseEntity<String>(
							new JSONException("Vous n'avez pas le droit de realiser un virement", exp).toJsonString(),
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
