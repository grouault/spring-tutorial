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
	void purchase(String isbn, String username) throws FonctionnelleException;
	
}
