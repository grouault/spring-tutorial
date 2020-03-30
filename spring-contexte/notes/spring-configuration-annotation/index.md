# liste des @annotations
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/INDEX.md)
[prec](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/configuration.annotation.md)

Configuration par annotation
```
@autowired
Stéréotype: @Component / @Repository / @Service / @Controller / @RestController / @Configuration
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

Cycle de vie annotation:
```
1- Détection des beans : Scan des annotations @Component
2- BeanFactoryPostProcessor
3- Instanciation des beans et injection des dépendances
4- BeanPostProcessor
```

## @Autowired

## @Qualifier

