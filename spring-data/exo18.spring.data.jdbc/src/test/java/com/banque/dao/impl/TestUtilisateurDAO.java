package com.banque.dao.impl;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.UtilisateurEntity;
import com.banque.test.AbstractTestSpring;

/**
 * Test sur la classe UtilisateurDAO.
 */
@Transactional
@Rollback(true)
public class TestUtilisateurDAO extends AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private IUtilisateurDAO utilisateurDao;

	/**
	 * Test
	 */
	@Test
	public void insertOk() {
		IUtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Smith");
		unUt1.setPrenom("Jhon");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(ESex.HOMME);
		unUt1.setAdresse("Quelque part dans le test");
		unUt1.setCodePostal(Integer.valueOf(78000));
		unUt1.setDateDeNaissance(1, 1, 1988);
		unUt1.setTelephone("0148759678");

		IUtilisateurEntity unUt2 = null;
		try {
			unUt2 = this.utilisateurDao.save(unUt1);
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt2);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme libelle", unUt1.getNom(), unUt2.getNom());
	}

	/**
	 * Test
	 */
	@Test
	public void updateOk() {
		// Avant le delete nous devons l'inserer
		IUtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Nom test");
		unUt1.setPrenom("Prenom test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(ESex.HOMME);

		try {
			unUt1 = this.utilisateurDao.save(unUt1);
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());

		unUt1.setNom("Test is back");
		IUtilisateurEntity unUt2 = null;
		try {
			unUt2 = this.utilisateurDao.save(unUt1);
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt2);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme id", unUt1.getId(), unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme libelle", unUt1.getNom(), unUt2.getNom());
	}

	/**
	 * Test
	 */
	@Test
	public void deleteOk() {
		// Avant le delete nous devons l'inserer
		IUtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Nom test");
		unUt1.setPrenom("Prenom test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(ESex.HOMME);

		try {
			unUt1 = this.utilisateurDao.save(unUt1);
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());

		try {
			this.utilisateurDao.delete(unUt1);
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test
	 */
	@Test
	public void selectOk() {
		IUtilisateurEntity unUt1 = null;
		try {
			// le login df existe
			unUt1 = this.utilisateurDao.selectLogin("df");
		} catch (ExceptionDao e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());
		Assert.assertEquals("L'utilisateur doit avoir un login = df", unUt1.getLogin(), "df");
	}

	/**
	 * Test
	 */
	@Test
	public void selectAllOk() {
		Iterable<IUtilisateurEntity> iter = null;
		try {
			iter = this.utilisateurDao.findAll();
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", iter);
	}
}
