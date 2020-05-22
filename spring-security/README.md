## Spring-security

https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-mvc-intro.html
https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/programmatic-registration.html
https://www.logicbig.com/tutorials/java-ee-tutorial/java-servlet/servlet-container-initializer-example.html
https://www.logicbig.com/tutorials/spring-framework/spring-security/security-with-servlet-jsp.html
https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-servlet-container-initializer.html
https://www.logicbig.com/tutorials/spring-framework/spring-security/spring-security-components-and-configuration.html

## permissions
https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html
https://www.baeldung.com/spring-security-create-new-custom-security-expression

https://www.concretepage.com/spring/spring-security/spring-security-in-memory-authentication
https://github.com/Baeldung/spring-security-registration/issues/65

## docs-spring
https://docs.spring.io/spring-security/site/docs/5.3.2.BUILD-SNAPSHOT/reference/html5/#servlet-authentication-authenticationmanager

* [form-login-1](https://www.baeldung.com/spring-security-login)
* [form-login-2](https://www.baeldung.com/java-config-spring-security)


## Process
### urls
https://docs.spring.io/spring-security/site/docs/4.2.14.RELEASE/apidocs/org/springframework/security/access/intercept/AbstractSecurityInterceptor.html
https://www.docs4dev.com/docs/en/spring-security/5.1.2.RELEASE/reference/authorization.html
https://www.docs4dev.com/docs/en/spring-security/5.1.2.RELEASE/reference/web-app-security.html#security-filter-chain


### 
*[mise-en-place](https://github.com/grouault/spring-tutorial/blob/master/spring-security/notes/mise-en-place.md)


Définition

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
