## Configuration par annotation

[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/INDEX.md)

Principe : Injection via les annotations
* annotations embarquées dans le code source de la classe
* configuration XML réduite au maximum

### [liste des @annotations](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-annotation/index.md)




### Java : Chargement du context-spring
Charger les fichiers de configurations de plusieurs manières:

    @ComponentScan("com.banque")
    @EnableAspectJAutoProxy
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

		// Permet de démarrer Spring.
		// Permet de charger Spring : recherche d'annotation sur la classe Main.
		// Spring va rechercher les annotations.
		// context = zone de mémoire avec ses propres valeurs spring <==> context
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);


* @ComponentScan: indique les packages à inspecter pour charger les configurations
