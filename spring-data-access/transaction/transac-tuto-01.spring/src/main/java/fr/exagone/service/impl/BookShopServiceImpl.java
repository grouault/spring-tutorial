package fr.exagone.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.IBookShopService;

@Service
public class BookShopServiceImpl implements IBookShopService {

	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private IBookShopDao bookShopDao;
	
	@Override
	public void purchase(String isbn, String username) {
		LOG.info("purchase: isbn = {}, userName = {}", isbn, username);
		bookShopDao.purchase(isbn, username);
	}

}
