package com.banque;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.banque.beans.AppBean;
import com.banque.db.ConnecteurDb;
import com.banque.db.PoolConnectionDb;

@ComponentScan("com.banque.config")
public final class Main {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private Main() {
		super();
		Main.LOG.error("Ne pas utiliser le constructeur");
	}

	public static void main(String[] args) throws SQLException {

		Main.LOG.debug("-- Debut -- ");
		
		AnnotationConfigApplicationContext appContext = null;
		// ClassPathXmlApplicationContext appContext = null;
		
		try {
			
			// appContext = new ClassPathXmlApplicationContext("spring/mes-beans.xml");
			appContext = new AnnotationConfigApplicationContext(Main.class);
			
			// recupeation du mode de connexion
			AppBean appConfig = (AppBean) appContext.getBean(AppBean.class);
			LOG.info("appConfig = {}", appConfig);
			
			if (appConfig.getIsConnecteurActive()) {
				
				//
				// CONNECTEUR FORUM
				//
				
				// recuperation d'une connexion
				ConnecteurDb connecteurDb = (ConnecteurDb) appContext.getBean(ConnecteurDb.class);
				Connection connexion = connecteurDb.getConnexion();
				LOG.info("connexion = {}", connexion);
				connecteurDb.fermerConnexion();
				
				// recuperation d'une connexion
				ConnecteurDb connecteurDb2 = (ConnecteurDb) appContext.getBean(ConnecteurDb.class);
				Connection connexion2 = connecteurDb2.getConnexion();
				LOG.info("connexion2 = {}", connexion2);
				connecteurDb.fermerConnexion();				
			
			} 
			
			if (appConfig.getIsPoolConnexion()) {
				
				//
				// POOL de connexion.
				//
				
				// recuperation du pool
				PoolConnectionDb pool = (PoolConnectionDb) appContext.getBean(PoolConnectionDb.class);
				
				// recuperation de deux connexions
				Connection connection1 = pool.getConnexion();
				Connection connection2 = pool.getConnexion();
				pool.getInfoPool();
				
				// on remet une connexion dans le pool.
				pool.addConnection(connection1);
				pool.getInfoPool();
				pool.inspectConnection();
				
				// destruction du pool de connexion
				pool.detruire();
				pool.getInfoPool();
				
			}
			
		
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