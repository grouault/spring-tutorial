package com.banque.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger LOG = LogManager.getLogger();

	
	@RequestMapping(value = "/admin.smvc", method = RequestMethod.GET)
	public String showAdminPage(Model model) {
		HomeController.LOG.debug("--> Passage dans showAdminPage");
		return "adm/pageB";
	}
	
}
