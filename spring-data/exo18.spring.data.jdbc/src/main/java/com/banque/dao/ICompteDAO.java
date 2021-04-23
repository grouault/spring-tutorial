package com.banque.dao;

import java.util.List;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ICompteEntity;

/**
 * Gestion des comptes.
 *
 */
public interface ICompteDAO extends IDAO<ICompteEntity> {
	/**
	 * Selectionne tous les comptes d'un utilisateur donnes.
	 *
	 * @param unUtilisateurId
	 *            un utilisateur id
	 * @return la liste des comptes repondant aux criteres
	 * @throws ExceptionDao
	 *             si un probleme survient
	 */
	public List<ICompteEntity> selectCriteria(int unUtilisateurId) throws ExceptionDao;
}