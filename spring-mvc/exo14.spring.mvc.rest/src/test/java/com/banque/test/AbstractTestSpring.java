package com.banque.test;

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
	// Classe mere des tests
}
