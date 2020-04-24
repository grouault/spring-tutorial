package com.banque.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Classe abstraite en charge de la preparation du chargement des fichiers
 * Spring.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name = "applicationContext", locations = { "classpath*:spring/*-context.xml" })
public abstract class AbstractTestSpring {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Appele au demarrage de tous les tests.
	 */
	@BeforeClass
	public static void initLog() {
		AbstractTestSpring.LOG.warn("N'oubliez pas d'activer les transactions");
	}
}
