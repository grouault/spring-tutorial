package com.banque.web.controller.rest;

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

import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.FonctionnelleException;
import com.banque.web.controller.rest.ex.JSONException;
import com.banque.web.model.LoginBean;

import net.sf.json.JSONObject;

/**
 * Gestion de l'authentification en rest. URL :
 * <ul>
 * <li>/rest/authentifier?login=dj&password=dj</li>
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
	 * /rest/authentifier avec un json de loginBean
	 *
	 * @param loginBean
	 *            le bean login en Json
	 * @param session
	 *            la session
	 * @return l'utilisateur authentifie
	 */
	@SuppressWarnings("squid:S2068")
	@RequestMapping(method = {
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> authentifierPost(@RequestBody LoginBean loginBean, HttpSession session) {
		AuthentifierRestController.LOG.info("authentifier RS login={} pwd=Xxxx", loginBean.getLogin());

		ResponseEntity<String> resu;

		// Spring s'assure que les valeurs ne sont pas nulles, mais elles
		// peuvent etre vides
		if (loginBean.getLogin().trim().isEmpty() || loginBean.getPassword().trim().isEmpty()) {
			resu = new ResponseEntity<String>(new JSONException("Usage is ?login=Xxxx&password=Yyyyy").toJsonString(),
					HttpStatus.BAD_REQUEST);
		} else {
			try {
				IUtilisateurEntity user = this.authenticationService.authentifier(loginBean.getLogin(),
						loginBean.getPassword());
				session.setAttribute("userId", user.getId());
				JSONObject obj = new JSONObject();
				obj.put("login", user.getLogin());
				obj.put("nom", user.getNom());
				obj.put("prenom", user.getPrenom());
				obj.put("id", user.getId());
				resu = new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
				AuthentifierRestController.LOG.info("authentifier RS login={} pwd={} est un succes",
						loginBean.getLogin(), loginBean.getPassword());
			} catch (FonctionnelleException exp) {
				resu = new ResponseEntity<String>(new JSONException("Erreur d'authentification", exp).toJsonString(),
						HttpStatus.FORBIDDEN);
			} catch (Exception exp) {
				resu = new ResponseEntity<String>(new JSONException("Une erreur est survenue", exp).toJsonString(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return resu;
	}

}
