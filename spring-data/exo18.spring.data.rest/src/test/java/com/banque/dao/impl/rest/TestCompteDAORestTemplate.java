package com.banque.dao.impl.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.banque.entity.impl.CompteEntity;
import com.banque.test.AbstractTestSpring;

/**
 * Test sur la classe ICompteDAO via un RestTemplate. <br/>
 * Attention : pour fonctionner il faut avoir lancer son serveur J2EE.
 */
public class TestCompteDAORestTemplate extends AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();
	// TODO Potentiellement mettre a jour l'URL
	private static final String ROOT_URL = "http://localhost:8080/exo18.spring.data.rest/rest/";
	private static final String COMPTES_URL = TestCompteDAORestTemplate.ROOT_URL + "comptes";

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		TestCompteDAORestTemplate.LOG.warn("Ne pas oublier de lancer le serveur J2EE");
	}

	/**
	 * Usage simple du rest template
	 */
	@Test
	public void testSelectOne() {
		TestCompteDAORestTemplate.LOG.trace("Test - testSelectOne");
		RestTemplate restTemplate = new RestTemplate();
		final Integer id = Integer.valueOf(12);
		ResponseEntity<CompteEntity> response = null;
		try {
			response = restTemplate.getForEntity(TestCompteDAORestTemplate.COMPTES_URL + "/" + id, CompteEntity.class);
		} catch (Exception e) {
			TestCompteDAORestTemplate.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le flux de retour est non null", response);
		Assert.assertNotNull("Le body flux de retour est non null", response.getBody());
		// Par defaut, les ID ne sont pas dans le flux
		Assert.assertNull("Le body flux de retour ne doit pas avoir d'id", response.getBody().getId());
		Assert.assertEquals("Le libelle doit etre 'Compte Courant'", "Compte Courant", response.getBody().getLibelle());
	}

}
