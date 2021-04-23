package com.banque.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.banque.entity.impl.OperationEntity;

/**
 * Interface representant l'operation DAO en utilisant Spring Data avec JPA.
 * <br/>
 *
 * Tous les WS seront visibles via SOAP-UI sur
 * http://localhost:8080/exo18.spring.data.rest/ <br/>
 *
 * Pas besoin de code, juste les requetes en JPA.
 */
@RepositoryRestResource(collectionResourceRel = "operations", path = "operations")
public interface IOperationDAO extends PagingAndSortingRepository<OperationEntity, Integer> {

	/**
	 * Selectionne les operations en fonction des criteres donnes.
	 *
	 * @param unCompteId
	 *            un compte id
	 * @param unDebut
	 *            une date de debut
	 * @param uneFin
	 *            une date de fin
	 * @return la liste des opertaions repondant aux criteres
	 *
	 */
	@Query("FROM OperationEntity op where op.compte.id = :unCompteId and op.date between :unDebut and :uneFin order by op.date DESC")
	public abstract List<OperationEntity> selectCriteria(@Param("unCompteId") Integer unCompteId,
			@Param("unDebut") @Temporal(javax.persistence.TemporalType.TIMESTAMP) @DateTimeFormat(pattern = "yyyy-MM-dd") Date unDebut,
			@Param("uneFin") @Temporal(javax.persistence.TemporalType.TIMESTAMP) @DateTimeFormat(pattern = "yyyy-MM-dd") Date uneFin);

	/**
	 * Selectionne les operations en fonction des criteres donnes.
	 *
	 * @param unCompteId
	 *            un compte id
	 * @param unDebut
	 *            une date de debut
	 * @param uneFin
	 *            une date de fin
	 * @return la liste des opertaions repondant aux criteres
	 *
	 */
	@Query("FROM OperationEntity op where op.compte.id = :unCompteId and op.date between :unDebut and :uneFin and op.montant < 0 order by op.date DESC")
	public abstract List<OperationEntity> selectCriteriaDebit(@Param("unCompteId") Integer unCompteId,
			@Param("unDebut") @Temporal(javax.persistence.TemporalType.TIMESTAMP) @DateTimeFormat(pattern = "yyyy-MM-dd") Date unDebut,
			@Param("uneFin") @Temporal(javax.persistence.TemporalType.TIMESTAMP) @DateTimeFormat(pattern = "yyyy-MM-dd") Date uneFin);

	/**
	 * Selectionne les operations en fonction des criteres donnes.
	 *
	 * @param unCompteId
	 *            un compte id
	 * @param unDebut
	 *            une date de debut
	 * @param uneFin
	 *            une date de fin
	 * @return la liste des opertaions repondant aux criteres
	 *
	 */
	@Query("FROM OperationEntity op where op.compte.id = :unCompteId and op.date between :unDebut and :uneFin and op.montant > 0 order by op.date DESC")
	public abstract List<OperationEntity> selectCriteriaCredit(@Param("unCompteId") Integer unCompteId,
			@Param("unDebut") @Temporal(javax.persistence.TemporalType.TIMESTAMP) @DateTimeFormat(pattern = "yyyy-MM-dd") Date unDebut,
			@Param("uneFin") @Temporal(javax.persistence.TemporalType.TIMESTAMP) @DateTimeFormat(pattern = "yyyy-MM-dd") Date uneFin);

	/**
	 * Selectionne toutes les operation qui appartiennent a un compte.
	 *
	 * @param aCptId
	 *            un id de compte
	 * @return toutes les operations d'un compte
	 */
	@Query("FROM OperationEntity op where op.compte.id = :aCptId order by op.date DESC")
	public List<OperationEntity> findAllBelongToCompteId(@Param("aCptId") int aCptId);
}