# [properties](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/spring-configuration-xml/index.md)

## XML CONFIG


    <!-- PROPERTIES -->
    <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="location" value="spring/adresses.properties"/>
    </bean>
