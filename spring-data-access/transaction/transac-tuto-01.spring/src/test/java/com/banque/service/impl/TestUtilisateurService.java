package com.banque.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.banque.service.IAuthentificationService;
import com.banque.test.AbstractTestSpring;

import fr.exagone.entity.IUtilisateurEntity;
import fr.exagone.service.ex.MauvaisMotdepasseException;
import fr.exagone.service.ex.UtilisateurInconnuException;

/**
 * Test sur la classe IAuthentificationService.
 */
public class TestUtilisateurService extends AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private IAuthentificationService authentificationService;

	/**
	 * Test
	 */
	@Test
	public void testAuthentifierOk() {
		final String login = "df";
		final String pwd = "df";
		IUtilisateurEntity user = null;
		try {
			user = this.authentificationService.authentifier(login, pwd);
		} catch (Exception e) {
			TestUtilisateurService.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", user);
		Assert.assertEquals("Le login de l'utilisateur est " + login, user.getLogin(), login);
	}

	/**
	 * Test
	 *
	 * @throws Exception
	 *             UtilisateurInconnuException attendue
	 */
	@Test(expected = UtilisateurInconnuException.class)
	public void testAuthentifierKo1() throws Exception {
		final String login = "dfd";
		final String pwd = "df";
		this.authentificationService.authentifier(login, pwd);
	}

	/**
	 * Test
	 *
	 * @throws Exception
	 *             MauvaisMotdepasseException attendue
	 */
	@Test(expected = MauvaisMotdepasseException.class)
	public void testAuthentifierKo2() throws Exception {
		final String login = "df";
		final String pwd = "dfd";
		this.authentificationService.authentifier(login, pwd);
	}

}
