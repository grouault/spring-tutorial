# Liser moi

## Description
1. Utilisation du JdbcTemplate sans le Transactionnel

## keywords
* [JdbcTemplate](https://docs.spring.io/autorepo/docs/spring-framework/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html) / DataSource

## DataSource
[org.apache.commons.dbcp2.BasicDataSource](https://commons.apache.org/proper/commons-dbcp/apidocs/org/apache/commons/dbcp2/BasicDataSource.html)
[org.springframework.jdbc.datasource.DriverManagerDataSource](https://docs.spring.io/autorepo/docs/spring-framework/current/javadoc-api/org/springframework/jdbc/datasource/DriverManagerDataSource.html)


# prérequis
1. docker : docker-compose up

## config
```
mvn
```
1. renommer le fichier database.default en database.properties

2. changer les valeurs

3. lancer docker-compose up

4. dans un navigateur taper:
```
localhost:8080
```
