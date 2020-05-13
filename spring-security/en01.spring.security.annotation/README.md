## Note

- Configuration de Spring-Security par annotation
- Pas de web.xml, ni de fichier de configuration de security-context.xml
- Utilisation de la classe: AbstractSecurityWebApplicationInitializer

* Nous ne sommes pas en Spring MVC ici, mais nous faisons usage du Spring

## HOW-TO

Le projet est à lancer par maven:

Pour lancer le projet:
mvn -Dmaven.tomcat.port=8181  tomcat7:run-war

Installer le plugin:

``
  <plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
  </plugin>
``
