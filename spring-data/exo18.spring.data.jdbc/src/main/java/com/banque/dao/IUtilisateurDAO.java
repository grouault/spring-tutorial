package com.banque.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IUtilisateurEntity;

/**
 * Gestion des utilisateurs.
 */
public interface IUtilisateurDAO extends PagingAndSortingRepository<IUtilisateurEntity, Integer> {

	/**
	 * Selectionne le premier utilisateur ayant le login indique.
	 *
	 * @param pLogin
	 *            un login.
	 * @return l'utilisateur
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract IUtilisateurEntity selectLogin(String pLogin) throws ExceptionDao;

}