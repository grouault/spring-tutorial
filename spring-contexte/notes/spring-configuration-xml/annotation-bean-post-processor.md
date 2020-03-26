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
