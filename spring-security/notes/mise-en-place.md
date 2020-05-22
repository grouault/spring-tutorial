## Architecture - mise en place

### Configuration XML
Dans un contexte J2ee deux choses à faire à minima:
* WEB.xml ajouter un listener pour que le fichiers Spring soient chargés dans le context J2ee
```
  <!-- Chargement du Spring -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath*:spring/*-context.xml</param-value>
  </context-param>

  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  
  <!-- Spring Security -->
  <filter>
  	<filter-name>springSecurityFilterChain</filter-name>
  	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter> 
   
  <!-- // toutes les urls --> 
  <filter-mapping>
  	<filter-name>springSecurityFilterChain</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping> 
```

* Réaliser un fichier de configuration dédié au Spring-Security

