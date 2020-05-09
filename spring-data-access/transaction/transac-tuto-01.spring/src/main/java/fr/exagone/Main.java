package fr.exagone;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.UnexpectedRollbackException;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.entity.impl.DefaultFooEntity;
import fr.exagone.service.IBookService;
import fr.exagone.service.IBookShopService;
import fr.exagone.service.ICashierService;
import fr.exagone.service.IFooService;
import fr.exagone.service.ex.FonctionnelleException;

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
			
			// testInsertFoo(appContext);

			// TEST Purchase
			// purchase(appContext);
			
			// TEST maj du prix
			// bookUpdatePrice(appContext);
			
			// purchase via AOP Classique
			// purchaseViaAopClassique(appContext);
			
			// opération d'achat de plusieurs livres.
			// purchaseManyBook(appContext);
			
			testIncreaseStock(appContext);
			
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

	
	/** -----------*/
	/** -- TESTs --*/
	/** -----------*/
	private static void testInsertFoo(ClassPathXmlApplicationContext appContext) throws FonctionnelleException {
		// On recupere le service authentification afin de recuperer un
		// utilisateur
		IFooService fooService = appContext.getBean(IFooService.class);
		// TEST : insert Foo.
		// ==> Permet de voir la gestion automatique de la transaction : declarative.
		Main.LOG.info("Debut de la méthode insert Foo");	
		fooService.insertFoo(new DefaultFooEntity());
		Main.LOG.info("Fin de la méthode insert Foo");
		
		// Main.LOG.info("Bonjour {} {}", fooService.getFoo(name), utilisateur.getPrenom());
	}

	/*
	 * Changer le Qualifier du DAO pour sélectionner le DAO souhaité
	 */
	private static void testPurchase(ClassPathXmlApplicationContext appContext) throws FonctionnelleException {
		IBookShopService bookShopService = appContext.getBean(IBookShopService.class, "bookShopServiceImpl");
		bookShopService.purchase("D7G 7T9", "grouault");
	}

	private static void testBookUpdatePrice(ClassPathXmlApplicationContext appContext) {
		IBookService bookService = appContext.getBean(IBookService.class);
		bookService.updatePrice("D7G 7T9", 40, "grouault");
	}

	private static void testPurchaseViaAopClassique(ClassPathXmlApplicationContext appContext) throws FonctionnelleException {
		IBookShopDao bookShopDao = (IBookShopDao) appContext.getBean("bookShopProxy");
		bookShopDao.purchase("D7G 7T9", "grouault");
	}

	private static void testPurchaseManyBook(ClassPathXmlApplicationContext appContext) throws FonctionnelleException {
		ICashierService bookShopCashierService = appContext.getBean(ICashierService.class);
		List<String> isbns = Arrays.asList(new String[] {"D7G 7T9","G4G 8O4"});
		bookShopCashierService.checkout(isbns, "grouault");
	}
	
	private static void testIncreaseStock(ClassPathXmlApplicationContext appContext) throws FonctionnelleException {
		// recuperation du service transactionnel
		LOG.info("TEST INCREASE STOCK");
		IBookShopService bookShopService = appContext.getBean("bookShopTxServiceImpl", IBookShopService.class);
		String isbn = "D7G 7T9";
		
		// bookShopService.checkStock(isbn);
		
		bookShopService.increaseStock(isbn, 1);
		
		// bookShopService.checkStock(isbn);
	}

}
