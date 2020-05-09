package fr.exagone.service;

import fr.exagone.service.ex.FonctionnelleException;

/**
 * Librairie
 * @author gildas
 *
 */
public interface IBookShopService {

	/**
	 * achat d'un livre
	 * @param isbn
	 * @param username
	 * @throws FonctionnelleException
	 */
	public void purchase(String isbn, String username) throws FonctionnelleException;

	/**
	 * augmenter le stock d'un livre
	 * @param isbn
	 * @param stock
	 */
	public void increaseStock(String isbn, int stock);
	
	/**
	 * vérifier le stock d'un livre
	 * @param isbn
	 * @return
	 */
	public int checkStock(String isbn);
	
}
