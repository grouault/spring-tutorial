package fr.exagone.entity;

import java.math.BigDecimal;

public interface IBookEntity extends IEntity {

	public abstract String getIsbn();
	
	public abstract void setIsbn(String isbn);
	
	public abstract String getBookName();
	
	public abstract void setBookName(String bookName);
	
	public abstract BigDecimal getPrice();
	
	public abstract void setPrice(BigDecimal price);
	

	
}
