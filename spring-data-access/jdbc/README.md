# JDBC

Bibliothèque d'interfaces et de classes utilisées pour accéder à une base de donéees.
Un programme JDBC envoie des requêtes à un SGBDR et exploite les résultats en Java.

## Driver JDBC et connection
Un driver est une implémentation des interfaces pour un SGBDR donné.
Pour obtenir une connexion, il faut:
* le chargement de la classe du driver par la JVM
```
Class.forName("com.mysql.jdbc.Driver")
```
* Récupération de la Connexion
```
DriverManager.getConnection("jdbc:mysql://localhost/test","login","mdp")
```

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

## prepareStatement
Méthode qui précompilent une instruction SQL paramétrée.
Elle laisse au driver JDBC, le soin de transmettre les valeurs des objets au format requis par la base de données.
Peut être utilisé plusieurs fois / concurrence / multi-thread

## Transaction
````
connexion.setAutoCommit(false);

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
connexion.setAutoCommit(true);
````
