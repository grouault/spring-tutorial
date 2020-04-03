package com.banque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.banque.beans.TestBean;
import com.banque.dao.ICompteDAO;
import com.banque.dao.IOperationDAO;
import com.banque.dao.IUtilisateurDAO;
import com.banque.service.IAuthentificationService;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;
import com.banque.service.impl.AuthentificationService;
import com.banque.service.impl.CompteService;
import com.banque.service.impl.OperationService;

@Configuration
@ComponentScan("com.banque.beans")
@ComponentScan("com.banque.aop")
@EnableAspectJAutoProxy
public class SpringServiceConfig2 {

	private final ICompteDAO compteDao;
	private final IOperationDAO operationDao;
	private final IUtilisateurDAO userDao;
	
	@Autowired
	private TestBean testBean;
	
	public SpringServiceConfig2(ICompteDAO compteDao, IOperationDAO operationDao, IUtilisateurDAO utilisateurDao) {
		this.compteDao = compteDao;
		this.operationDao = operationDao;
		this.userDao = utilisateurDao;
	}
	
	@Bean
	public ICompteService getCompteService() {
		System.out.println("getCompteService : compteDao =  " + this.compteDao);
		System.out.println("getCompteService : test = " + this.testBean.getTest());
		CompteService compteService = new CompteService(this.compteDao);
		return compteService;
	}
	
	@Bean
	public IOperationService getOperationService() {
		OperationService operationService = new OperationService(this.operationDao, this.compteDao);
		return operationService;
	}
	
	@Bean
	public IAuthentificationService getAuthService() {
		AuthentificationService authService = new AuthentificationService(this.userDao);
		return authService;
	}
	
}
