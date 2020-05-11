## Spring-security

https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-mvc-intro.html
https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/programmatic-registration.html
https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/servlet-container-initializer-example.html
https://www.logicbig.com/tutorials/spring-framework/spring-security/security-with-servlet-jsp.html
https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-servlet-container-initializer.html
https://www.logicbig.com/tutorials/spring-framework/spring-security/spring-security-components-and-configuration.html

# permissions
https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html
https://www.baeldung.com/spring-security-create-new-custom-security-expression

Définition:

### Spring-security-core

### Core Services

#### AuthenticationManager

* API qui définit comment les filtres de spring-security réalise l'authentification.
* Retourne un objet `Authentication` qui est mis dans le context `SecurityContextHolder`par le contrôleur (les filtres) qui ont invoqué l'`AutenticationManager`.


#### ProviderManager
* L'implémetation par défaut de l'interface AuthenticationManager.
* Délègue le traitement de l'authentification à une liste d'AuthenticationProviders.
* Chaque AuthenticationProvider à la possiblité de valider l'authentification ou sinon de permettre au filtre suivant de décider.
* Si aucun des Providers ne peut réaliser l'authentification, une exception 'ProviderNotFoundException' est levée.

#### AuthenticationProviders
