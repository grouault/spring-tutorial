# [list,set,vector ... ](https://github.com/grouault/spring-tutorial/blob/master/spring-contexte/notes/balise-attributs-xml/configuration-xml.md)

Note: il faut utiliser un constructeur pour préciser l'implémentation de la la liste : list, set, vector

adresses : liste de champs de type String


     <!-- 
     property name="adresses": simple string -->
     <property name="adresses">
       <list>
         <value>1 rue du midi</value>
         <value>é rue de Paris</value>
       </list>
     </property>

adresses : liste de bean de type Adresse
==> construction des objets directement dans la liste


    <!-- property name="adresses" -->
    <property name="adresses">
      <list value-type="fr.exagone.beans.Adresse">
        <bean class="fr.exagone.beans.Adresse">
          <constructor-arg index="0" value="78000" />
          <constructor-arg index="1" value="18 rue de la Reine" />
          <constructor-arg index="2" value="Versailles" />
          <constructor-arg index="3" value="France" />
         </bean>
      </list>
    </property>

adresses : liste de bean de type Adresse
* construction des objets directement dans la liste
* utilistation d'un constructeur

    <!-- property name="adresses" -->
    <property name="adresses">
    <bean class="java.util.ArrayList">
      <constructor-arg>
        <list value-type="fr.exagone.beans.Adresse">
          <bean class="fr.exagone.beans.Adresse">
             <constructor-arg index="0" value="78000" />
	     <constructor-arg index="1" value="18 rue de la Reine" />
	     <constructor-arg index="2" value="Versailles" />
	     <constructor-arg index="3" value="France" />
         </bean>
        </list>
      </constructor-arg>
     </bean>
    </property>

adresses : liste de bean de type Adresse
==> la liste référence des beans déjà existant	


    <!-- par référence -->
    <bean id="adresses" class="java.util.Vector">
	  <list>
	    <ref bean="adresse1" />
		<ref bean="adresse2" />
		<ref bean="adresse3" />                
	  </list>
    </bean>
	
adresses : liste de bean de type Adresse
==> la liste référence des beans déjà existant
==> utilistation d'un constructeur	
	
    <!-- par référence -->
    <bean id="adresses" class="java.util.Vector">
	<constructor-arg>
	  <list>
	    <ref bean="adresse1" />
		<ref bean="adresse2" />
		<ref bean="adresse3" />                
	  </list>
    </constructor-arg>
    </bean>