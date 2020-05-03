package fr.exagone.dao.impl;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import fr.exagone.dao.IBookShopDao;

/**
 * 
 * Besoin :
 * 
 * - besoin d'un contrôle précis sur la validation et l'annulation des transactions dans 
 *   les méthodes méters mais sans vouloir accéder directement à l'API transactionnelle
 *   sous-jacentes.
 *   
 *   Utilisation du gestionnaire de transaction spring (PlatefromTransactionManager)
 *   Comme c'est une entité abstraite que l'on utilise, les méthodes appelées 
 *   sont indépendantes de la technologie sous-jacente.
 * 
 * 
 * Note:
 *   
 *   Injection de la dataSource par le constructeur.
 *   La classe setDataSource() est défini dans la classe JdbcDaoSupport avec le mot clé final.
 *   On ne peut donc l'overrider.
 *   Sinon faire une configuration par XML
 * 
 * @author gildas
 *
 */
// @Component
public class BookShopDaoTransactionalJdbcImpl extends JdbcDaoSupport implements IBookShopDao {

	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private PlatformTransactionManager txManager;

	// constructeur avec dataSource.
	public BookShopDaoTransactionalJdbcImpl(@Autowired DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	@Override
	public void purchase(String isbn, String username) {

		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = txManager.getTransaction(transactionDefinition);
		
		try {

			// recuperation du prix
			int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, new Object[] {isbn});
			// mise à jour du stock
			getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", new Object[] {isbn });
			// mise à jour du compte
			getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", new Object[] { price, username });
			
			// commit
			txManager.commit(transactionStatus);
			
			
		} catch (DataAccessException  e) {
			// rollback
			LOG.error(e.getMessage());
			txManager.rollback(transactionStatus);
		}
		
	}
	
	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}
	
}
