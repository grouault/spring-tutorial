# Transaction
## 1. Propriétés ACID des transactions
* Atomicité (rollback)
* Cohérence
* Isolation ()
* Durabilité (commit)

## 2. Problèmes consécutifs à une concurrence sans controle
* Défaut de serialisabilité
  * les mises à jours perdues
  * tuples fantômes

* Défaut de recouvrabilité
  
  Une exécution recouvrable introduit un conflit insolvable entre les commit effectué par une T et les rollback d'une autre.
  
  * lecture sâle
  T2 lit une mise à jour de T1 alors que T1 n'a pas validé. La lecture sâle transmet un tuple modifié par T1 à T2.
  T2 peut modifier et valider. Quid d'un rollback de T1?
  T1 devient dépendante de T2
  
  
  * écriture sâle

## 3. Les niveaux d'isolation
