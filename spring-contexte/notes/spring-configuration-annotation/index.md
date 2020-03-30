# liste des @annotations
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/INDEX.md)
[prec](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/configuration.annotation.md)

Configuration par annotation
```
@autowired
Stéréotype: @Component / @Repository / @Service / @Controller / @RestController
@Scope
@Qualifier
@Value
@Required
@Lazy
```

Les composants sont détectés au démarrage de l'application
XML:
```
<context:component-scan base-package="base.package" />
```
Java:
```
@ComponentScan("com.banque")
```

## @Autowired


## [properties](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/properties.md)
