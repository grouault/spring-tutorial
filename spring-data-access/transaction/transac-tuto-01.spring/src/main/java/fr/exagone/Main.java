package fr.exagone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.UnexpectedRollbackException;

import fr.exagone.service.IBookService;
import fr.exagone.service.IBookShopService;
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
		
			
			// TEST : insert Foo.
			// ==> Permet de voir la gestion automatique de la transaction : declarative.
			// Main.LOG.info("Debut de la méthode insert Foo");	
			// fooService.insertFoo(new DefaultFooEntity());
			// Main.LOG.info("Fin de la méthode insert Foo");
			
			// Main.LOG.info("Bonjour {} {}", fooService.get, utilisateur.getPrenom());

			// TEST Purchase
			// IBookShopService bookShopService = appContext.getBean(IBookShopService.class);
			// bookShopService.purchase("D7G 7T9", "grouault");
			
			// TEST maj du prix
			IBookService bookService = appContext.getBean(IBookService.class);
			bookService.updatePrice("D7G 7T9", 40, "grouault");
			
			
			Main.LOG.info("-- Fin --");
			
		} catch (UnexpectedRollbackException ex) {
			// Pris en compte de cette erreur quand deux transactions logiques différentes sont mise en jeu.
			// Si l'une échoue, elle fera échouer l'autre.
			LOG.error("UnexpectedRollbackException = {}", ex.getMessage());
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
