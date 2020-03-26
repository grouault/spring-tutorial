# annotation : init et destroy
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/index.md)

## lifecycle bean
[lifecyclebean](https://howtodoinjava.com/spring-core/spring-bean-life-cycle/){:target="_blank"}

![image](https://user-images.githubusercontent.com/20648687/77641152-de451d80-6f5b-11ea-932b-43b8c695f506.png)
{:target="_blank"}

<a href="https://user-images.githubusercontent.com/20648687/77641152-de451d80-6f5b-11ea-932b-43b8c695f506.png" target="_blank">Hello, world!</a>

## Traitement post-processeurs
This post-processor includes support for the PostConstruct and PreDestroy annotations - as init annotation and destroy annotation, respectively - through inheriting from InitDestroyAnnotationBeanPostProcessor with pre-configured annotation types.

XML


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
