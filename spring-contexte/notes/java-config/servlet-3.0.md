## Servlet 3.0
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/java-config/index.md)

### AbstractSecurityWebApplicationInitializer
* Classe qui aura la chage de charger les fichiers de configuration par
* annotation et de faire le mapping des URL.
* Le fichir web.xml n'a plus la charge de réaliser ses operations.
* Il faut être en Servlet 3.0 minimum pour utiliser cette technique.
* Security, on herite de AbstractSecurityWebApplicationInitializer
* Permet de se passer de la declaration du filtre Spring Security ainsi que du
* listener de Spring pour le chargement des fichiers de configurations.
