package fr.exagone.service;

import fr.exagone.entity.IFooEntity;

public interface IFooService {

	IFooEntity getFoo(Integer id);
	
	IFooEntity getFoo(String name);
	
	void insertFoo(IFooEntity foo);
	
	void updateFoo(IFooEntity foo);
	
	void deleteFoo(IFooEntity foo);
	
}
