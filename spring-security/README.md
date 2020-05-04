## Spring-security

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
