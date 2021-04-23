package com.banque.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.mapper.UtilisateurJdbcMapper;
import com.banque.dao.unmapper.UtilisateurJdbcUnMapper;
import com.banque.entity.IUtilisateurEntity;

/**
 * Gestion des utilisateurs.
 *
 * @see <a href=
 *      "https://github.com/nurkiewicz/spring-data-jdbc-repository">https://github.com/nurkiewicz/spring-data-jdbc-repository</a>
 */
@Repository
public class UtilisateurDAO extends AbstractDAO<IUtilisateurEntity> implements IUtilisateurDAO {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public UtilisateurDAO() {
		super(new UtilisateurJdbcMapper(), new UtilisateurJdbcUnMapper(), "utilisateur");
	}

	@Override
	public IUtilisateurEntity selectLogin(String pLogin) throws ExceptionDao {
		IUtilisateurEntity result = null;
		UtilisateurDAO.LOG.debug("selectLogin sur {} pLogin={}", this.getClass(), pLogin);
		try {
			result = this.getJdbcTemplate().queryForObject("select * from " + super.getTableName() + " where login=?;",
					this.getRowMapper(), pLogin);
		} catch (EmptyResultDataAccessException e) {
			UtilisateurDAO.LOG.trace("Pas de resultat", e);
			return result;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;

	}

}