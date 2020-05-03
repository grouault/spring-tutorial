package fr.exagone.entity.impl;

import java.math.BigDecimal;

import fr.exagone.entity.IBookEntity;

public class BookEntity extends AbstractEntity implements IBookEntity{

	private static final long serialVersionUID = 1L;

	private String isbn;
	
	private String bookName;
	
	private BigDecimal price;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	protected String asString() {
		// TODO Auto-generated method stub
		return null;
	}	
	

}
