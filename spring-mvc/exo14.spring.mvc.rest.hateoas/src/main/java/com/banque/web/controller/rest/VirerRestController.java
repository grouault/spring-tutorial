package com.banque.web.controller.rest;

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
	 * @return un json object compose de la taille de la liste, de l'id du compte
	 *         source et destination et d'une liste de json objet operation (2 si
	 *         tout va bien), chacun etant une operation
	 * @throws Exception
	 *             si un probleme survient. Rattrapé par @ExceptionHandler
	 */
	@RequestMapping(value = "/{userId}", method = { RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<IOperationEntity>> virerPut(@PathVariable("userId") String userId,
			@RequestParam(name = "compteSrc", required = true) String compteSrc,
			@RequestParam(name = "compteDest", required = true) String compteDest,
			@RequestParam(name = "montant", required = true) String montant) throws Exception {

		VirerRestController.LOG.info("virer RS (userId={}, compteSrc={}, compteDest={}, montant={})", userId, compteSrc,
				compteDest, montant);

		int userIdInt = Integer.parseInt(userId);
		int compteSrcInt = Integer.parseInt(compteSrc);
		int compteDestInt = Integer.parseInt(compteDest);
		double montantD = Double.parseDouble(montant);

		List<IOperationEntity> operations = this.operationService.faireVirement(userIdInt, compteSrcInt, compteDestInt,
				montantD);
		return new ResponseEntity<List<IOperationEntity>>(operations, HttpStatus.OK);

	}

}
