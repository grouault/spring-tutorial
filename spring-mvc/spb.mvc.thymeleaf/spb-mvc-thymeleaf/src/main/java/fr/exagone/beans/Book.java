package fr.exagone.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

public class Book implements Serializable{

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
	
	
	
}
