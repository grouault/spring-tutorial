package fr.exagone.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.ex.FonctionnelleException;

/**
 * Opération Repository sans le code des transactions.
 * 
 * Instancier par AOP classique : aop-classique-context.xml
 * 
 * @author gildas
 *
 */
public class BookShopDaoJdbcImpl extends JdbcDaoSupport implements IBookShopDao {

	public BookShopDaoJdbcImpl(@Autowired DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	@Override
	public void purchase(String isbn, String username) throws FonctionnelleException {
		
		// recuperation du prix
		int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, new Object[] {isbn});
		// mise à jour du stock
		getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", new Object[] {isbn });
		// mise à jour du compte
		getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", new Object[] { price, username });
		
	}

	@Override
	public int checkStock(String isbn) {
		int stock = getJdbcTemplate().queryForObject("SELECT STOCK FROM BOOK_STOCK WHERE ISBN = ?", Integer.class, new Object[] {isbn});
		return stock;
	}

	@Override
	public void increaseStock(String isbn, int stock) {
		getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK + ? WHERE ISBN = ?", new Object[] { stock, isbn });
	}

}
