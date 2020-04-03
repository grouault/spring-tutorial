package com.banque;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;

/**
 * Exemple.
 */
public final class Main {
	/** Main log. */
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private Main() {
		super();
		Main.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Exemple de fonctionnement.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		Main.LOG.info("-- Debut -- ");
		// Declaration du context Spring
		ClassPathXmlApplicationContext appContext = null;
		try {
			// Chargement des fichiers Spring
			appContext = new ClassPathXmlApplicationContext("spring/*-context.xml");
			// On recupere le service authentification afin de recuperer un
			// utilisateur
			IAuthentificationService serviceAuth = appContext.getBean(IAuthentificationService.class);
			IUtilisateurEntity utilisateur = serviceAuth.authentifier("dj", "dj");
			Main.LOG.info("Bonjour {} {}", utilisateur.getNom(), utilisateur.getPrenom());
			// On recupere le service de gestion des comptes pour recuperer la
			// liste de ses comptes
			ICompteService serviceCpt = appContext.getBean("compteService", ICompteService.class);
			List<ICompteEntity> listeCpts = serviceCpt.selectAll(utilisateur.getId().intValue());
			Main.LOG.info("Vous avez {} comptes", String.valueOf(listeCpts.size()));
			// On prend deux id de comptes pour faire un virement
			Integer[] deuxId = new Integer[2];
			Iterator<ICompteEntity> iter = listeCpts.iterator();
			int id = 0;
			while (iter.hasNext() && id < deuxId.length) {
				ICompteEntity compteEntity = iter.next();
				deuxId[id] = compteEntity.getId();
				id++;
			}

			// On Effectue un virement entre deux comptes, via le service des
			// operations
			IOperationService serviceOp = (IOperationService) appContext.getBean("operationService");
			serviceOp.faireVirement(utilisateur.getId().intValue(), deuxId[0].intValue(), deuxId[1].intValue(), 5d);
			Main.LOG.info("Votre virement s'est bien effectue");
		} catch (Exception e) {
			Main.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			// Quoi qu'il arrive fermeture du context Spring
			if (appContext != null) {
				appContext.close();
			}
		}
		Main.LOG.info("-- Fin -- ");
		System.exit(0);
	}

}
