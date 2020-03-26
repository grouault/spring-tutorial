package fr.exagone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.exagone.beans.SimpleQuestion;

public class Main {

	
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private Main() {
		super();
		Main.LOG.error("Ne pas utiliser le constructeur");
	}
	
	public static void main(String[] args) {
		
		Main.LOG.debug("-- Debut -- ");
		ClassPathXmlApplicationContext appContext = null;
		try {
			
			// Chargement du context Spring.
			appContext = new ClassPathXmlApplicationContext("spring/mes-beans.xml");

			// Recuperation de notre instance de client
			LOG.info("Premiere question");
			SimpleQuestion question = (SimpleQuestion)appContext.getBean("question-1");
			question.displayInfo();
			
			LOG.info("Deuxieme question");
			SimpleQuestion question2 = (SimpleQuestion)appContext.getBean("question-1");
			question2.displayInfo();
			
			// TEST si le même objet : singleton - prototype
			if (question.hashCode() == question2.hashCode()) {
				LOG.info("Meme instance de beans");
			} else {
				LOG.info("Deux instance de beasn différentes");
			}
			LOG.info(question.hashCode());
			LOG.info(question2.hashCode());
			
		} catch (BeansException e) {
			Main.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			
			if (appContext != null) {
				Main.LOG.info("[Main] - Close context");
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
