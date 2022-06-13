## Connection H2

http://localhost:8086/h2-console/login.do?jsessionid=7982f2d9dc73d63a8b4bd1bb47f42620




## CommandeLineRunner
CommandeLineRunner :
C'est une interface à implémenter qui contient la méthode run
retourne une fonction qui va exécuter un traitement
Ce traitement s'exécute au démarrage

C'est mieux que d'implémenter l'interface.
Pkoi ? Car on peut injecter les Repos que l'on veut dans les paramètres
Plus besoin de les déclarer en attribut avec Autowired

## lombok
* seul le plugin dans le pom.xml permet la configuration pour intellij

## JSon
* voir pour le mapping jackson.
mapping cyclique.