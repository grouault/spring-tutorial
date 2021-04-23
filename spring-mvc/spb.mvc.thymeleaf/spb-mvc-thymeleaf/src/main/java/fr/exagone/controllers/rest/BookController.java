package fr.exagone.controllers.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.exagone.beans.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	@Qualifier("book1")
	Book book1;
	
	@GetMapping("/{id}")
	public Book findBook(@PathVariable Long id) {
		LOG.info("Tentative de récupération du book={} ", id);
		return book1;
	}
	
	// RFC 2616: "A successful response SHOULD be (...) 204 (No Content) if the
	// action has been enacted but the response does not include an
	// entity."	
	@DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		LOG.info("Tentative de suppression du book={} ", id);
	}
	
}
