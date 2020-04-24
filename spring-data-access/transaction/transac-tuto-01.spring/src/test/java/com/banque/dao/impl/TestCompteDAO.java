package com.banque.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.banque.test.AbstractTestSpring;

import fr.exagone.dao.ICompteDAO;
import fr.exagone.dao.ex.ExceptionDao;
import fr.exagone.entity.ICompteEntity;
import fr.exagone.entity.impl.CompteEntity;

/**
 * Test sur la classe CompteDAO.
 */
public class TestCompteDAO extends AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private ICompteDAO compteDao;

	/**
	 * Test sur l'insertion
	 */
	@Test
	public void insertOk() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));
		ICompteEntity unCpt2 = null;
		try {
			unCpt2 = this.compteDao.insert(unCpt1);
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt2);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt2.getId());
		Assert.assertEquals("Les deux comptes doivent avoir le meme libelle", unCpt1.getLibelle(), unCpt2.getLibelle());

	}

	/**
	 * Test sur la mise a jour
	 */
	@Test
	public void updateOk() {
		// Avant de l'updater nous devons l'inserer
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt Test");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		try {
			unCpt1 = this.compteDao.insert(unCpt1);
		} catch (ExceptionDao e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt1.getId());

		unCpt1.setLibelle("Cpt change test");
		ICompteEntity unCpt2 = null;
		try {
			unCpt2 = this.compteDao.update(unCpt1);
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt2);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt2.getId());
		Assert.assertEquals("Les deux comptes doivent avoir le meme libelle", unCpt1.getLibelle(), unCpt2.getLibelle());
	}

	/**
	 * Test la suppression
	 */
	@Test
	public void deleteOk() {
		// Avant le delete nous devons l'inserer
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));

		try {
			unCpt1 = this.compteDao.insert(unCpt1);
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt1.getId());
		boolean resu = false;
		try {
			resu = this.compteDao.delete(unCpt1);
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue("Le compte a du etre supprime", resu);
	}

	/**
	 * Selectionne un compte
	 */
	@Test
	public void selectOk() {
		ICompteEntity unCpt1 = null;
		try {
			unCpt1 = this.compteDao.select(12);
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertEquals("Le compte doit avoir un id = 12", unCpt1.getId().intValue(), 12);
	}

	/**
	 * Selectionne les comptes
	 */
	@Test
	public void selectAllOk() {
		List<ICompteEntity> liste = null;
		ICompteEntity unCpt1 = null;
		try {
			liste = this.compteDao.selectAll("id=12", null);
			unCpt1 = liste.get(0);
		} catch (ExceptionDao e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());

		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertEquals("Le compte doit avoir un id = 12", unCpt1.getId().intValue(), 12);
	}

}
