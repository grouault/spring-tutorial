package fr.exagone.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.exagone.dao.IBookDao;
import fr.exagone.dao.IBookShopDao;
import fr.exagone.service.IBookService;
import fr.exagone.service.ex.FonctionnelleException;

/**
 * Transactionnel via l'aop
 * voir aop-context.xml : boolServiceOperation
 * 
 * 
 * @author gildas
 *
 */
@Service
public class BookServiceImpl implements IBookService {

	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private IBookDao bookDao;
	
	private IBookShopDao bookShopDao;
	
	@Autowired
	public BookServiceImpl(@Qualifier("bookShopDaoTxAnnotation")IBookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
	
	@Override
	public void updatePrice(String isbn, Integer price, String userName) {
 
		// operation d'achat qui se doit d'être atomique.
		// s'exécute dans une transaction indépendante.
		LOG.info("updatePrice - purchase");
		try {
			bookShopDao.purchase(isbn, userName);
		} catch (FonctionnelleException e) {
			LOG.error("update price : FonctionnelleException = {}", e.getMessage());
		} 
	
		// TODO: mettre à jour la table de suivi des opérations.
		
		// maj du prix dans une transaction indépendante
		LOG.info("updatePrice - updatePrice");
		bookDao.updatePrice(isbn, price);
			
		
	}

}
