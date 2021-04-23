package com.banque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.banque.entity.impl.CompteEntity;

/**
 * Compte DAO en utilisant Spring Data avec JPA. <br/>
 *
 * Tous les WS seront visibles via SOAP-UI sur
 * http://localhost:8080/exo18.spring.data.rest/ <br/>
 *
 * Par exemple celui là :
 * http://localhost:8080/exo18.spring.data.rest/comptes/search/findAllBelongToUserId?aUserId=2<br/>
 *
 * Pas besoin de code, juste les requetes en JPA. <br/>
 */
@RepositoryRestResource(collectionResourceRel = "comptes", path = "comptes")
public interface ICompteDAO extends PagingAndSortingRepository<CompteEntity, Integer> {
	/**
	 * Selectionne tous les comptes qui appartiennent a un utilisateur.
	 *
	 * @param aUserId
	 *            un utilisateur id
	 * @return tous les comptes de l'utilisateur
	 */
	@Query("FROM CompteEntity cpt where cpt.utilisateur.id = :aUserId order by cpt.libelle")
	public List<CompteEntity> findAllBelongToUserId(@Param("aUserId") int aUserId);
}
