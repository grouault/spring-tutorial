# JdbcTemplate

[Retour](https://github.com/grouault/spring-tutorial/blob/master/spring-data-access/jdbc/README.md)

## Gestion de la connexion:
* par rapport à un projet pur Jdbc, jdbctemplate gère la connexion. 
* On supprime donc la méthode getConnexion
* Eviter de demander la connexion au JdbcTemplate

## Dao
* Toutes les méthodes n'ont pas besoin de la connexion mais y accède via jdbctemplate
* Certaines méthodes prennent en paramètre la connexion (classe anonyme), mais sont appelées par des méthode de jdbctemplate
