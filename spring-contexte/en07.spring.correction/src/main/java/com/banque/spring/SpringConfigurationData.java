package com.banque.spring;

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

/**
 * Configuration sous la forme d'une classe Java. <br/>
 *
 * La premiere classe de configuration s'occupe des DAO, la seconde des
 * services. <br/>
 *
 */
@Configuration
@PropertySource("classpath:spring/database.properties")
public class SpringConfigurationData {

	@Bean(name = "compteDao")
	public ICompteDAO monCompteDao(@Value("${db.driver}") String driver, @Value("${db.url}") String url,
			@Value("${db.login}") String login, @Value("${db.password}") String pwd) {
		CompteDAO resu = new CompteDAO();
		resu.setDriver(driver);
		resu.setUrl(url);
		resu.setLogin(login);
		resu.setPwd(pwd);
		return resu;
	}

	@Bean(name = "operationDao")
	public IOperationDAO monOperationDao(@Value("${db.driver}") String driver, @Value("${db.url}") String url,
			@Value("${db.login}") String login, @Value("${db.password}") String pwd) {
		OperationDAO resu = new OperationDAO();
		resu.setDriver(driver);
		resu.setUrl(url);
		resu.setLogin(login);
		resu.setPwd(pwd);
		return resu;
	}

	@Bean(name = "utilisateurDao")
	public IUtilisateurDAO monUtilisateurDao(@Value("${db.driver}") String driver, @Value("${db.url}") String url,
			@Value("${db.login}") String login, @Value("${db.password}") String pwd) {
		UtilisateurDAO resu = new UtilisateurDAO();
		resu.setDriver(driver);
		resu.setUrl(url);
		resu.setLogin(login);
		resu.setPwd(pwd);
		return resu;
	}

}
