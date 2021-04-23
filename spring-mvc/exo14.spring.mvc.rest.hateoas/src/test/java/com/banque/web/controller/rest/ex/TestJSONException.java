package com.banque.web.controller.rest.ex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.banque.test.AbstractTestSpring;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test le flux JSON represente par l'objet JSONException
 */
public class TestJSONException extends AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();

	@Test
	public void testJson() {
		JSONException obj = new JSONException(new Exception("Une erreur"));
		ObjectMapper mapper = new ObjectMapper();
		String resu = null;
		try {
			resu = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			TestJSONException.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		TestJSONException.LOG.debug("Flux Json pour l'exception: {}", resu);
		Assert.assertNotNull("Le flux ne doit pas etre vide", resu);
	}
}
