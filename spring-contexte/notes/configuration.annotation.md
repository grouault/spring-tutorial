## Configuration par annotation

[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/INDEX.md)

Principe : Injection via les annotations
* annotations embarquées dans le code source de la classe
* configuration XML réduite au maximum

### Cycle de vie annotation:
```
1- Détection des beans : Scan des annotations @Component
2- BeanFactoryPostProcessor
3- Instanciation des beans et injection des dépendances
4- BeanPostProcessor
```

### Chargement du context-spring

Les composants sont détectés au démarrage de l'application:
- indique la racine, les sous-package sont scannés
- les archives jars des dépendances sont prises en compte

XML:
```
<context:component-scan base-package="base.package" />
```

Java:
```
@ComponentScan("com.banque")
```

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


### [liste des @annotations](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-annotation/index.md)
