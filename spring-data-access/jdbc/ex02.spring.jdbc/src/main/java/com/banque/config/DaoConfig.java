package com.banque.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.banque.dao.ICompteDAO;
import com.banque.dao.IOperationDAO;
import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.impl.CompteDAO;
import com.banque.dao.impl.OperationDAO;
import com.banque.dao.impl.UtilisateurDAO;

/*
 * on peut créer autant de configuration que l'on souhaite.
 */
@Configuration
public class DaoConfig {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Bean
	public ICompteDAO getCompteDAO() {
		ICompteDAO cdao = new CompteDAO(jdbcTemplate);
		return cdao;
	}

	@Bean
	public IOperationDAO getOperationDAO() {
		IOperationDAO oDao = new OperationDAO(jdbcTemplate);
		return oDao;
	}

	@Bean
	public IUtilisateurDAO getUtilisateurDAO() {
		IUtilisateurDAO uDao = new UtilisateurDAO(jdbcTemplate);
		return uDao;
	}
	
}
