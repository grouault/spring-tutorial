package fr.exagone.dao;

import fr.exagone.service.ex.FonctionnelleException;

public interface IBookShopDao {

	/**
	 * opération d'achat
	 * 
	 * Erreur fonctionnelle : 
	 * - stock défaillant : pas assez de ressources en stock.
	 * - account défaillant : montant alloué aux comptes ne permet pas l'achat.
	 * 
	 * @param isbn
	 * @param username
	 * @throws FonctionnelleException
	 */
	public void purchase(String isbn, String username) throws FonctionnelleException;
	
	public int checkStock(String isbn);
	
	public void increaseStock(String isbn, int stock);
	
}
