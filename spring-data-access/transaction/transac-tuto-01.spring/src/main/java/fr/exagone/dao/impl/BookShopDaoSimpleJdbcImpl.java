package fr.exagone.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.exagone.dao.IBookShopDao;

/**
 * Gestion de la transaction en utilisant directement API JDBC
 * - avantage : gérer la transaction en validant et en annulant explicitement.
 * 
 * - inconvénient : à répéter dans toutes les méthodes
 * - code propre à JDBC et doit être changer si une autre technologie doit être utilisée.
 * 
 * @author gildas
 *
 */
// @Component
public class BookShopDaoSimpleJdbcImpl implements IBookShopDao{

	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void purchase(String isbn, String username) {

		Connection conn = null;
		try {
			
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			// recuperation du prix du livre
			PreparedStatement stmt1 = conn.prepareStatement("SELECT PRICE FROM BOOK WHERE ISBN = ?");
			stmt1.setString(1, isbn);
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			int price = rs.getInt("PRICE");
			stmt1.close();
			
			// mise à jour du stock du livre.
			PreparedStatement stmt2 = conn.prepareStatement("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?");
			stmt2.setString(1, isbn);
			stmt2.executeUpdate();
			stmt2.close();
			
			// ajustement du solde du compte.
			PreparedStatement stmt3 = conn.prepareStatement("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?");
			stmt3.setInt(1, price);
			stmt3.setString(2, username);
			stmt3.executeUpdate();
			stmt3.close();
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
				LOG.error("sqlException : {}", e.getMessage());
				throw new RuntimeException(e);
			} catch (SQLException e1) {}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
