package fr.exagone.dao;

import fr.exagone.entity.IBookEntity;

public interface IBookDao extends IDAO<IBookEntity> {

	void updatePrice(String isbn, Integer price);
	
}
