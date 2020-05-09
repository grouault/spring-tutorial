package fr.exagone.service;

import java.util.List;

import fr.exagone.service.ex.FonctionnelleException;

public interface ICashierService {

	/**
	 * achat de plusieurs livres
	 * @param isbns
	 * @param username
	 * @throws FonctionnelleException
	 */
	public void checkout(List<String> isbns, String username) throws FonctionnelleException;
	
}
