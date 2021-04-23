package fr.exagone.configuration;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.exagone.beans.Book;

@Configuration
public class BookConfiguration {

	@Bean(value="book1")
	public Book createMonPremierBook() {
		
		Book book1 = new Book();
		book1.setBookName("mon book name");
		book1.setIsbn("123-456");
		book1.setPrice(new BigDecimal(123.12));
		return book1;
		
	}
	
}
