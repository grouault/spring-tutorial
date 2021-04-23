package com.banque.dao.unmapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.entity.ICompteEntity;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

/**
 * Objet utilitaire pour le JDBC spring.
 */
public class CompteJdbcUnMapper implements RowUnmapper<ICompteEntity> {
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public CompteJdbcUnMapper() {
		super();
	}

	@Override
	public Map<String, Object> mapColumns(ICompteEntity anEntity) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", anEntity.getId());
		result.put("libelle", anEntity.getLibelle());
		result.put("solde", anEntity.getSolde());
		result.put("utilisateurId", anEntity.getUtilisateurId());
		result.put("decouvert", anEntity.getDecouvert());
		result.put("taux", anEntity.getTaux());
		return result;
	}

}
