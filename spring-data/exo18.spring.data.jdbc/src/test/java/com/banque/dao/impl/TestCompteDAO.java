package com.banque.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.banque.dao.ICompteDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ICompteEntity;
import com.banque.entity.impl.CompteEntity;
import com.banque.test.AbstractTestSpring;

/**
 * Test sur la classe CompteDAO.
 */
@Transactional
@Rollback(true)
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
			unCpt2 = this.compteDao.save(unCpt1);
		} catch (Exception e) {
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
			unCpt1 = this.compteDao.save(unCpt1);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt1.getId());

		unCpt1.setLibelle("Cpt change test");
		ICompteEntity unCpt2 = null;
		try {
			unCpt2 = this.compteDao.save(unCpt1);
		} catch (Exception e) {
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
			unCpt1 = this.compteDao.save(unCpt1);
		} catch (Exception e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt1.getId());
		try {
			this.compteDao.delete(unCpt1);
		} catch (Exception e) {
			TestCompteDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Selectionne un compte
	 */
	@Test
	public void selectOk() {
		ICompteEntity unCpt1 = null;
		try {
			unCpt1 = this.compteDao.findOne(Integer.valueOf(12));
		} catch (Exception e) {
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
			liste = this.compteDao.selectCriteria(1);
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
