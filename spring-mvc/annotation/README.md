## Spring MVC et les annotations
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-mvc/README.md)

### Idées :
* privilégié le modèle POJO (annoter les classse POJOs)
* Alléger la configuration XML

### Le contrôleur : @Controller
* pas d'héritage de classe/interface spécifique
* pas de méthode spécifique (formBacking, referencedData ...) mais affichage et soummission formulaire dans le même controller
* pas de configuation XML, véification à la compilation...
* Déclaration, instanciation etinjection de dépendance : `@Controller`, `@RequestMapping`, `@Autowired` 
* Déclaration : fichier de contexte Spring du DispatcherServlet

```
<mvc:annotation-driven>
<context:component-scan base-package="fr.exagone.web" />`
```

### Le view resolver
* a pour objectif de simplifier vos chemins d'URL dans le code
* Comme à l'ancienne, le faire en déclartion XML.

### ModelAndView
Contient:
* la vue (identifiant de vue)
* le modèle (ensembles des informations à afficher)

`Spring MVC place automatiquement la Map dans le contexte appoprié : request par défaut`


### Signature des méthodes n'est pas figée
##### En paramètres : Request, Response et Session de l'API Servlet

En paramètre |  ...
 ---: | :--- 
@PathVariable | récupère une partie de l'url et la place dans le paramètre annoté 
@RequestParam | récupère un paramètre de requête web (GET ou POST)
@RequestHeader | récèpère un header d'une reqête web
@RequestBody | accède au corps de la requête
@ModelAttribute | * classe contenant le modèle objet et permet son enrichissement avant transmission à la vue * permet de construire un bean qui sera automatiqument contruit par Spring * faire le lien entre un formulaire web et le code
ModelMap | Modèle. On place dedans des informations, ce que l'on veut. Par défaut tout va dans le scope Request
BindingResult | ...

##### En retour 
En retour |  ...
 ---: | :--- 
ModelAndView | encapsule le nom logique de la vue et son modèle
Model | Encapsule le modèle. Retour sur la vue courante.
View | Retourne la vue préparée par l'application: Exemple : retour de documents générés.
String | Fournit le nom logique

##### Exemple de définition de méthode 

### @RequestMapping

### @RequestParam

### Gestion des vues

### Gestion du modèle
##### Scope Session
##### Scope Application

### Gestion des JSPs

### Gestion des erreurs

### Gestion de l'internationnalisation

### Validation JSR 303 - Annotation

### Validation JSR 3030 - Spring
