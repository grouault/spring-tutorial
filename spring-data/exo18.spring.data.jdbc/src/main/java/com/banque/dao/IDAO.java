package com.banque.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.banque.entity.IEntity;

/**
 * Interface mere de tous les DAO. <br/>
 * Ne pas oublier de changer le mot de passe si necessaire.
 *
 * @param <T>
 *            Un type d'entite.
 */
public interface IDAO<T extends IEntity> extends PagingAndSortingRepository<T, Integer> {
	// Interface
}