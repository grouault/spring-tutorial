package fr.exagone.service;

import fr.exagone.entity.IFooEntity;
import fr.exagone.service.ex.FonctionnelleException;

public interface IFooService {

	IFooEntity getFoo(Integer id);
	
	IFooEntity getFoo(String name) throws FonctionnelleException;
	
	void insertFoo(IFooEntity foo) throws FonctionnelleException;
	
	void updateFoo(IFooEntity foo);
	
	void deleteFoo(IFooEntity foo);
	
}
