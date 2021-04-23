package com.banque.web.controller.rest;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banque.entity.IOperationEntity;
import com.banque.service.IOperationService;
import com.banque.service.ex.FonctionnelleException;
import com.banque.web.controller.rest.ex.JSONException;

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
	 * Realiser un virement entre deux comptes. <br>
	 * En put uniquement : /rest/virer/userId?compteSrc=xxx&compteDest=
	 * Yyyy&montant=Zzzz <br/>
	 * Important, on ne gere pas ici la problematique d'authentification.
	 *
	 * @param userId
	 *            le user id
	 * @param compteSrc
	 *            l'id du compte source
	 * @param compteDest
	 *            id du compte destination
	 * @param montant
	 *            le montant
	 * @return un json object compose de la taille de la liste, de l'id du
	 *         compte source et destination et d'une liste de json objet
	 *         operation (2 si tout va bien), chacun etant une operation
	 */
	@RequestMapping(value = "/{userId}", method = { RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> virerPut(@PathVariable("userId") String userId,
			@RequestParam(name = "compteSrc", required = true) String compteSrc,
			@RequestParam(name = "compteDest", required = true) String compteDest,
			@RequestParam(name = "montant", required = true) String montant) {
		// TODO Faire usage du userId afin de verifier que le compte lui
		// appartient
		VirerRestController.LOG.info("virer RS (userId={}, compteSrc={}, compteDest={}, montant={})", userId, compteSrc,
				compteDest, montant);

		ResponseEntity<String> resu = null;
		if (userId == null || userId.trim().isEmpty() || compteSrc.trim().isEmpty() || compteDest.trim().isEmpty()
				|| montant.trim().isEmpty()) {
			resu = new ResponseEntity<String>(
					new JSONException("Usage is /virer/userId?compteSrc=xxx&compteDest=Yyyy&montant=Zzzz")
							.toJsonString(),
					HttpStatus.BAD_REQUEST);
		} else {
			int userIdInt = -1;
			try {
				userIdInt = Integer.parseInt(userId);
			} catch (Exception e) {
				resu = new ResponseEntity<String>(
						new JSONException("Votre user id n'est pas un chiffre valable.", e).toJsonString(),
						HttpStatus.BAD_REQUEST);
			}
			int compteSrcInt = -1;
			try {
				compteSrcInt = Integer.parseInt(compteSrc);
			} catch (Exception e) {
				resu = new ResponseEntity<String>(
						new JSONException("Votre compte id source n'est pas un chiffre valable.", e).toJsonString(),
						HttpStatus.BAD_REQUEST);
			}
			int compteDestInt = -1;
			try {
				compteDestInt = Integer.parseInt(compteDest);
			} catch (Exception e) {
				resu = new ResponseEntity<String>(
						new JSONException("Votre compte id destination n'est pas un chiffre valable.", e)
								.toJsonString(),
						HttpStatus.BAD_REQUEST);
			}
			double montantD = -1d;
			try {
				montantD = Double.parseDouble(montant);
			} catch (Exception e) {
				resu = new ResponseEntity<String>(
						new JSONException("Votre montant n'est pas un chiffre valable.", e).toJsonString(),
						HttpStatus.BAD_REQUEST);
			}

			// On verifie que le tout est ok
			if (userIdInt > 0 && compteSrcInt > 0 && compteDestInt > 0 && montantD > 0) {
				try {

					List<IOperationEntity> operations = this.operationService.faireVirement(userIdInt, compteSrcInt,
							compteDestInt, montantD);
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
					resultat.put("compteSrc", Integer.valueOf(compteSrcInt));
					resultat.put("compteDest", Integer.valueOf(compteDestInt));
					resultat.put("taille", Integer.valueOf(array.size()));
					resultat.put("liste", array);
					resu = new ResponseEntity<String>(resultat.toString(), HttpStatus.OK);
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
