package com.banque.web.controller.rest;

import javax.servlet.http.HttpSession;

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

import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.web.controller.rest.json.UtilisateurJson;

/**
 * Gestion de l'authentification en rest. URL :
 * <ul>
 * <li>/rest/authentifier/byurl/dj/dj</li>
 * <li>/rest/authentifier/byparam?login=dj&password =dj</li>
 * </ul>
 */
@RestController
@RequestMapping("/authentifier")
public class AuthentifierRestController {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private IAuthentificationService authenticationService;

	/**
	 * Authentifie un utilisateur. <br/>
	 * Utilise les parametres pour retrouver les valeurs :<br/>
	 * /rest/authentifier/byparam?login=dj&password =dj
	 *
	 * @param login
	 *            le login
	 * @param password
	 *            le password
	 * @param session
	 *            la session
	 * @return l'utilisateur authentifie
	 * @throws Exception
	 *             si un probleme survient. Rattrapé par @ExceptionHandler
	 */
	@SuppressWarnings("squid:S2068")
	@RequestMapping(value = "/byparam", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpEntity<UtilisateurJson> authentifierByParam(@RequestParam(name = "login", required = true) String login,
			@RequestParam(name = "password", required = true) String password, HttpSession session) throws Exception {

		AuthentifierRestController.LOG.info("authentifier byparam RS login={} pwd=Xxxx", login);

		IUtilisateurEntity user = this.authenticationService.authentifier(login, password);
		if (session != null) {
			session.setAttribute("user", user);
		}

		UtilisateurJson json = new UtilisateurJson(user);
		// Vers lui meme
		json.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(AuthentifierRestController.class).voirUtilisateur(user.getId()))
				.withRel("utilisateur"));
		// Vers ses comptes
		json.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ListerCompteRestController.class)
				.listerCompte(String.valueOf(user.getId()))).withRel("comptes"));

		return new ResponseEntity<UtilisateurJson>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userId}", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpEntity<UtilisateurJson> voirUtilisateur(@PathVariable(value = "userId") int userId) throws Exception {
		AuthentifierRestController.LOG.info("voir un utilisateur RS ({})", String.valueOf(userId));

		IUtilisateurEntity user = this.authenticationService.select(userId);
		UtilisateurJson json = new UtilisateurJson(user);
		// Vers lui meme
		json.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(AuthentifierRestController.class).voirUtilisateur(userId))
				.withSelfRel());
		// Vers ses comptes
		json.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(ListerCompteRestController.class).listerCompte(String.valueOf(userId)))
				.withRel("comptes"));
		return new ResponseEntity<UtilisateurJson>(json, HttpStatus.OK);
	}
}
