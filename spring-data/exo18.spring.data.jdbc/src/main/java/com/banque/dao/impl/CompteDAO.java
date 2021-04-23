package com.banque.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.banque.dao.ICompteDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.mapper.CompteJdbcMapper;
import com.banque.dao.unmapper.CompteJdbcUnMapper;
import com.banque.entity.ICompteEntity;

/**
 * Gestion des comptes.
 *
 * @see <a href=
 *      "https://github.com/nurkiewicz/spring-data-jdbc-repository">https://github.com/nurkiewicz/spring-data-jdbc-repository</a>
 *
 */
@Repository
public class CompteDAO extends AbstractDAO<ICompteEntity> implements ICompteDAO {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public CompteDAO() {
		super(new CompteJdbcMapper(), new CompteJdbcUnMapper(), "compte");
	}

	@Override
	public List<ICompteEntity> selectCriteria(int unUtilisateurId) throws ExceptionDao {
		List<ICompteEntity> result = new ArrayList<ICompteEntity>();
		CompteDAO.LOG.debug("selectAll sur {} avec comme id utilisateur", this.getClass(),
				Integer.valueOf(unUtilisateurId));
		try {
			StringBuilder request = new StringBuilder();
			request.append("select * from ");
			request.append(super.getTableName());
			request.append(" where utilisateurId=? order by libelle;");
			CompteDAO.LOG.debug("selectAll sur {} - requete={}", this.getClass(), request.toString());
			result = this.getJdbcTemplate().query(request.toString(), super.getRowMapper(),
					Integer.valueOf(unUtilisateurId));
		} catch (EmptyResultDataAccessException e) {
			CompteDAO.LOG.trace("Pas de resultat", e);
			return result;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}
}