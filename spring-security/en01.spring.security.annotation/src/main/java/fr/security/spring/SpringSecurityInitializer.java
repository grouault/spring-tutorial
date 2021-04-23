package fr.security.spring;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Classe qui aura la chage de charger les fichiers de configuration par
 * annotation et de faire le mapping des URL. <br/>
 *
 * Le fichir web.xml n'a plus la charge de réaliser ces operations. <br/>
 *
 * Il faut être en Servlet 3.0 minimum pour utiliser cette technique. <br/>
 *
 * Nous ne sommes pas en Spring MVC ici, mais nous faisons usage du Spring
 * Security, on herite de AbstractSecurityWebApplicationInitializer <br/>
 *
 * Permet de se passer de la declaration du filtre Spring Security ainsi que du
 * listener de Spring pour le chargement des fichiers de configurations.
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	/**
	 * Constructeur de l'objet.
	 *
	 * Charge le / les classes annotees
	 */
	public SpringSecurityInitializer() {
		super(SpringSecurityConfiguration.class);
	}

}
