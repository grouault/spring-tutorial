package com.banque.web.controller.rest;

import java.text.SimpleDateFormat;
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

import com.banque.entity.IOperationEntity;
import com.banque.service.IOperationService;
import com.banque.service.ex.FonctionnelleException;
import com.banque.web.controller.rest.ex.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Gestion du listage des operations en rest. <br/>
 * URL en post/put/get : /rest/operations/lister?userId=xxx&compteId=
 * zzz&dateDebut=yyyy/MM/dd&dateFin=yyyy/MM/dd&credit=1 <br/>
 * Retourne du JSON.
 */
@RestController
@RequestMapping("/operations")
public class ListerOperationRestController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private IOperationService operationService;

	/**
	 * Liste les operations d'un compte d'un utilisateur. <br>
	 * En put/get/post : /rest/operations/lister?userId=xxx&compteId=
	 * zzz&dateDebut=yyyy/MM/dd&dateFin=yyyy/MM/dd&credit=1 <br/>
	 * Important, on ne gere pas ici la problematique d'authentification.
	 *
	 * @param userId
	 *            le user id
	 * @param compteId
	 *            l'id du compte
	 * @param dateDebut
	 *            la date de debut au format 2016/25/12
	 * @param dateFin
	 *            la date de fin au format 2016/25/12
	 * @param credit
	 *            indique si c'est un credit ou un debit ou les deux
	 *            <ul>
	 *            <li>1 = credit</li>
	 *            <li>0 = debit</li>
	 *            <li>2 = credit & debit</li>
	 *            </ul>
	 * @return un json object compose de la taille de la liste, de l'id du
	 *         compte cible et d'une liste de json objet, chacun etant une
	 *         operation
	 */
	@RequestMapping(value = "/lister", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> listerOperations(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "compteId", required = true) String compteId,
			@RequestParam(name = "dateDebut", required = false) String dateDebut,
			@RequestParam(name = "dateFin", required = false) String dateFin,
			@RequestParam(name = "credit", required = false, defaultValue = "2") String credit) {
		// TODO Faire usage du userId afin de verifier que le compte lui
		// appartient
		ListerOperationRestController.LOG.info(
				"listage des operations RS (userId={}, compteId={}, dateDebut={}, dateFin={}, credit={} )", userId,
				compteId, dateDebut, dateFin, credit);

		ResponseEntity<String> resu = null;
		if (userId == null || userId.trim().isEmpty() || compteId == null || compteId.trim().isEmpty()) {
			resu = new ResponseEntity<String>(new JSONException(
					"Usage is /operations/lister?userId=xxx&compteId=zzz&dateDebut=yyyy/MM/dd&dateFin=yyyy/MM/dd&credit=1")
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
			int compteIdInt = -1;
			try {
				compteIdInt = Integer.parseInt(compteId);
			} catch (Exception e) {
				resu = new ResponseEntity<String>(
						new JSONException("Votre compte id n'est pas un chiffre valable.", e).toJsonString(),
						HttpStatus.BAD_REQUEST);
			}

			// On verifie que le tout est ok
			if (userIdInt > 0 && compteIdInt > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				int creditInt = -1;
				try {
					creditInt = Integer.parseInt(credit);
				} catch (Exception e) {
					ListerOperationRestController.LOG.warn("Valeur pour credit invalide ({})", credit, e);
					creditInt = -1;
				}
				java.util.Date dateD = null;
				java.util.Date dateF = null;
				try {
					if (dateDebut != null) {
						dateD = sdf.parse(dateDebut);
					}
				} catch (Exception e) {
					ListerOperationRestController.LOG.warn("Date de debut invalide ({})", dateDebut, e);
				}
				try {
					if (dateFin != null) {
						dateF = sdf.parse(dateFin);
					}
				} catch (Exception e) {
					ListerOperationRestController.LOG.warn("Date de fin invalide ({})", dateFin, e);
				}
				try {

					List<IOperationEntity> operations = this.operationService.selectCritere(userIdInt, compteIdInt,
							dateD, dateF, creditInt == 1, creditInt == 0);
					ListerOperationRestController.LOG.debug(
							"listage des operations - trouve {} nb comptes pour {} et {}",
							Integer.valueOf(operations.size()), userId, compteId);
					JSONObject resultat = new JSONObject();
					JSONArray array = new JSONArray();
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					for (IOperationEntity operation : operations) {
						JSONObject obj = new JSONObject();
						obj.put("id", operation.getId());
						obj.put("libelle", operation.getLibelle());
						if (operation.getDate() != null) {
							obj.put("date", sdf2.format(operation.getDate()));
						}
						obj.put("montant", operation.getMontant());
						array.add(obj);
					}
					resultat.put("userId", userId);
					resultat.put("compteId", Integer.valueOf(compteIdInt));
					resultat.put("taille", Integer.valueOf(array.size()));
					resultat.put("liste", array);
					resu = new ResponseEntity<String>(resultat.toString(), HttpStatus.OK);
				} catch (FonctionnelleException exp) {
					resu = new ResponseEntity<String>(
							new JSONException("Vous n'avez pas le droit de visualiter les operations de ce compte", exp)
									.toJsonString(),
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
