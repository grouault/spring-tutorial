## Spring-security

Définition:

### Spring-security-core

### Core Services

* AuthenticationManager
API qui définit comment les filtres de spring-security réalise l'authentification.
Retourne un objet `Authentication` qui est mis dans le context `SecurityContextHolder`.


* ProviderManager
L'implémetation par défaut de l'interface AuthenticationManager.


* AuthenticationProviders
