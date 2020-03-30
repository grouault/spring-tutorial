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

## @Qualifier / @Primary
* pour lever les ambiguités quand plusieurs beans de même type sont sélectionnables par Spring.
* bonnes pratiques: si possible ne pas préciser les noms des composants / il est rare de définir plusieurs implémentations pour un même bean au sein d'un contexte applicatif.


## traitement post-processeurs
[voir](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/annotation-bean-post-processor.md)

## properties
[voir](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/properties.md)

