## Spring transaction template
[retour](https://github.com/grouault/spring-tutorial/blob/master/spring-data-access/transaction/notes/spring-transaction.md)

### urls
* [doc.spring](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/transaction/support/TransactionTemplate.html)

### Problématique
Un bloc de code d'une méthode métier, non l'intégralité du corps de la méthode a le comportement suivant
* démarrer une nouvelle transaction
* valider la transaction après exécution du code réussie
* annuler la transaction en cas de réception d'une exceptions dans le code

### Solution
Utiliser un template de transaction `TransactionTemplate` qui va gérer pour nous le code standard de gestion de la transaction.
Le code est à encapsuler dans une classe de rappel qui implémente l'interface `TransactionCallBack` et la passer au template de transaction pour son exécution.

### Explication
Un template de transaction est créé pour un gestionnaire de transactions, tout comme un template JDBC est créé pour une source de données.
Le template exécute un objet de rappel qui implémente l'interface de rappel par une classe séparéee ou interne.
Les arguments de la méthode dans lequel est définit le TransactionTemplate doivent être déclaré final.

### Notes
#### portée de la transaction
* Le code transactionnel démarre et arrête au début et à la fin de la classe de rappel.
#### gestion Exception
* Si une exception, type RuntimeException est déclenché dans la classe de rappel, la Tx est annulé (Rollback) et remonte dans la pile d'appel. 

1- Dans le cas d'une transaction qui n'opére pas dans le même contexte transactionnel que la transaction parente, si l'exception n'est pas gérée, la transaction englobante est quand même annulée. En gérant l'exception, la transaction englobante ira à son terme.

2-  Dans le cas d'une transaction qui opére dans le même contexte transactionnel que la transaction parente, que l'exception soit gérée ou non, la transaction englobante échouera. Une exception de type `UnexpectedRollbackException` sera alors lancée.

Traitement:
L'idée est donc de gérée l'exception dans un block 'try catch' . Ce dernier (ou bloque finally) peut exécuter un code transactionnel. Ce dernier se fera dans le contexte transactionnelle englobant. Si ce dernier doit se faire dans son propre contexte transactionne, un transaction template peut être utilisé.


