package com.banque;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;

/**
 * Exemple.
 */
// indique les répertoires ou rechercher les annotations.
@ComponentScan("com.banque")
// @EnableAspectJAutoProxy
public final class Main {

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Exemple de fonctionnement.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) throws Exception {
		Main.LOG.info("-- Debut -- ");
		
		// Définition du contexte par fichier de config : aop-context.xml
		ClassPathXmlApplicationContext context = null;

		// Définition du contexte par annotation.
		// AnnotationConfigApplicationContext context = null;
		
		try {
			
			// Permet de démarrer Spring.
			// Permet de charger Spring : recherche d'annotation sur la classe Main.
			// Spring va rechercher les annotations.
			// context = zone de mémoire avec ses propres valeurs spring <==> context
			
			// context = new AnnotationConfigApplicationContext(Main.class);
			context = new ClassPathXmlApplicationContext("spring/aop-context.xml");
		
			
			// On instancie le service authentification afin de recuperer un
			// utilisateur
			// ici j'ai été explicite, mon code ne peut pas évoluer
			// ==> trop dépendant de certaines classes.
			// IAuthentificationService serviceAuth = new AuthentificationService();
			IAuthentificationService serviceAuth = context.getBean(IAuthentificationService.class);
			
			IUtilisateurEntity utilisateur = serviceAuth.authentifier("dj", "dj");
			Main.LOG.info("Bonjour {} {}", utilisateur.getNom(), utilisateur.getPrenom());
			// On instancie le service de gestion des comptes pour recuperer la
			// liste de ses comptes
			ICompteService serviceCpt = context.getBean(ICompteService.class);
			List<ICompteEntity> listeCpts = serviceCpt.selectAll(utilisateur.getId().intValue());
			Main.LOG.info("Vous avez {} comptes", String.valueOf(listeCpts.size()));
			// On prend deux id de comptes pour faire un virement
			Integer[] deuxId = new Integer[2];
			int id = 0;
			Iterator<ICompteEntity> iter = listeCpts.iterator();
			while (iter.hasNext() && id < deuxId.length) {
				ICompteEntity compteEntity = iter.next();
				deuxId[id] = compteEntity.getId();
				id++;
			}

			// On Effectue un virement entre deux comptes, via le service des
			// operations
			// IOperationService serviceOp = new OperationService();
			IOperationService serviceOp = context.getBean(IOperationService.class);
			serviceOp.faireVirement(utilisateur.getId().intValue(), deuxId[0].intValue(), deuxId[1].intValue(), 5D);
			Main.LOG.info("Votre virement s'est bien effectue");

		} finally {
			
			if (context != null) {
				context.close();
			}
			
		}	
			
		Main.LOG.info("-- Fin -- ");
		System.exit(0);
	}

}
