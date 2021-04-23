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
import com.banque.web.model.HistoriqueBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Gestion du listage des operations en rest. <br/>
 * URL en put : /rest/operations <br/>
 * Retourne du JSON.
 */
@RestController
@RequestMapping("/operations")
public class ListerOperationRestController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private IOperationService operationService;

	/**
	 * Constructeur de l'objet.
	 */
	public ListerOperationRestController() {
		super();
	}

	/**
	 * Liste les operations d'un compte d'un utilisateur. <br>
	 * En put : /rest/operations <br/>
	 *
	 * @param historiqueBean
	 *            le bean historique
	 * @param session
	 *            la session
	 *
	 * @return un json object compose de la taille de la liste, de l'id du
	 *         compte cible et d'une liste de json objet, chacun etant une
	 *         operation
	 */
	@RequestMapping(method = {
			RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> listerOperations(@RequestBody HistoriqueBean historiqueBean, HttpSession session) {
		// ---
		// IMPORTANT : On ne peut pas faire de GET avec un RequestBody
		// ---
		ResponseEntity<String> resu = new ResponseEntity<String>(
				new JSONException("Mauvais usage du service").toJsonString(), HttpStatus.INTERNAL_SERVER_ERROR);

		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			resu = new ResponseEntity<String>(new JSONException("Vous devez vous authentifier").toJsonString(),
					HttpStatus.UNAUTHORIZED);
		} else {
			ListerOperationRestController.LOG.info(
					"listage des operations RS (userId={}, compteId={}, dateDebut={}, dateFin={}, credit={} )", userId,
					historiqueBean.getCptId(), historiqueBean.getDateDebut(), historiqueBean.getDateFin(),
					historiqueBean.getCredit());

			// On verifie que le tout est ok
			if (historiqueBean.getCptId() != null && userId.intValue() > 0
					&& historiqueBean.getCptIdAsInteger().intValue() > 0) {
				try {
					boolean credit = historiqueBean.getCredit() != null
							&& historiqueBean.getCreditAsBoolean().booleanValue();
					boolean debit = historiqueBean.getDebit() != null
							&& historiqueBean.getDebitAsBoolean().booleanValue();
					List<IOperationEntity> operations = this.operationService.selectCritere(userId.intValue(),
							historiqueBean.getCptIdAsInteger().intValue(), historiqueBean.getDateDebutAsDate(),
							historiqueBean.getDateFinAsDate(), credit, debit);
					ListerOperationRestController.LOG.debug(
							"listage des operations - trouve {} nb comptes pour {} et {}",
							Integer.valueOf(operations.size()), userId, historiqueBean.getCptId());
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
					resultat.put("compteId", historiqueBean.getCptId());
					resultat.put("taille", Integer.valueOf(array.size()));
					resultat.put("liste", array);
					resu = new ResponseEntity<String>(resultat.toString(), HttpStatus.OK);
					ListerOperationRestController.LOG.info(
							"listage des operations RS (userId={}, compteId={}, dateDebut={}, dateFin={}, credit={} ) est un succes",
							userId, historiqueBean.getCptId(), historiqueBean.getDateDebut(),
							historiqueBean.getDateFin(), historiqueBean.getCredit());

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
