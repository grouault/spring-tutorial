package com.banque.web.controller.rest;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
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
import com.banque.web.controller.rest.json.OperationJson;

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
	 * En put/get/post : /rest/operations/search?userId=xxx&compteId=
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
	 * @return un json object compose de la taille de la liste, de l'id du compte
	 *         cible et d'une liste de json objet, chacun etant une operation
	 * @throws Exception
	 *             si un probleme survient. Rattrapé par @ExceptionHandler
	 *
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<IOperationEntity>> listerOperations(
			@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "compteId", required = true) String compteId,
			@RequestParam(name = "dateDebut", required = false) String dateDebut,
			@RequestParam(name = "dateFin", required = false) String dateFin,
			@RequestParam(name = "credit", required = false, defaultValue = "2") String credit) throws Exception {
		// TODO Faire usage du userId afin de verifier que le compte lui
		// appartient
		ListerOperationRestController.LOG.info(
				"listage des operations RS (userId={}, compteId={}, dateDebut={}, dateFin={}, credit={} )", userId,
				compteId, dateDebut, dateFin, credit);

		int userIdInt = Integer.parseInt(userId);
		int compteIdInt = Integer.parseInt(compteId);

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

		List<IOperationEntity> operations = this.operationService.selectCritere(userIdInt, compteIdInt, dateD, dateF,
				creditInt == 1, creditInt == 0);
		ListerOperationRestController.LOG.debug("listage des operations - trouve {} nb comptes pour {} et {}",
				Integer.valueOf(operations.size()), userId, compteId);

		return new ResponseEntity<List<IOperationEntity>>(operations, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userId}/{cptId}/{opId}", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpEntity<OperationJson> voirOperation(@PathVariable(value = "userId") int userId,
			@PathVariable(value = "cptId") int cptId, @PathVariable(value = "opId") int opId) throws Exception {
		ListerOperationRestController.LOG.info("voir un operation RS ({} {} {})", String.valueOf(userId),
				String.valueOf(cptId), String.valueOf(opId));

		IOperationEntity op = this.operationService.select(userId, cptId, opId);
		OperationJson resu = new OperationJson(op);
		// Vers lui meme
		resu.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(ListerOperationRestController.class).voirOperation(userId, cptId, opId))
				.withSelfRel());
		// Vers le compte
		resu.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(ListerCompteRestController.class).voirCompte(userId, cptId))
				.withRel("compte"));
		// Vers l'utilisateur
		resu.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(AuthentifierRestController.class).voirUtilisateur(userId))
				.withRel("utilisateur"));
		return new ResponseEntity<OperationJson>(resu, HttpStatus.OK);
	}

}
