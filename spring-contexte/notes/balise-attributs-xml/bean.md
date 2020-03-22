# bean 
[retour]

## Traitements posts-processeurs
### bean xml: init-method / destroy-method
    <bean id="client1" class="fr.exagone.beans.Client" init-method="initialiser" destroy-method="detruire">
      <property name="nom" value="lunz" />
      <property name="prenom" value="Axel" />
      <property name="age" value="28" />
      <property name="adresses" ref="adresses" />
    </bean>
    
### objet java
    
    public void initialiser() {
      Client.LOG.info("Dans mon Init du client");
      }
	   
    public void detruire() {
		  Client.LOG.info("Dans mon Destroy du client");
    }
    
    
