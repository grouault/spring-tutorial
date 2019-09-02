package fr.exagone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.exagone.Main;
import fr.exagone.beans.Adresse;
import fr.exagone.beans.Client;
import fr.exagone.beans.Voiture;

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

			// Recuperation de notre instance de client
			Voiture v1 = (Voiture)appContext.getBean("voiture1");
			Main.LOG.debug("voiture 1 : " + v1.toString());
			
			// Récupération des clients
			Client c1 = (Client)appContext.getBean("client1");
			Main.LOG.debug("client 1: " + c1.toString());
			Client c2 = (Client)appContext.getBean("client2");
			Main.LOG.debug("client 2 : " + c2.toString());
			Client c3 = (Client)appContext.getBean("client3");
			Main.LOG.debug("client 3 : " + c3.toString());
			
			// Tentative de récupération des adresses
			Adresse a2 = (Adresse)appContext.getBean("adresse2");
			Main.LOG.debug("adresse 2 : " + a2.toString());
			Adresse a3 = (Adresse)appContext.getBean("adresse3");
			Main.LOG.debug("adresse 3 : " + a3.toString());
			
			
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
