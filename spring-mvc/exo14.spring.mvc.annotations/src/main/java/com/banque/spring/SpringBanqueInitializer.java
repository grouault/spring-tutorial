package com.banque.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Classe qui aura la chage de charger les fichiers de configuration par
 * annotation et de faire le mapping des URL. <br/>
 *
 * Le fichir web.xml n'a plus la charge de réaliser ses operations. <br/>
 *
 * Il faut être en Servlet 3.0 minimum pour utiliser cette technique.
 */
public class SpringBanqueInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static final Logger LOG = LogManager.getLogger();

	@Override
	protected Class<?>[] getRootConfigClasses() {
		SpringBanqueInitializer.LOG.trace("getRootConfigClasses");
		return new Class[] { SpringConfigurationData.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		SpringBanqueInitializer.LOG.trace("getServletConfigClasses");
		return new Class[] { SpringConfigurationWeb.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.smvc" };
	}

}
