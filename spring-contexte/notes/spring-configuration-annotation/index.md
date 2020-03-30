# liste des @annotations
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/INDEX.md)
[prec](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/configuration.annotation.md)

Configuration par annotation
```
@autowired
Stéréotype: @Component / @Repository / @Service / @Controller / @RestController / @Configuration
@Scope
@Qualifier/@Primary
@Value
@Required
@Lazy
```

Les composants sont détectés au démarrage de l'application:
- indique la racine, les sous-package sont scannés
- les archives jars des dépendances sont prises en compte

XML:
```
<context:component-scan base-package="base.package" />
```
Java:
```
@ComponentScan("com.banque")
```

Cycle de vie annotation:
```
1- Détection des beans : Scan des annotations @Component
2- BeanFactoryPostProcessor
3- Instanciation des beans et injection des dépendances
4- BeanPostProcessor
```

## @Autowired
* injection par constructeur
* injection par méthode setXxxx
* injection par membre  / attribut

## @Qualifier / @Primary
* pour lever les ambiguités quand plusieurs beans de même type sont sélectionnables par Spring.
* bonnes pratiques: si possible ne pas préciser les noms des composants / il est rare de définir plusieurs implémentations pour un même bean au sein d'un contexte applicatif.


