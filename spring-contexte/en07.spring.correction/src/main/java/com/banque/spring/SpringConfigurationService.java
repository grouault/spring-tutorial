package com.banque.spring;

import org.springframework.context.annotation.Bean;
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
 * Seconde classe de configuration Spring.
 */
@Configuration
public class SpringConfigurationService {

	@Bean(name = "compteService")
	public ICompteService monCompteService(ICompteDAO compteDao) {
		return new CompteService(compteDao);
	}

	@Bean(name = "operationService")
	public IOperationService monOperationService(ICompteDAO compteDao, IOperationDAO operationDao) {
		return new OperationService(operationDao, compteDao);
	}

	@Bean(name = "authentificationService")
	public IAuthentificationService monAuthentificationService(IUtilisateurDAO utilisateurDao) {
		return new AuthentificationService(utilisateurDao);
	}
}
