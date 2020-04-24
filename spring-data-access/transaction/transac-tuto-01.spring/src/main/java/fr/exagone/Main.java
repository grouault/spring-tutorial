package fr.exagone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.exagone.entity.impl.DefaultFooEntity;
import fr.exagone.service.IFooService;

/**
 * Exemple.
 */
public final class Main {
	/** Main log. */
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private Main() {
		super();
		Main.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Exemple de fonctionnement.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		
		Main.LOG.info("-- Debut -- ");
		// Declaration du context Spring
		ClassPathXmlApplicationContext appContext = null;
		
		try {
			
			// Chargement des fichiers Spring
			appContext = new ClassPathXmlApplicationContext("spring/*-context.xml");
		
			// On recupere le service authentification afin de recuperer un
			// utilisateur
			IFooService fooService = appContext.getBean(IFooService.class);
			fooService.insertFoo(new DefaultFooEntity());
			
			// Main.LOG.info("Bonjour {} {}", fooService.get, utilisateur.getPrenom());

			Main.LOG.info("-- Fin --");
			
		} catch (Exception e) {
			Main.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			// Quoi qu'il arrive fermeture du context Spring
			if (appContext != null) {
				appContext.close();
			}
		}
		Main.LOG.info("-- Fin -- ");
		System.exit(0);
	}

}
