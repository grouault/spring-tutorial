# creation d'une map
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/balise-attributs-xml/configuration-xml.md)
    
    <bean id="mapVilles" class="java.util.HashMap">
    <constructor-arg>
      <map>
        <entry key="Rennes"><ref bean="client3"/></entry>
        <entry key="Paris"><ref bean="client1"/></entry>
        <entry key="Versailles"><ref bean="client2"/></entry>
      </map>	
      </constructor-arg>
    </bean>
