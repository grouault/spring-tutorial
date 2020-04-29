# spring-transaction
[menu](https://github.com/grouault/spring-tutorial/blob/master/spring-data-access/transaction/readme.md)

## Implementation Spring framework declarative transaction

La gestion des transactions est permise/activé par le proxy AOP. La configuration de la transaction se faite par l'advice transactionnel (définition des metadatas). Le proxy utilise l'interceptor / advisor en conjonction avec l'implémentation du PlatformTransactionManager pour piloter les transactions au niveau de chaque invocation de méthode.

![image](https://user-images.githubusercontent.com/20648687/80582769-94929b80-8a0f-11ea-9224-b7be7ba2d35c.png)

## declarative transaction
<tx:advice> : greffon qui permet de décrirer le comportement transactionnel souhaité à l'aide de <tx:attribute>. Le transaction-manager doit être précisé sur le greffon
