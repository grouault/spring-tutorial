package fr.exagone.service;

import fr.exagone.service.ex.FonctionnelleException;

public interface IBookShopService {

	public void purchase(String isbn, String username) throws FonctionnelleException;
	
}
