package com.banque.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.banque.dao.ICompteDAO;
import com.banque.entity.ICompteEntity;

/**
 * Gestion des comptes.
 */
@Repository
public class CompteDAO extends AbstractDAO<ICompteEntity> implements ICompteDAO {
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public CompteDAO() {
		super();
	}

	@Override
	protected Class<ICompteEntity> getEntityClass() {
		return ICompteEntity.class;
	}
}