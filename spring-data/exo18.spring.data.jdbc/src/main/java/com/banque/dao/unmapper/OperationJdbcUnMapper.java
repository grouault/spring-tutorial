package com.banque.dao.unmapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.entity.IOperationEntity;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

/**
 * Objet utilitaire pour le JDBC spring.
 */
public class OperationJdbcUnMapper implements RowUnmapper<IOperationEntity> {
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public OperationJdbcUnMapper() {
		super();
	}

	@Override
	public Map<String, Object> mapColumns(IOperationEntity anEntity) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", anEntity.getId());
		result.put("libelle", anEntity.getLibelle());
		result.put("montant", anEntity.getMontant());
		result.put("date", anEntity.getDate());
		result.put("compteId", anEntity.getCompteId());
		return result;
	}

}
