package com.banque.web.controller.rest;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.banque.entity.impl.UtilisateurEntity;
import com.banque.test.AbstractTestSpring;
import com.banque.web.model.LoginBean;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test sur le web service d'authentification. <br/>
 * Utilisation de mock = pas besoin de deployer les web services.
 *
 */
@WebAppConfiguration
public class TestAvecMock extends AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();

	private static final String AUTHENTIFIER_URL = "/authentifier";
	private static final String LISTER_CPT_URL = "/comptes";

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	/**
	 * Fabrication de notre mock MVC
	 */
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	/**
	 * Usage des mock.
	 */
	@Test
	public void testAuthentifierOk() {
		TestAvecMock.LOG.trace("Test - testAuthentifierOk");
		LoginBean loginBean = new LoginBean();
		loginBean.setLogin("df");
		loginBean.setPassword("df");
		// Objet -> Json en Jackson
		ObjectMapper mapper = new ObjectMapper();
		try {
			String loginBeanAsJSon = mapper.writeValueAsString(loginBean);
			TestAvecMock.LOG.debug(loginBeanAsJSon);

			// On appelle le web service en POST, en envoyant du JSON
			// Attention : il n'y a plus d'URL complet (http://....)
			// Juste l'URL du web service <=> @RequestMapping
			ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post(TestAvecMock.AUTHENTIFIER_URL)
					.contentType(MediaType.APPLICATION_JSON).content(loginBeanAsJSon));
			// On test que le resultat est bien 200
			result.andExpect(MockMvcResultMatchers.status().isOk());
			// Avec un flux JSON qui contient une propriete id qui vaut 1
			result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Integer.valueOf(1)));
		} catch (Exception e) {
			TestAvecMock.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Usage des mock.
	 */
	@Test
	public void testListerCompteOk() {
		TestAvecMock.LOG.trace("Test - testListerCompteOk");
		// Bean qui sert à l'authentification
		LoginBean loginBean = new LoginBean();
		loginBean.setLogin("df");
		loginBean.setPassword("df");
		// Objet session qui permet de propager la session dans nos requetes qui
		// suivent l'authentification
		HttpSession session = null;
		UtilisateurEntity objRetour = null;
		// Objet -> Json en Jackson
		ObjectMapper mapper = new ObjectMapper();
		try {
			String loginBeanAsJSon = mapper.writeValueAsString(loginBean);

			// On appelle le web service en POST, en envoyant du JSON
			// Attention : il n'y a plus d'URL complet (http://....)
			// Juste l'URL du web service <=> @RequestMapping
			ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post(TestAvecMock.AUTHENTIFIER_URL)
					.contentType(MediaType.APPLICATION_JSON).content(loginBeanAsJSon));
			// On test que le resultat est bien 200
			result.andExpect(MockMvcResultMatchers.status().isOk());
			// Avec un flux JSON qui contient une propriete id qui vaut 1
			result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Integer.valueOf(1)));
			// On recupère le session pour la repasser a la requete suivante
			// On fait cela car notre WS se sert de HttpSession
			session = result.andReturn().getRequest().getSession();
			// Recupération du flux en JSON
			String fullStringResult = result.andReturn().getResponse().getContentAsString();
			TestAvecMock.LOG.debug("JSON Result is {}", fullStringResult);
			// Json -> Object en Jackson
			objRetour = mapper.readValue(fullStringResult, UtilisateurEntity.class);
		} catch (Exception e) {
			TestAvecMock.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La session ne doit pas être null", session);
		Assert.assertNotNull("La session doit contenir le user id", session.getAttribute("userId"));
		Assert.assertNotNull("L'objet traduit du Json ne doit pas etre null", objRetour);
		Assert.assertEquals("Le user id est le même que celui du flux JSON", objRetour.getId(),
				session.getAttribute("userId"));

		// On va maintenant recuperer les comptes de l'utilisateur connecte
		try {
			// On appelle le web service en GET, en remettant la session
			ResultActions result = this.mockMvc.perform(
					MockMvcRequestBuilders.get(TestAvecMock.LISTER_CPT_URL).session((MockHttpSession) session));
			// On test que le resultat est bien 200
			result.andExpect(MockMvcResultMatchers.status().isOk());
			// Avec un flux JSON qui contient une propriete liste qui n'est pas
			// null
			result.andExpect(MockMvcResultMatchers.jsonPath("$.liste").value(CoreMatchers.notNullValue()));
			// Recupération du flux en JSON
			String fullStringResult = result.andReturn().getResponse().getContentAsString();
			TestAvecMock.LOG.debug("JSON Result is {}", fullStringResult);
		} catch (Exception e) {
			TestAvecMock.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
	}
}
