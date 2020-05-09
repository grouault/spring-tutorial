package fr.exagone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.ICashierService;
import fr.exagone.service.ex.FonctionnelleException;

@Service
public class BookShopCashierServiceImpl implements ICashierService {

	private IBookShopDao bookShopDao;
	
	@Autowired
	public BookShopCashierServiceImpl(@Qualifier("bookShopDaoTxAnnotation") IBookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	@Override
	@Transactional
	public void checkout(List<String> isbns, String username) throws FonctionnelleException {

		for (String isbn : isbns) {
			bookShopDao.purchase(isbn, username);
		}
		
	}

}
