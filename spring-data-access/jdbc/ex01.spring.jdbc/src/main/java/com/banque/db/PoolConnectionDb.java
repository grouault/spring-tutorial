package com.banque.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PoolConnectionDb {

	private static final Logger LOG = LogManager.getLogger();
	
	private Integer nbConnexionInitial;
	
	// pool de connexion
	private Queue<Connection> pool = new LinkedList<>();
	
	// actives connexions
	private Set<Connection> activesConnexions = new HashSet<>();
	
	private PoolConnectionDb() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * permet de donner des infos sur le pool.
	 * 
	 */
	public void getInfoPool() {
		LOG.info("Pool.size = {} / Connections utilisées = {} / Nb connexions initiales = {}"  , this.pool.size(), this.activesConnexions.size(), this.nbConnexionInitial);
	}
	
	/**
	 * constructeur
	 * 
	 * @param nbConnexions
	 * @param driver
	 * @param url
	 * @param login
	 * @param pwd
	 * @throws SQLException
	 */
	public PoolConnectionDb(Integer nbConnexions, ConnexionParams params) throws SQLException {
		this.nbConnexionInitial = nbConnexions;
		try {
			Class.forName(params.getDriver());
			for (int i = 0 ; i < nbConnexions ; i++) {
				Connection newConnexion = DriverManager.getConnection(params.getUrl(), params.getLogin(), params.getPwd());
				pool.add(newConnexion);
				LOG.info("Ajout d'un nouvelle connexion au pool : connexion = {}", newConnexion);
			}
			LOG.info("Nombre de connexion dans le pool - nb = {}", pool.size());
		} catch (ClassNotFoundException e) {
			throw new SQLException("Impossible de charger la classe");
		}
	}
	
	/**
	 * permet de récupérer une connexion dans le pool.
	 * @return
	 */
	public Connection getConnexion() {
		
		// recuperer la connexion dans le pool
	    Connection connection = this.pool.poll(); 	
	
	    // ajouter la connexion dans les connexions actives
	    this.activesConnexions.add(connection);
	    
	    LOG.info("Connexion libérée du pool = {} ", connection); 
	    return connection;
	
	}

	/**
	 * permet de remettre une connexion dans le pool
	 * @param connexionActive
	 */
	public void addConnection(Connection connexionActive) {
		
		if (this.activesConnexions.remove(connexionActive)) {
			this.pool.add(connexionActive);
			LOG.info("Connexion remise dans le pool - connexion = {}", connexionActive);
		} else {
			LOG.warn("Impossible d'ajouter la connexion au pool - connexion = {} " + connexionActive);
		}
		
	}

	/**
	 * visualiser les connexions dans le pool.
	 */
	public void inspectConnection() {
		for (Iterator<Connection> itPool = pool.iterator(); itPool.hasNext();) {
			Connection current = itPool.next();
			LOG.info("Connexion dans le pool : connexion = {}", current);
		}
	}

	/**
	 * destuction du pool.
	 * @throws SQLException
	 */
	public void detruire() throws SQLException {
		
		LOG.info("Destruction Pool");
		
		// transfert des connexions actives dans le pool.
		for (Iterator<Connection> it = activesConnexions.iterator(); it.hasNext();) {
			Connection connexion = it.next();
			pool.add(connexion);
			it.remove();
		}
		
		// fermeture des connexion du pool.
		for (Iterator<Connection> itPool = pool.iterator(); itPool.hasNext();) {
			Connection current = itPool.next();
			// fermeture de la connexion
			current.close();
			// suppression de la connexion du pool
			itPool.remove();
		}
		
	}
	

}
