package com.banque.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.MauvaisMotdepasseException;
import com.banque.service.ex.UtilisateurInconnuException;
import com.banque.web.bean.LoginBean;
import com.banque.web.bean.LoginBeanJsr303;
import com.banque.web.bean.validator.LoginBeanValidator;

/**
 * Controller de login. <br/>
 */
@Controller
@SessionAttributes(names = { "utilisateur" })
public class LoginController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private IAuthentificationService service;

	/**
	 * Constructeur de l'objet.
	 */
	public LoginController() {
		super();
	}

	/**
	 * S'affiche quand on appel l'URL en get. Place le bean dans le modele.
	 *
	 * @param model
	 *            le model
	 * @return la ou aller
	 */
	@RequestMapping(value = "/login.smvc", method = RequestMethod.GET)
	public String showLogin(Model model) {
		LoginController.LOG.debug("--> Passage dans showLogin");
		model.addAttribute("loginBean", new LoginBean());
		return "login";
	}

	/**
	 * Soumet le formulaire.
	 *
	 * @param loginBean
	 *            le bean qui represente le formulaire
	 * @param modelMap
	 *            le model map
	 * @param bindingResult
	 *            result binding
	 * @return la ou aller
	 */
	@RequestMapping(value = "/dologin.smvc", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("loginBean") LoginBean loginBean, ModelMap modelMap,
			BindingResult bindingResult) {
		LoginController.LOG.debug("--> Passage dans doLogin");
		LoginBeanValidator validator = new LoginBeanValidator();
		validator.validate(loginBean, bindingResult);

		if (!bindingResult.hasErrors()) {
			try {
				LoginController.LOG.debug("-- doLogin login={}", loginBean.getLogin());
				IUtilisateurEntity utilisateur = this.service.authentifier(loginBean.getLogin(),
						loginBean.getPassword());
				// Si tout va bien on sauvegarde l'entite dans la session
				// c'est l'annotation qui fait le lien avec la session
				LoginController.LOG.debug("-- doLogin login={} resultat={}", loginBean.getLogin(), utilisateur);
				modelMap.addAttribute("utilisateur", utilisateur);
			} catch (MauvaisMotdepasseException e) {
				bindingResult.reject("error.wrong.password");
				LoginController.LOG.error("Erreur:", e);
				return "login";
			} catch (UtilisateurInconnuException e) {
				bindingResult.reject("error.user.unknown");
				LoginController.LOG.error("Erreur:", e);
				return "login";
			} catch (Exception e) {
				String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getName();
				bindingResult.reject("error.technical", new Object[] { msg }, msg);
				LoginController.LOG.error("Erreur:", e);
				return "login";
			}

			return "menu";
		}
		return "login";
	}

	/**
	 * Soumet le formulaire. <br/>
	 *
	 * Fait la validation en JSR303.
	 *
	 * @param loginBean
	 *            le bean qui represente le formulaire
	 * @param bindingResult
	 *            result binding. DOIT impérativement être juste après le bean à
	 *            valider.
	 * @param modelMap
	 *            le model map
	 * @return la ou aller
	 */
	@RequestMapping(value = "/dologin2.smvc", method = RequestMethod.POST)
	public String doLogin2(@ModelAttribute("loginBean") @Validated LoginBeanJsr303 loginBean,
			BindingResult bindingResult, ModelMap modelMap) {
		LoginController.LOG.debug("--> Passage dans doLogin2 avec JSR303");
		// Ici nous sommes en JSR303, donc pas besoin de faire :
		// LoginBeanValidator validator = new LoginBeanValidator();
		// validator.validate(loginBean, bindingResult);

		if (!bindingResult.hasErrors()) {
			try {
				LoginController.LOG.debug("-- doLogin2 login={}", loginBean.getLogin());
				IUtilisateurEntity utilisateur = this.service.authentifier(loginBean.getLogin(),
						loginBean.getPassword());
				// Si tout va bien on sauvegarde l'entite dans la session
				// c'est l'annotation qui fait le lien avec la session
				LoginController.LOG.debug("-- doLogin2 login={} resultat={}", loginBean.getLogin(), utilisateur);
				modelMap.addAttribute("utilisateur", utilisateur);
			} catch (MauvaisMotdepasseException e) {
				bindingResult.reject("error.wrong.password");
				LoginController.LOG.error("Erreur:", e);
				return "login";
			} catch (UtilisateurInconnuException e) {
				bindingResult.reject("error.user.unknown");
				LoginController.LOG.error("Erreur:", e);
				return "login";
			} catch (Exception e) {
				String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getName();
				bindingResult.reject("error.technical", new Object[] { msg }, msg);
				LoginController.LOG.error("Erreur:", e);
				return "login";
			}

			return "menu";
		}
		return "login";
	}

}
