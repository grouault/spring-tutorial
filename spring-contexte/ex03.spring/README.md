Projet simple permettant d'instancier des composants dans le conteneur léger

# Exemple de création de beans:
==> Exemple d'implémentation d'une liste en Spring

# Code générer par l'exécution:
[03/09/2019 21:52:11] - DEBUG - fr.exagone.Main.main(Main.java:30) - -- Debut --  
[03/09/2019 21:52:12] -  INFO - org.springframework.context.support.AbstractApplicationContext.prepareRefresh(AbstractApplicationContext.java:583) - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@20140db9: startup date [Tue Sep 03 21:52:12 CEST 2019]; root of context hierarchy 
[03/09/2019 21:52:12] -  INFO - org.springframework.beans.factory.xml.XmlBeanDefinitionReader.loadBeanDefinitions(XmlBeanDefinitionReader.java:317) - Loading XML bean definitions from class path resource [spring/mesBeans.xml] 
[03/09/2019 21:53:25] - DEBUG - fr.exagone.Main.main(Main.java:39) - client 1: nom:lunz, prenom:Axel, adresse[[rue:rue Roland Garros, ville:Athis, codePostal:91234, pays:France], [rue:rue des lilas, ville:Pré-Saint-Gervais, codePostal:93087, pays:France], [rue:rue du tennis, ville:Loisirs, codePostal:10000, pays:Pays des rêves]] 
[03/09/2019 21:53:25] -  INFO - org.springframework.context.support.AbstractApplicationContext.doClose(AbstractApplicationContext.java:984) - Closing org.springframework.context.support.ClassPathXmlApplicationContext@20140db9: startup date [Tue Sep 03 21:52:12 CEST 2019]; root of context hierarchy 
[03/09/2019 21:53:25] - DEBUG - fr.exagone.Main.main(Main.java:58) - -- Fin --  
