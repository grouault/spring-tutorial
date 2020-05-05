package fr.exagone.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.ex.FonctionnelleException;

@Repository("bookShopDaoTxAnnotation")
public class BookShopDaoTxAnnotionImpl extends JdbcDaoSupport implements IBookShopDao {

	// injection de la DataSource par constructeur
	public BookShopDaoTxAnnotionImpl(@Autowired DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	@Override
	@Transactional
	public void purchase(String isbn, String username) throws FonctionnelleException {
		
		// recuperation du prix
		int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, new Object[] {isbn});
		// mise à jour du stock
		getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", new Object[] {isbn });
		// mise à jour du compte
		getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", new Object[] { price, username });
		

	}

}
