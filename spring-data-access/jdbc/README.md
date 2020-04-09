# JDBC

## Driver

## Connection
La classe java.sql.DriverManager fournit la méthode getConnexion().
Cette méthode renvoie un objet Connexion qui est utilisée pour effectuer les opérations sur la base.
Cette classe fournit par le driver implémente l'interface : java.sql.Connection
Principales méthodes :
* createStatement
* prepareStatement
* prepareCall
* setAutocommit / commit  /rollback
* getMetaData
* close / isClosed

## Transaction
````
connexion.setAutoCommit(false)
try{

  // instruction transaction
  ...
  
  // validation de la transaction
  connexion.commit();

} catch (SQLException ex) {

  // annulation connexion
  connexion.rollback();
  
} finally {

  connexion.close();
  
}
````
