## SPRING: configuration XML
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/INDEX.md)

### [balises et attributs xml](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/balise-attributs-xml/configuration-xml.md)
==> principales balises pour la configuration des beans

### [spring.configuration](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/index.md)
==> fonctionnalité de spring à configurer dans les fichiers de configuration de spring

### Java : Chargement du context-spring

Charger les fichiers de configurations de plusieurs manières:

    appContext = new ClassPathXmlApplicationContext("classpath:spring/mesBeans.xml",
    	"file:///data/config.xml","http://monserveur.com/config.xml");


* classpath: permet de charger une configuration dans un sous répertoire du répertoire local
* file: permet de charger un fichier dans c:\data\config.xml
* http: permet de charger un fichier depuis une URL

Exemple de code 


    appContext = new ClassPathXmlApplicationContext("spring/mesBeans.xml");
    appContext = new ClassPathXmlApplicationContext("spring-ingredient.xml, spring-accessoires.xml");
    appContext = new ClassPathXmlApplicationContext("spring/spring-*.xml");
    
    
Code générique complet de chargement


    ClassPathXmlApplicationContext appContext = null;
    try{
        // Chargement du context Spring
        appContext = new ClassPathXmlApplicationContext("spring/mesBeans.xml");
    } catch (BeansException e) {
	    Main.LOG.fatal("Erreur", e);
		System.exit(-1);
	} finally {
    
	    if (appContext != null) {
		    appContext.close();
		    }
			
		// On peut aussi faire :
		// appContext.registerShutdownHook();
		// Juste apres la creation du context, de cette manière il sera
		// detruit automatiquement à la fin du programme
        
	}    
  
  
