package fr.exagone.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.IBookShopService;
import fr.exagone.service.ex.FonctionnelleException;

@Service
public class BookShopServiceImpl implements IBookShopService {

	private static final Logger LOG = LogManager.getLogger();
	
	private IBookShopDao bookShopDao;
	
	@Autowired
	public BookShopServiceImpl(@Qualifier("bookShopDaoTxJdbc")IBookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	
	@Override
	public void purchase(String isbn, String username) throws FonctionnelleException {
		LOG.info("purchase: isbn = {}, userName = {}", isbn, username);
		bookShopDao.purchase(isbn, username);
	}

}
