Projet simple permettant d'instancier des composants dans le conteneur léger

# Exemple de création de beans:
- par le constructeur sans paramètre
- par le constructeur avec paramètre
- création de deux beans dans une même déclaration

# Code générer par l'exécution:
[02/09/2019 22:23:16] - DEBUG - fr.exagone.Main.main(Main.java:33) - -- Debut --  
[02/09/2019 22:23:16] -  INFO - org.springframework.context.support.AbstractApplicationContext.prepareRefresh(AbstractApplicationContext.java:583) - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@6fb0d3ed: startup date [Mon Sep 02 22:23:16 CEST 2019]; root of context hierarchy 
[02/09/2019 22:23:16] -  INFO - org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317) - Loading XML bean definitions from class path resource [spring/mesBeans.xml] 
[02/09/2019 22:23:16] - DEBUG - fr.exagone.Main.main(Main.java:42) - voiture 1 : marque:Opel, modele:Grandland X 
[02/09/2019 22:23:16] - DEBUG - fr.exagone.Main.main(Main.java:46) - client 1: nom:lunz, prenom:Axel, adresse[rue:rue Roland Garros, ville:Athis, codePostal:91234, pays:France] 
[02/09/2019 22:23:16] - DEBUG - fr.exagone.Main.main(Main.java:48) - client 2 : nom:Dominguez, prenom:Dimitri, adresse[rue:rue des lilas, ville:Pré-Saint-Gervais, codePostal:93087, pays:France] 
[02/09/2019 22:23:16] - DEBUG - fr.exagone.Main.main(Main.java:50) - client 3 : nom:Vanavermat, prenom:Greg, adresse[rue:rue du tennis, ville:Loisirs, codePostal:10000, pays:Pays des rêves] 
[02/09/2019 22:23:16] - DEBUG - fr.exagone.Main.main(Main.java:54) - adresse 2 : [rue:rue des lilas, ville:Pré-Saint-Gervais, codePostal:93087, pays:France] 
[02/09/2019 22:23:16] - FATAL - fr.exagone.Main.main(Main.java:60) - Erreur 
org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'adresse3' available
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:687) ~[spring-beans-4.3.11.RELEASE.jar:4.3.11.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1207) ~[spring-beans-4.3.11.RELEASE.jar:4.3.11.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:284) ~[spring-beans-4.3.11.RELEASE.jar:4.3.11.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197) ~[spring-beans-4.3.11.RELEASE.jar:4.3.11.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1078) ~[spring-context-4.3.11.RELEASE.jar:4.3.11.RELEASE]
	at fr.exagone.Main.main(Main.java:55) [classes/:?] 