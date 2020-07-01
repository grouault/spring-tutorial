package com.banque.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banque.web.beans.LoginBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class LoginController {

	private static final Logger LOG = LogManager.getLogger();
	
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
	
	
	@RequestMapping(value = "/tomenu.smvc", method = RequestMethod.GET)
	public String toMenu() {
		LoginController.LOG.debug("--> Passage dans toMenu");
		return "menu";
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
		return "menu";
	}
	
}
