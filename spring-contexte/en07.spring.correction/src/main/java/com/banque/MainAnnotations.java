package com.banque;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;
import com.banque.spring.SpringConfigurationData;
import com.banque.spring.SpringConfigurationService;

/**
 * Dans cet exemple, on ne fait pas usage des fichiers XML, on charge la classe
 * annotee.
 */
public final class MainAnnotations {
	/** Main log. */
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private MainAnnotations() {
		super();
		MainAnnotations.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Charge la/les classes de configuration Spring.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		MainAnnotations.LOG.debug("-- Debut -- ");
		// Il n'y a pas de fichier XML ici
		AnnotationConfigApplicationContext appContext = null;
		try {
			// Chargement de notre classe de configuration
			appContext = new AnnotationConfigApplicationContext(SpringConfigurationData.class,
					SpringConfigurationService.class);
			// Le reste ne change pas par rapport au chargement d'un fichier XML

			// On recupere le service authentification afin de recuperer un
			// utilisateur
			IAuthentificationService serviceAuth = appContext.getBean(IAuthentificationService.class);
			IUtilisateurEntity utilisateur = serviceAuth.authentifier("dj", "dj");
			MainAnnotations.LOG.info("Bonjour {} {}", utilisateur.getNom(), utilisateur.getPrenom());
			// On recupere le service de gestion des comptes pour recuperer la
			// liste de ses comptes
			ICompteService serviceCpt = appContext.getBean("compteService", ICompteService.class);
			List<ICompteEntity> listeCpts = serviceCpt.selectAll(utilisateur.getId().intValue());
			MainAnnotations.LOG.info("Vous avez {} comptes", String.valueOf(listeCpts.size()));
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
			MainAnnotations.LOG.info("Votre virement s'est bien effectue");
		} catch (Exception e) {
			MainAnnotations.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}

		MainAnnotations.LOG.debug("-- Fin -- ");
		System.exit(0);
	}
}