# spring-transaction
[menu](https://github.com/grouault/spring-tutorial/blob/master/spring-data-access/transaction/readme.md)

Implementation Spring framework declarative transaction

La gestion des transactions est permise/activé par le proxy AOP. La configuration de la transaction se faite par l'advice transactionnel (définition des metadatas). Le proxy utilise l'interceptor / advisor en conjonction avec l'implémentation du PlatformTransactionManager pour piloter les transactions au niveau de chaque invocation de méthode.
![image](https://github.com/grouault/spring-tutorial/issues/3)
