package fr.exagone;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.exagone.beans.Client;

public class Main {

	
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private Main() {
		super();
		Main.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Charge un fichier Spring.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		Main.LOG.debug("-- Debut -- ");
		ClassPathXmlApplicationContext appContext = null;
		try {
			
			// Chargement du context Spring.
			appContext = new ClassPathXmlApplicationContext("spring/mesBeans.xml");

			// Récupération des clients
			Client c1 = (Client)appContext.getBean("client1");
			Main.LOG.debug("client 1: " + c1.toString());

			// Récupération des clients
			Client c2 = (Client)appContext.getBean("client2");
			Main.LOG.debug("client 2: " + c2.toString());

			Client c3 = (Client)appContext.getBean("client3");
			Main.LOG.debug("client 3: " + c3.toString());
			
			// Recuperation de la map des villes.
			Map<String, Client> mapVilles = (Map)appContext.getBean("mapVilles");
			Main.LOG.debug("mapVilles = " + mapVilles);
			
			
		} catch (BeansException e) {
			Main.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			
			if (appContext != null) {
				appContext.close();
			}
			
			// On peut aussi faire :
			// appContext.registerShutdownHook();
			// Juste apres la creation du context, de cette manière il sera
			// detruit automatiquement à la fin du programme
		
		}

		Main.LOG.debug("-- Fin -- ");
		System.exit(0);
	}
	
}
