package com.banque.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * connecteur gérant la connexion à une base de données.
 * @author grouault
 *
 */
public class ConnecteurDb {

	private static final Logger LOG = LogManager.getLogger();
	
	private Connection connexion;
	
	public ConnecteurDb(ConnexionParams params) throws SQLException {
		this.constructConnexion(params);
	}

	// recuperation de la connexion.
	public Connection getConnexion() throws SQLException {
		LOG.info("récupération de la connexion : connexion = {}", this.connexion);
		return this.connexion;
	}
	
	// fermeture de la connexion.
	public void fermerConnexion() throws SQLException {
		if (this.connexion != null && !this.connexion.isClosed()) {
			LOG.info("tentative de fermeture de la connexion : connexion = {}", this.connexion);
			this.connexion.close();	
		}
	}
	
	// construction de la connexion
	protected void constructConnexion(ConnexionParams params) throws SQLException {
		try {
			Class.forName(params.getDriver());
			this.connexion = DriverManager.getConnection(params.getUrl(), params.getLogin(), params.getPwd());
			LOG.info("construction de la connexion : connexion = {}", this.connexion);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Classe introvable : " + e.getMessage());
		}
	}
		
	
}
