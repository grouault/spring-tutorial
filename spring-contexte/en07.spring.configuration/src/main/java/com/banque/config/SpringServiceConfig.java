package com.banque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.banque.dao.ICompteDAO;
import com.banque.dao.IOperationDAO;
import com.banque.dao.IUtilisateurDAO;
import com.banque.service.IAuthentificationService;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;
import com.banque.service.impl.AuthentificationService;
import com.banque.service.impl.CompteService;
import com.banque.service.impl.OperationService;
/**
 * - injection des Dao explicit : @Autowired
 * - bean non nommé
 * 
 * @author grouault
 *
 */
/*
@Configuration
@ComponentScan("com.banque.beans")
public class SpringServiceConfig {

	private ICompteDAO compteDao;

	private IUtilisateurDAO utilisateurDao;

	private IOperationDAO operationDao;
	
	@Bean(name="compteService")
	public ICompteService getCompteService() {
		CompteService compteService = new CompteService(compteDao);
		return compteService;
	}
	
	@Bean(name="compteService2")
	public ICompteService compteService() {
		CompteService compteService = new CompteService(compteDao);
		return compteService;
	}
	
	@Bean
	public IOperationService getOperationService() {
		OperationService operationService = new OperationService(operationDao, compteDao);
		return operationService;
	}
	
	@Bean
	public IAuthentificationService getAuthentificationService() {
		AuthentificationService authService = new AuthentificationService(utilisateurDao);
		return authService;
	}

	
}
*/