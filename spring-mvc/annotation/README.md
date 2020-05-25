## Spring MVC et les annotations

### Idées :
* supprimer la nécessité d'héritage
* privilégié le modèle POJO (annoter les classse POJOs)
* Alléger la configuration XML

### Le contrôleur : @Controller
* pas d'héritage de classe/interface spécifique
* pas de configuation XML
* injection de dépendance : @Autowired
* déclaration : fichier de contexte spring du DispatcherServlet

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


### Défintion Spring MVC
1 header | header 2 | 3 he
- |:-: | -:
line `1` | **1** | **_valeur_**
Line 2 | 2 | *Value*
