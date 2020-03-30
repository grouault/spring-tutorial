# Liser moi
Version du projet avec une configuration Full XML. Les beans sont gérés dans les fichiers XML:
- service, dao
- chargement des properties : paramètre bdd

Note : 
La connexion n'est à la bdd n'est pas géré par Spring.
- Appelle à la méthode getConnexion() (AbstractDao)


## prérequis
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
