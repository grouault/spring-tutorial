# annotation

## CommonAnnotationBeanPostProcessor
This post-processor includes support for the PostConstruct and PreDestroy annotations - as init annotation and destroy annotation, respectively - through inheriting from InitDestroyAnnotationBeanPostProcessor with pre-configured annotation types.

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
