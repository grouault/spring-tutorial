package com.banque.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.banque.entity.IOperationEntity;
import com.banque.entity.impl.OperationEntity;

public class OperationJdbcMapper implements RowMapper<IOperationEntity> {

	@Override
	public IOperationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		IOperationEntity result = new OperationEntity();
		result.setId(Integer.valueOf(rs.getInt("id")));
		result.setLibelle(rs.getString("libelle"));
		double vm = rs.getDouble("montant");
		// Le montant etait-il null ?
		boolean mnull = rs.wasNull();
		if (!mnull) {
			result.setMontant(Double.valueOf(vm));
		} else {
			result.setMontant(null);
		}
		result.setDate(rs.getTimestamp("date"));
		result.setCompteId(Integer.valueOf(rs.getInt("compteId")));
		return result;
		
	}

}
