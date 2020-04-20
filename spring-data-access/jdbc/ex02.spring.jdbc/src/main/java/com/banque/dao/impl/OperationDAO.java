package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.mappers.OperationJdbcMapper;
import com.banque.entity.IOperationEntity;
import com.banque.entity.impl.OperationEntity;

/**
 * Gestion des operations.
 */
public class OperationDAO extends AbstractDAO<IOperationEntity> implements IOperationDAO {
	
	public OperationDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	private static final Logger LOG = LogManager.getLogger();

	@Override
	protected String getTableName() {
		return "operation";
	}

	@Override
	protected String getAllColumnNames() {
		return "id,libelle,montant,date,compteId";
	}

	@Override
	protected PreparedStatement buildStatementForInsert(IOperationEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "insert into " + this.getTableName() + " (libelle, montant, date, compteId) values (?,?,?,?);";
		PreparedStatement ps = connexion.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getMontant().doubleValue());
		ps.setTimestamp(3, pUneEntite.getDate());
		ps.setInt(4, pUneEntite.getCompteId().intValue());
		return ps;
	}

	@Override
	protected PreparedStatement buildStatementForUpdate(IOperationEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "update " + this.getTableName() + " set libelle=?, montant=?, date=?, compteId=? where "
				+ this.getPkName() + "=?;";
		PreparedStatement ps = connexion.prepareStatement(request);
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getMontant().doubleValue());
		ps.setTimestamp(3, pUneEntite.getDate());
		ps.setInt(4, pUneEntite.getCompteId().intValue());
		ps.setInt(5, pUneEntite.getId().intValue());
		return ps;
	}

	@Override
	public List<IOperationEntity> selectCriteria(int unCompteId, Date unDebut, Date uneFin, Boolean pCreditDebit) throws ExceptionDao {
		
		List<IOperationEntity> result = new ArrayList<IOperationEntity>();
		OperationDAO.LOG.debug("selectCriteria sur {} unCompteId={} unDebut={} uneFin={} pCreditDebit={}",
				this.getClass(), String.valueOf(unCompteId), String.valueOf(unDebut), String.valueOf(uneFin),
				String.valueOf(pCreditDebit));
		
		try {
			
			StringBuilder request = new StringBuilder();
			request.append("select ").append(this.getAllColumnNames()).append(" from ");
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

			request.append(" order by date DESC");

			request.append(';');
			OperationDAO.LOG.debug("selectCriteria sur {} requete={}", this.getClass(), request.toString());
			
			// execution de la requete.
			result = this.getJdbcTemplate().query(request.toString(), this.setPssParameters(gaps), this.getMapper());
			

		} catch (Exception e) {
			throw new ExceptionDao(e);
		} 

		return result;
	}

	@Override
	protected RowMapper<IOperationEntity> getMapper() {
		return new OperationJdbcMapper();
	}

}