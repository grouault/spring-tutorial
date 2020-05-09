package fr.exagone.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.IBookShopService;
import fr.exagone.service.ex.FonctionnelleException;

@Transactional
@Service
@Qualifier("bookShopTxServiceImpl")
public class BookShopTxServiceImpl implements IBookShopService {

	private static final Logger LOG = LogManager.getLogger();
	
	private IBookShopDao bookShopDao;
	
	// Note: utiliser le bon Qualifier pour injecter le DAO souhaité.
	@Autowired
	public BookShopTxServiceImpl(@Qualifier("bookShopDaoTxAnnotation")IBookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	/*
	 * Transactionnel par configuration AOP (aop-context.xml)
	 */
	@Override
	public void purchase(String isbn, String username) throws FonctionnelleException {
		LOG.info("purchase: isbn = {}, userName = {}", isbn, username);
		bookShopDao.purchase(isbn, username);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void increaseStock(String isbn, int stock) {
		LOG.info("increase: isbn = {}, stock = {}", isbn, stock);
		bookShopDao.checkStock(isbn);
		bookShopDao.increaseStock(isbn, stock);
		bookShopDao.checkStock(isbn);
	}

	@Override
	public int checkStock(String isbn) {
		LOG.info("checkStock: isbn = {}", isbn);
		int stock = bookShopDao.checkStock(isbn);
		return stock;
	}
	

}
