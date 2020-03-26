# bean - lifcecycle - init et destroy - InitaliseBean - DisposableBean - BeanPostProcessor
==> il s'agit des traitement PostProcessor

[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/index.md)

## lifecycle bean
[lifecyclebean](https://howtodoinjava.com/spring-core/spring-bean-life-cycle/)

![image](https://user-images.githubusercontent.com/20648687/77641152-de451d80-6f5b-11ea-932b-43b8c695f506.png)

## par configuration xml : init-method / destroy-method
xml

    <bean id="question-1" class="fr.exagone.beans.SimpleQuestion" scope="prototype" 
        init-method="initialiser" destroy-method="detruire">
	<constructor-arg value="1"></constructor-arg>
	<constructor-arg value="What is java"></constructor-arg>
	<constructor-arg ref="reponse-1"></constructor-arg>
    </bean>

Java      
    
    public void initialiser() {
        SimpleQuestion.LOG.info("[SimpleQuestion]: in Init method");
    }
	
    public void detruire() {
        SimpleQuestion.LOG.info("[SimpleQuestion]: in Detruire method");
    }

## par annotation :@PostConstruct / @PreDestroy
This post-processor includes support for the PostConstruct and PreDestroy annotations - as init annotation and destroy annotation, respectively - through inheriting from InitDestroyAnnotationBeanPostProcessor with pre-configured annotation types.

Fichier de configuration spring : XML


    <bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor" />


Java:

    @PostConstruct
    public void initialize() {
        Adresse.LOG.info("init dans Adresse");
    }
	
    @PreDestroy
    public void destroy() {
        Adresse.LOG.info("destroy dans Adresse");
    }

## InitializingBean
L'interface permet à un bean d’effectuer des travaux d’initialisation après que toutes les propriétés nécessaires sur le bean ont été définies par le conteneur.

## DisposableBean
L'interface permet à un bean d’obtenir un callback lorsque le conteneur le contenant est détruit.

## singleton / prototype
Un bean ayant un scope prototype et une destroy-method (en annotation, déclaration XML ou implémentation d'interface) ne verra JAMAIS cette dernière appelée.
Tant que le prototype ne contient pas lui-même une référence à une autre ressource telle qu’une connexion à une base de données ou un objet de session, il sera ramasser par le garbage collector dès que toutes les références à l’objet auront été supprimées ou que l’objet sera hors de portée. Il n’est donc généralement pas nécessaire de détruire explicitement un bean de type prototype.

Cependant, dans le cas où une fuite de mémoire peut se produire comme décrit ci-dessus, les beans prototypes peuvent être détruits en créant un bean post-processeur, singleton dont la méthode de destruction appelle explicitement la méthode de destruction de vos beans prototypes. Parce que le post-processeur est lui-même de portée singleton, sa méthode de destruction sera invoqué par Spring.

## bean post-processor
Un post-processeur de beans permet de modifier sur mesure les nouvelles instances de beans créées par Spring Bean Factory. Si vous voulez implémenter une logique personnalisée après que le conteneur Spring ait fini d’instancier, de configurer et d’initialiser un bean, nous pouvons brancher une ou plusieurs implémentations de Beanpostprocessor.

Dans le cas de plusieurs instances Beanpostprocessor, nous pouvons contrôler l’ordre en définissant la propriété order ou implémenter l’interface Ordered.

A l'instanciation, spring cherche les composants qui sont des BeansPostProcessor, et exécute la méthode [afterPropertiesSet].
A la fermeture du contexte-spring, la méthode [destroy] est exécutée.
 
[Plus d'info](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/ex00.spring/README.md)
