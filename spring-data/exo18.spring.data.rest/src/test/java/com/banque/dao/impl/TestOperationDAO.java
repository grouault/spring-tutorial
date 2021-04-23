package com.banque.dao.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.banque.dao.IOperationDAO;
import com.banque.entity.impl.CompteEntity;
import com.banque.entity.impl.OperationEntity;
import com.banque.test.AbstractTestSpring;

/**
 * Test sur la classe OperationDAO.
 */
@Transactional
@Rollback(true)
public class TestOperationDAO extends AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private IOperationDAO operationDao;

	/**
	 * Test
	 */
	@Test
	public void saveOk() {
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(12)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1");
		uneOp1.setMontant(Double.valueOf(500D));
		OperationEntity uneOp2 = null;

		try {
			uneOp2 = this.operationDao.save(uneOp1);
		} catch (Exception e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp2);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp2.getId());
		Assert.assertEquals("Les deux operations doivent avoir le meme libelle", uneOp1.getLibelle(),
				uneOp2.getLibelle());
	}

	/**
	 * Test
	 */
	@Test
	public void updateOk() {
		// Avant le delete nous devons l'inserer
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(12)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1 de test");
		uneOp1.setMontant(Double.valueOf(500D));

		try {
			uneOp1 = this.operationDao.save(uneOp1);
		} catch (Exception e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp1.getId());

		uneOp1.setLibelle("Op1 de test bis");
		OperationEntity uneOp2 = null;
		try {
			uneOp2 = this.operationDao.save(uneOp1);
		} catch (Exception e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp2);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp2.getId());
		Assert.assertEquals("Les deux operations doivent avoir le meme libelle", uneOp1.getLibelle(),
				uneOp2.getLibelle());
	}

	/**
	 * Test
	 */
	@Test
	public void deleteOk() {
		// Avant le delete nous devons l'inserer
		OperationEntity uneOp1 = new OperationEntity();
		uneOp1.setCompte(new CompteEntity(Integer.valueOf(12)));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Op1 de test");
		uneOp1.setMontant(Double.valueOf(500D));

		try {
			uneOp1 = this.operationDao.save(uneOp1);
		} catch (Exception e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp1.getId());
		try {
			this.operationDao.delete(uneOp1);
		} catch (Exception e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test
	 */
	@Test
	public void selectOk() {
		OperationEntity uneOp1 = null;
		try {
			uneOp1 = this.operationDao.findOne(Integer.valueOf(1));
		} catch (Exception e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertEquals("L'operation doit avoir un id = 1", uneOp1.getId().intValue(), 1);
	}

	/**
	 * Test
	 */
	@Test
	public void selectAllOk() {
		List<OperationEntity> liste = null;
		OperationEntity uneOp1 = null;
		try {
			liste = this.operationDao.findAllBelongToCompteId(12);
			uneOp1 = liste.get(0);
		} catch (Exception e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertNotNull("Le compte associe a l'operation doit exister", uneOp1.getCompte());
		Assert.assertEquals("L'operation doit avoir un compte id = 12", uneOp1.getCompte().getId().intValue(), 12);
	}

	/**
	 * Test
	 */
	@Test
	public void selectCriteriaOk() {
		List<OperationEntity> liste = null;
		OperationEntity uneOp1 = null;
		try {
			java.util.Date start = new Date();
			Calendar gc = Calendar.getInstance();
			gc.setTime(start);
			gc.set(Calendar.YEAR, gc.get(Calendar.YEAR) - 5);
			java.util.Date end = new Date();
			liste = this.operationDao.selectCriteriaCredit(Integer.valueOf(12), gc.getTime(), end);
			uneOp1 = liste.get(0);
		} catch (Exception e) {
			TestOperationDAO.LOG.error("Erreur", e);
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertNotNull("Le compte associe a l'operation doit exister", uneOp1.getCompte());
		Assert.assertEquals("L'operation doit avoir un compte id = 12", uneOp1.getCompte().getId().intValue(), 12);
		Assert.assertTrue("L'operation doit avoir un montant >= 0", uneOp1.getMontant().doubleValue() >= 0);

	}

}
