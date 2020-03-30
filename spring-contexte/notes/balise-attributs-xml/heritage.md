## Héritage

[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/balise-attributs-xml/configuration-xml.md)

### Héritage de configuration &lt;bean&gt;
    <!-- Declaration de notre configuration service parent -->
	  <bean id="abstractService" class="com.banque.service.impl.AbstractService" abstract="true"/>	

    <!-- Declaration de nos services et mise en place des liens avec les DAO -->
    <bean id="authentificationService" class="com.banque.service.impl.AuthentificationService" parent="abstractService">
      <constructor-arg name="utilisateurDAO" ref="utilisateurDAO"></constructor-arg>
    </bean>

### Héritage de configuration abstract
    <!-- Declaration de notre configuration de DAO parent -->
    <bean id="abstractDao" class="com.banque.dao.impl.AbstractDAO" abstract="true">
      <property name="url" value="${bdd.url}"></property>
		  <property name="driver" value="${bdd.driver}"></property>
		  <property name="login" value="${bdd.login}"></property>
		  <property name="pwd" value="${bdd.password}"></property>
	  </bean>
    
    <!-- Declaration de nos DAO enfants -->
    <bean id="compteDAO" class="com.banque.dao.impl.CompteDAO" parent="abstractDao">
    </bean>
