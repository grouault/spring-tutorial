package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.dao.mappers.UtilisateurJdbcMapper;
import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;

/**
 * Gestion des utilisateurs.
 */
public class UtilisateurDAO extends AbstractDAO<IUtilisateurEntity> implements IUtilisateurDAO {
	
	public UtilisateurDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	private static final Logger LOG = LogManager.getLogger();

	@Override
	protected String getTableName() {
		return "utilisateur";
	}

	@Override
	protected String getAllColumnNames() {
		return "id,nom,prenom,login,password,sex,derniereConnection,dateDeNaissance,adresse,codePostal,telephone";
	}


	@Override
	protected PreparedStatement buildStatementForInsert(IUtilisateurEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "insert into " + this.getTableName()
				+ " (nom,prenom,login,password,sex,derniereConnection,dateDeNaissance,adresse,codePostal,telephone) values (?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = connexion.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pUneEntite.getNom());
		ps.setString(2, pUneEntite.getPrenom());
		ps.setString(3, pUneEntite.getLogin());
		ps.setString(4, pUneEntite.getPassword());
		if (pUneEntite.getSex().equals(ESex.HOMME)) {
			ps.setByte(5, (byte) 0);
		} else if (pUneEntite.getSex().equals(ESex.FEMME)) {
			ps.setByte(5, (byte) 1);
		} else {
			ps.setByte(5, (byte) 2);
		}
		ps.setTimestamp(6, pUneEntite.getDerniereConnection());
		ps.setDate(7, pUneEntite.getDateDeNaissance());
		ps.setString(8, pUneEntite.getAdresse());
		if (pUneEntite.getCodePostal() != null) {
			ps.setInt(9, pUneEntite.getCodePostal().intValue());
		} else {
			ps.setNull(9, Types.INTEGER);
		}
		ps.setString(10, pUneEntite.getTelephone());
		return ps;
	}

	@Override
	@SuppressWarnings("squid:S2068")
	protected PreparedStatement buildStatementForUpdate(IUtilisateurEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "update " + this.getTableName()
				+ " set nom=?,prenom=?,login=?,password=?,sex=?,derniereConnection=?,dateDeNaissance=?,adresse=?,codePostal=?,telephone=? where "
				+ this.getPkName() + "=?;";
		PreparedStatement ps = connexion.prepareStatement(request);
		ps.setString(1, pUneEntite.getNom());
		ps.setString(2, pUneEntite.getPrenom());
		ps.setString(3, pUneEntite.getLogin());
		ps.setString(4, pUneEntite.getPassword());
		if (pUneEntite.getSex().equals(ESex.HOMME)) {
			ps.setByte(5, (byte) 0);
		} else if (pUneEntite.getSex().equals(ESex.FEMME)) {
			ps.setByte(5, (byte) 1);
		} else {
			ps.setByte(5, (byte) 2);
		}
		ps.setTimestamp(6, pUneEntite.getDerniereConnection());
		ps.setDate(7, pUneEntite.getDateDeNaissance());
		ps.setString(8, pUneEntite.getAdresse());
		if (pUneEntite.getCodePostal() != null) {
			ps.setInt(9, pUneEntite.getCodePostal().intValue());
		} else {
			ps.setNull(9, Types.INTEGER);
		}
		ps.setString(10, pUneEntite.getTelephone());
		ps.setInt(11, pUneEntite.getId().intValue());

		return ps;
	}

	@Override
	public IUtilisateurEntity selectLogin(String pLogin) throws ExceptionDao {
		
		IUtilisateurEntity result = null;
		UtilisateurDAO.LOG.debug("selectLogin sur {} pLogin={}", this.getClass(), pLogin);
		try {
			
			// query
			String request = "select " + this.getAllColumnNames() + " from " + this.getTableName() + " where login=?;";
			
			// execute query
			result = this.getJdbcTemplate().queryForObject(request, this.getMapper(), pLogin);

		} catch (EmptyResultDataAccessException e) {
			return result;
		}
		catch (Exception e) {
			throw new ExceptionDao(e);
		} 
		return result;

	}

	@Override
	protected RowMapper<IUtilisateurEntity> getMapper() {
		return new UtilisateurJdbcMapper();
	}

}