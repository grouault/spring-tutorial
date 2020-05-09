package fr.exagone.dao.impl;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.ex.FonctionnelleException;

@Repository("bookShopDaoTxAnnotation")
public class BookShopDaoTxAnnotionImpl extends JdbcDaoSupport implements IBookShopDao {

	private static final Logger LOG = LogManager.getLogger();
	
	// injection de la DataSource par constructeur
	public BookShopDaoTxAnnotionImpl(@Autowired DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void purchase(String isbn, String username) throws FonctionnelleException {
		
		LOG.info("[{}] : achat du livre '{}'", username, isbn);
		
		// recuperation du prix
		int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, new Object[] {isbn});
		// mise à jour du stock
		getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", new Object[] {isbn });
		// mise à jour du compte
		getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", new Object[] { price, username });
		

	}

	@Override
	public int checkStock(String isbn) {
		
		String threadName = Thread.currentThread().getName();
		LOG.info("[Thread: {}] - prêt à vérifier le stock du livre {}.", threadName, isbn);
		int stock = getJdbcTemplate().queryForObject("SELECT STOCK FROM BOOK_STOCK WHERE ISBN = ?", Integer.class, new Object[] {isbn});
		LOG.info("[Thread: {}] - stock du livre {} : {}.", threadName, isbn, stock);
		sleep(threadName);
		
		return stock;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void increaseStock(String isbn, int stock) {
		
		String threadName = Thread.currentThread().getName();
		LOG.info("[Thread {}] - prêt à augmenter le stock du livre {}.", threadName, isbn);
		getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK + ? WHERE ISBN = ?", new Object[] { stock, isbn });
		LOG.info("[Thread {}] - Stock du livre {} augmenté de {}.", threadName, isbn, stock);
		sleep(threadName);
		
		LOG.info("[Thread {}] - Augmentation du stock annulé");
		throw new RuntimeException("Augmentation par erreur");
		
	}

	private void sleep(String threadName) {
		LOG.info("[Thread: {}] - En sommeil", threadName);
		try {
			Thread.sleep(1000);
			// Thread.sleep(10000);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}
		LOG.info("[Thread: {}] - Réveillé", threadName);
	}
	
}
