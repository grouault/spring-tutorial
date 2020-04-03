package com.banque.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
@PropertySource("classpath:spring/database.properties")
public class SpringDaoConfig {

	private String driver;
	private String url;
	private String login;
	private String pwd;
	
	public SpringDaoConfig(
			@Value("${bdd.driver}") String driver, 
			@Value("${bdd.url}") String url, 
			@Value("${bdd.login}") String login, 
			@Value("${bdd.password}") String pwd) {
		this.driver = driver;
		this.url = url;
		this.login = login;
		this.pwd = pwd;
	}
	
	
	@Bean
	public ICompteDAO getCompteDao() {
		System.out.println("getCompteDao");
		CompteDAO compteDao = new CompteDAO();
		compteDao.setDriver(this.driver);
		compteDao.setUrl(this.url);
		compteDao.setLogin(this.login);
		compteDao.setPwd(this.pwd);
		return compteDao;
		
	}
	
	@Bean
	public IUtilisateurDAO getUtilisateurDao() {
		UtilisateurDAO userDao = new UtilisateurDAO();
		userDao.setDriver(this.driver);
		userDao.setUrl(this.url);
		userDao.setLogin(this.login);
		userDao.setPwd(this.pwd);
		return userDao;
	}
	
	@Bean
	public IOperationDAO getOperationDao() {
		OperationDAO operationDao = new OperationDAO();
		operationDao.setDriver(this.driver);
		operationDao.setUrl(this.url);
		operationDao.setLogin(this.login);
		operationDao.setPwd(this.pwd);
		return operationDao;
	}
	
}
