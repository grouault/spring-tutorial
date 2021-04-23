package com.banque.dao.unmapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

/**
 * Objet utilitaire pour le JDBC spring.
 */
public class UtilisateurJdbcUnMapper implements RowUnmapper<IUtilisateurEntity> {
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public UtilisateurJdbcUnMapper() {
		super();
	}

	@Override
	public Map<String, Object> mapColumns(IUtilisateurEntity anEntity) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", anEntity.getId());
		result.put("nom", anEntity.getNom());
		result.put("prenom", anEntity.getPrenom());
		result.put("login", anEntity.getLogin());
		result.put("password", anEntity.getPassword());
		result.put("sex", anEntity.getSex() == ESex.HOMME ? Byte.valueOf((byte) 0) : Byte.valueOf((byte) 1));
		result.put("derniereConnection", anEntity.getDerniereConnection());
		result.put("adresse", anEntity.getAdresse());
		result.put("codePostal", anEntity.getCodePostal());
		result.put("telephone", anEntity.getTelephone());
		result.put("dateDeNaissance", anEntity.getDateDeNaissance());
		return result;
	}
}
