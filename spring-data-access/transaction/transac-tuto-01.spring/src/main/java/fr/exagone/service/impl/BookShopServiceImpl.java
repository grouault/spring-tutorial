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

@Service
@Qualifier("bookShopServiceImpl")
public class BookShopServiceImpl implements IBookShopService {

	private static final Logger LOG = LogManager.getLogger();
	
	private IBookShopDao bookShopDao;
	
	// Note: utiliser le bon Qualifier pour injecter le DAO souhaité.
	@Autowired
	public BookShopServiceImpl(@Qualifier("bookShopDaoJdbc")IBookShopDao bookShopDao) {
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
	public void increaseStock(String isbn, int stock) {

		String threadName = Thread.currentThread().getName();
		LOG.info("[Thread {}] - prêt à augmenter le stock du livre {}.", threadName, isbn);
		bookShopDao.increaseStock(isbn, stock);
		LOG.info("[Thread {}] - Stock du livre {} augmenté de {}.", threadName, isbn, stock);
		sleep(threadName);

	}


	@Override
	public int checkStock(String isbn) {
		
		String threadName = Thread.currentThread().getName();
		LOG.info("[Thread: {}] - prêt à vérifier le stock du livre {}.", threadName, isbn);
		int stock = bookShopDao.checkStock(isbn);
		LOG.info("[Thread: {}] - stock du livre {} : {}.", threadName, isbn, stock);
		sleep(threadName);
		
		return stock;
		
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
