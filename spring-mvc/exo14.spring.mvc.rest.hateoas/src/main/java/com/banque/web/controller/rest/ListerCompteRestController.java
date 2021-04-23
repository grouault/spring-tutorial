package com.banque.web.controller.rest;

import java.util.ArrayList;
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

import com.banque.entity.ICompteEntity;
import com.banque.service.ICompteService;
import com.banque.web.controller.rest.json.CompteJson;

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
	 * En put/pots/get : /rest/comptes/search?userId=xxx <br/>
	 * Important, on ne gere pas ici la problematique d'authentification.
	 *
	 * @param userId
	 *            le user id
	 * @return un json object compose de la taille de la liste et d'une liste de
	 *         json objet, chacun etant un compte
	 * @throws Exception
	 *             si un probleme survient. Rattrapé par @ExceptionHandler
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpEntity<List<CompteJson>> listerCompte(@RequestParam(value = "userId", required = true) String userId)
			throws Exception {
		ListerCompteRestController.LOG.info("listage des comptes RS ({})", userId);

		int userIdInt = Integer.parseInt(userId);

		List<ICompteEntity> comptes = this.compteService.selectAll(userIdInt);
		ListerCompteRestController.LOG.debug("listage des comptes - trouve {} nb comptes pour {}",
				Integer.valueOf(comptes.size()), userId);
		List<CompteJson> resu = new ArrayList<CompteJson>(comptes.size());
		for (ICompteEntity cptEntity : comptes) {
			CompteJson cj = new CompteJson(cptEntity);
			// Vers lui meme
			cj.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ListerCompteRestController.class)
					.voirCompte(cptEntity.getUtilisateurId(), cptEntity.getId())).withSelfRel());
			// Vers l'utilisateur
			cj.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(AuthentifierRestController.class)
					.voirUtilisateur(cptEntity.getUtilisateurId())).withRel("utilisateur"));
			resu.add(cj);

		}
		return new ResponseEntity<List<CompteJson>>(resu, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userId}/{cptId}", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpEntity<CompteJson> voirCompte(@PathVariable(value = "userId") int userId,
			@PathVariable(value = "cptId") int cptId) throws Exception {
		ListerCompteRestController.LOG.info("voir un compte RS ({} {})", String.valueOf(userId), String.valueOf(cptId));

		ICompteEntity compte = this.compteService.select(userId, cptId);
		CompteJson resu = new CompteJson(compte);
		// Vers lui meme
		resu.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(ListerCompteRestController.class).voirCompte(userId, cptId))
				.withSelfRel());
		// Vers l'utilisateur
		resu.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(AuthentifierRestController.class).voirUtilisateur(userId))
				.withRel("utilisateur"));
		return new ResponseEntity<CompteJson>(resu, HttpStatus.OK);
	}
}
