package com.banque.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.banque.dao.IDAO;
import com.banque.entity.IEntity;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;

/**
 * DAO abstrait.
 *
 * On fait usage ici de la librairie nurkiewicz qui prend en charge toutes les
 * operations CRUD de base.
 *
 * @param <T>
 *            Un type d'entite
 * @see <a href=
 *      "https://github.com/nurkiewicz/spring-data-jdbc-repository">https://github.com/nurkiewicz/spring-data-jdbc-repository</a>
 */
@Repository
abstract class AbstractDAO<T extends IEntity> extends JdbcRepository<T, Integer> implements IDAO<T> {
	private static final Logger LOG = LogManager.getLogger();

	private final RowMapper<T> rowMapper;
	private final String tableName;

	@Autowired
	// On garde le jdbcTemplate uniquement pour les requetes complexes (qui ne
	// sont pas CRUD)
	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructeur de l'objet.
	 *
	 * @param unMapper
	 *            un mapper
	 * @param unUnmapper
	 *            un un mapper
	 * @param aTableName
	 *            un table name
	 */
	protected AbstractDAO(RowMapper<T> unMapper, RowUnmapper<T> unUnmapper, String aTableName) {
		super(unMapper, unUnmapper, new TableDescription(aTableName, "id"));
		this.rowMapper = unMapper;
		this.tableName = aTableName;
	}

	/**
	 * Recupere le jdbc template
	 *
	 * @return le jdbc template
	 */
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete rowMapper
	 */
	protected RowMapper<T> getRowMapper() {
		return this.rowMapper;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete tableName
	 */
	protected String getTableName() {
		return this.tableName;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param jdbcTemplate
	 *            la nouvelle valeur pour l'attribut jdbcTemplate
	 */
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected <S extends T> S postCreate(S entity, Number generatedId) {
		AbstractDAO.LOG.trace("Reinjection de la cle primaire sur {} avec comme valeur {}", entity, generatedId);
		// La gestion de la clef primaire se realise de cette maniere
		entity.setId(Integer.valueOf(generatedId.intValue()));
		return entity;
	}

}