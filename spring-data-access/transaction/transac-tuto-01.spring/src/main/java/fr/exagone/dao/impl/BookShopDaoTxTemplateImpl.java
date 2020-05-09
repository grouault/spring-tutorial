package fr.exagone.dao.impl;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.ex.FonctionnelleException;

@Repository("bookShopDaoTxTemplate")
public class BookShopDaoTxTemplateImpl extends JdbcDaoSupport implements IBookShopDao {

	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	public BookShopDaoTxTemplateImpl(@Autowired DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	/**
	 * Opération d'achat d'un livre
	 * Se déroule dans une Trxction indépendante car cette opération se doit d'être atomique.
	 * La transaction 
	 */
	@Override
	public void purchase(final String isbn, final String username) throws FonctionnelleException{

		TransactionTemplate transactionTemplate = new TransactionTemplate(txManager);
		transactionTemplate.setName(username + "-" + isbn);
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		
		LOG.info("purchase - transactionTemplate = {}", transactionTemplate.getName());
		
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					
					// recuperation du prix
					int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, new Object[] {isbn});
					// mise à jour du stock
					getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", new Object[] {isbn });
					// mise à jour du compte
					getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", new Object[] { price, username });
					
				}
				
			});
		
		} 
		catch (UncategorizedSQLException ex) {
			// prise en compte des erreurs 'check en base de donnéees'
			// - sur stock
			// - sur account
			LOG.error("purchase : error = {}", ex.getMessage());
			throw new FonctionnelleException(ex.getMessage());
		} 
		finally {
			
			// do-nothing.
			LOG.info("purchase - transactionTemplate - Nouvelle transaction ");
			/*
			* recuperation de la Transaction courante.
			TransactionStatus txStatus= txManager.getTransaction(new DefaultTransactionDefinition());
			txManager.commit(txStatus);
			LOG.info("purchase - transactionTemplate - FIN : txStatus = ", txStatus.isNewTransaction());
			*/
			
			/*
			* creation d'une nouvelle Transaction
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					// recuperation du prix
					int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, new Object[] {isbn});
				}
			});
			*/
			
			LOG.info("purchase - transactionTemplate - FIN ");	
			
		}

	}
	
	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

	@Override
	public int checkStock(String isbn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void increaseStock(String isbn, int stock) {
		// TODO Auto-generated method stub
		
	}

}
