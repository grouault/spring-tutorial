## SPRING-CONTEXTE

### Chargement du context-spring

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
  
  
