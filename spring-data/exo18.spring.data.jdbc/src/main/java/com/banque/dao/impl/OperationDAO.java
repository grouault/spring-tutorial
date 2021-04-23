package com.banque.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.mapper.OperationJdbcMapper;
import com.banque.dao.unmapper.OperationJdbcUnMapper;
import com.banque.entity.IOperationEntity;

/**
 * Gestion des operations.
 *
 * @see <a href=
 *      "https://github.com/nurkiewicz/spring-data-jdbc-repository">https://github.com/nurkiewicz/spring-data-jdbc-repository</a>
 *
 */
@Repository
public class OperationDAO extends AbstractDAO<IOperationEntity> implements IOperationDAO {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public OperationDAO() {
		super(new OperationJdbcMapper(), new OperationJdbcUnMapper(), "operation");
	}

	@Override
	public List<IOperationEntity> selectCriteria(int unCompteId, Date unDebut, Date uneFin, Boolean pCreditDebit)
			throws ExceptionDao {
		List<IOperationEntity> result = new ArrayList<IOperationEntity>();
		OperationDAO.LOG.debug("selectCriteria sur {} unCompteId={} unDebut={} uneFin={} pCreditDebit={}",
				this.getClass(), String.valueOf(unCompteId), String.valueOf(unDebut), String.valueOf(uneFin),
				String.valueOf(pCreditDebit));
		try {
			StringBuilder request = new StringBuilder();
			request.append("select * from ");
			request.append(this.getTableName());
			request.append(" where compteId=?");
			List<Object> gaps = new ArrayList<Object>();
			gaps.add(Integer.valueOf(unCompteId));
			if (unDebut != null && uneFin == null) {
				request.append(" and date >= ?");
				gaps.add(unDebut);
			}

			if (uneFin != null && unDebut == null) {
				// Probleme sur la date de fin car MySQL a des dates en
				// 2016-08-26 18:38:22
				// Mais nous on lui donne des date en 2016-08-26 00:00:00
				// Du coup, on doit gerer la date de fin en faisant +1 jour
				// Le < (et pas <=) evite d'avoir l'operation du lendemain a
				// 00:00:00
				request.append(" and date < DATE_ADD(?, INTERVAL 1 DAY)");
				gaps.add(uneFin);
			}

			if (uneFin != null && unDebut != null) {
				// Probleme sur la date de fin car MySQL a des dates en
				// 2016-08-26 18:38:22
				// Mais nous on lui donne des date en 2016-08-26 00:00:00
				// Du coup, on doit gerer la date de fin en faisant +1 jour
				// Le < (et pas <=) evite d'avoir l'operation du lendemain a
				// 00:00:00
				request.append(" and date >= ? and date < DATE_ADD(?, INTERVAL 1 DAY)");
				gaps.add(unDebut);
				gaps.add(uneFin);
			}

			if (pCreditDebit != null) {
				if (pCreditDebit.booleanValue()) {
					request.append(" and montant >= 0");
				} else {
					request.append(" and montant <= 0");
				}
			}
			request.append(" order by date DESC;");
			OperationDAO.LOG.debug("selectCriteria sur {} requete={}", this.getClass(), request.toString());
			result = this.getJdbcTemplate().query(request.toString(), this.getRowMapper(),
					gaps.toArray(new Object[gaps.size()]));
		} catch (EmptyResultDataAccessException e) {
			OperationDAO.LOG.trace("Pas de resultat", e);
			return result;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}

}