package com.banque.web.controller.rest;

import javax.servlet.http.HttpSession;

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

import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.FonctionnelleException;
import com.banque.web.controller.rest.ex.JSONException;

import net.sf.json.JSONObject;

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
	 * Utilise l'url pour decouper les valeurs : <br/>
	 * /rest/authentifier/byurl/dj/dj
	 *
	 * @param login
	 *            le login
	 * @param password
	 *            le password
	 * @param pSession
	 *            la session
	 * @return l'utilisateur authentifie
	 */
	@SuppressWarnings("squid:S2068")
	@RequestMapping(value = "/byurl/{login}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> authentifierByUrl(@PathVariable String login, @PathVariable String password,
			HttpSession pSession) {
		AuthentifierRestController.LOG.info("authentifier byurl RS login={} pwd=Xxxx", login);

		return this.authentifiaction(login, password, pSession);
	}

	/**
	 * Authentifie un utilisateur. <br/>
	 * Utilise les parametres pour retrouver les valeurs :<br/>
	 * /rest/authentifier/byparam?login=dj&password =dj
	 *
	 * @param login
	 *            le login
	 * @param password
	 *            le password
	 * @param pSession
	 *            la session
	 * @return l'utilisateur authentifie
	 */
	@SuppressWarnings("squid:S2068")
	@RequestMapping(value = "/byparam", method = { RequestMethod.GET, RequestMethod.PUT,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> authentifierByParam(@RequestParam(name = "login", required = true) String login,
			@RequestParam(name = "password", required = true) String password, HttpSession pSession) {

		AuthentifierRestController.LOG.info("authentifier byparam RS login={} pwd=Xxxx", login);

		return this.authentifiaction(login, password, pSession);
	}

	/**
	 * Fait l'authentification.
	 *
	 * @param pLogin
	 *            un login
	 * @param pPwd
	 *            un password
	 * @param session
	 *            une session http
	 * @return la response
	 */
	private ResponseEntity<String> authentifiaction(String pLogin, String pPwd, HttpSession session) {
		if (pLogin == null || pPwd == null || pLogin.trim().length() == 0 || pPwd.trim().length() == 0) {
			return new ResponseEntity<String>(new JSONException("Usage is /{login}/{password}").toJsonString(),
					HttpStatus.BAD_REQUEST);
		}
		try {
			IUtilisateurEntity user = this.authenticationService.authentifier(pLogin, pPwd);
			JSONObject obj = new JSONObject();
			obj.put("login", user.getLogin());
			obj.put("nom", user.getNom());
			obj.put("prenom", user.getPrenom());
			obj.put("id", user.getId());
			if (session != null) {
				session.setAttribute("user", user);
			}
			return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
		} catch (FonctionnelleException exp) {
			return new ResponseEntity<String>(new JSONException("Erreur d'authentification", exp).toJsonString(),
					HttpStatus.FORBIDDEN);
		} catch (Exception exp) {
			return new ResponseEntity<String>(new JSONException("Erreur technique", exp).toJsonString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
