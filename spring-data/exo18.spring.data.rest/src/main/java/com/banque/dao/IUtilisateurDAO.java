package com.banque.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.banque.entity.impl.UtilisateurEntity;

/**
 * Interface representant l'utilisateur en utilisant Spring Data avec JPA. <br/>
 *
 * Tous les WS seront visibles via SOAP-UI sur
 * http://localhost:8080/exo18.spring.data.rest/ <br/>
 *
 * Pas besoin de code, juste les requetes en JPA.
 */
@RepositoryRestResource(collectionResourceRel = "utilisateur", path = "utilisateur")
public interface IUtilisateurDAO extends PagingAndSortingRepository<UtilisateurEntity, Integer> {

	/**
	 * Selectionne le premier utilisateur ayant le login indique.
	 *
	 * @param pLogin
	 *            un login.
	 * @return l'utilisateur
	 */
	public abstract UtilisateurEntity findByLogin(String pLogin);

}