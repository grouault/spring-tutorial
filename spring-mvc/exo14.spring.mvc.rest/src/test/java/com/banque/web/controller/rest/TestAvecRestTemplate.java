package com.banque.web.controller.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.banque.test.AbstractTestSpring;

/**
 * Test sur la classe IAuthentificationService via un RestTemplate. <br/>
 * Attention : pour fonctionner il faut avoir lancer son serveur J2EE.
 */
public class TestAvecRestTemplate extends AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();
	// TODO Potentiellement mettre a jour l'URL
	private static final String ROOT_URL = "http://localhost:8080/exo14.spring.mvc.rest/rest/";
	private static final String AUTHENTIFIER_URL = TestAvecRestTemplate.ROOT_URL + "authentifier/byparam";
	private static final String LISTER_CPT_URL = TestAvecRestTemplate.ROOT_URL + "comptes/lister";

	/**
	 * Initialisation du log. <br/>
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		TestAvecRestTemplate.LOG.warn("Ne pas oublier de lancer le serveur J2EE");
	}

	/**
	 * Usage simple du rest template
	 */
	@Test
	public void testAuthentifierPostOk() {
		TestAvecRestTemplate.LOG.trace("Test - testAuthentifierPostOk");
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> info = new LinkedMultiValueMap<String, String>();
		info.add("login", "df");
		info.add("password", "df");
		try {
			// Envoie de login/pwd en post en tant que parametres
			restTemplate.postForLocation(TestAvecRestTemplate.AUTHENTIFIER_URL, info);
		} catch (RestClientException e) {
			TestAvecRestTemplate.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Usage simple du rest template
	 */
	@Test
	public void testAuthentifierGetOk() {
		TestAvecRestTemplate.LOG.trace("Test - testAuthentifierGetOk");
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TestAvecRestTemplate.AUTHENTIFIER_URL)
				.queryParam("login", "df").queryParam("password", "df");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = null;
		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
		} catch (RestClientException e) {
			TestAvecRestTemplate.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		TestAvecRestTemplate.LOG.debug("Resultat-flux de retour {}", response.getBody());
		Assert.assertNotNull("Le flux de retour est non null", response);
		Assert.assertNotNull("Le flux String de retour est non null", response.getBody());
		Assert.assertFalse("Le flux String de retour est non vide", response.getBody().isEmpty());
	}

	/**
	 * Usage de exchange avec le rest template
	 */
	@Test
	public void testListerCompteOk() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		MultiValueMap<String, Object> info = new LinkedMultiValueMap<String, Object>();
		info.add("login", "df");
		info.add("password", "df");
		HttpEntity<?> entity = new HttpEntity<>(info, headers);

		ResponseEntity<String> result = restTemplate.exchange(TestAvecRestTemplate.AUTHENTIFIER_URL, HttpMethod.POST,
				entity, String.class);
		TestAvecRestTemplate.LOG.debug("Resultat-status {}", result.getStatusCode());
		TestAvecRestTemplate.LOG.debug("Resultat-flux de retour {}", result.getBody());

		// // Afin de lister le contenu du header de la réponse
		// for (Map.Entry<String, List<String>> elm :
		// result.getHeaders().entrySet()) {
		// TestUtilisateurWService.LOG.debug("{}={}", elm.getKey(),
		// elm.getValue());
		// }

		// Pour la seconde requete on recupere le JSESSIONID et on le repousse
		// C'est un exemple de propagation de la session
		headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Cookie", result.getHeaders().get("Set-Cookie").get(0));
		info = new LinkedMultiValueMap<String, Object>();
		info.add("userId", String.valueOf(1));
		entity = new HttpEntity<>(info, headers);
		// Envoie en POST
		result = restTemplate.exchange(TestAvecRestTemplate.LISTER_CPT_URL, HttpMethod.POST, entity, String.class);
		TestAvecRestTemplate.LOG.debug("Resultat-status {}", result.getStatusCode());
		TestAvecRestTemplate.LOG.debug("Resultat-flux de retour {}", result.getBody());
	}

}
