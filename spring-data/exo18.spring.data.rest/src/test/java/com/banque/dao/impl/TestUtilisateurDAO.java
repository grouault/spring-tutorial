package com.banque.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.banque.dao.IUtilisateurDAO;
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
	public void saveOk() {
		UtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Smith");
		unUt1.setPrenom("Jhon");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(Boolean.TRUE);
		unUt1.setAdresse("Quelque part dans le test");
		unUt1.setCodePostal(Integer.valueOf(78000));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date d = null;
		try {
			d = sdf.parse("1988/01/01");
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		unUt1.setDateDeNaissance(new java.sql.Date(d.getTime()));
		unUt1.setTelephone("0148759678");

		UtilisateurEntity unUt2 = null;

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
		UtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Nom test");
		unUt1.setPrenom("Prenom test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(Boolean.TRUE);

		try {
			unUt1 = this.utilisateurDao.save(unUt1);
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());

		unUt1.setNom("Test is back");
		UtilisateurEntity unUt2 = null;
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
		UtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login");
		unUt1.setNom("Nom test");
		unUt1.setPrenom("Prenom test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(Boolean.TRUE);

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
		UtilisateurEntity unUt1 = null;
		try {
			// le login df existe
			unUt1 = this.utilisateurDao.findByLogin("df");
		} catch (Exception e) {
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
	public void selectId() {
		UtilisateurEntity unUt1 = null;
		try {
			unUt1 = this.utilisateurDao.findOne(Integer.valueOf(1));
		} catch (Exception e) {
			TestUtilisateurDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}

		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());
		Assert.assertEquals("L'utilisateur doit avoir un login = Fargis", unUt1.getNom(), "Fargis");
	}
}
