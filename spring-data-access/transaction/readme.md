# Transaction
## 1. Propriétés ACID des transactions
* Atomicité (rollback)
* Cohérence
* Isolation ()
* Durabilité (commit)

## 2. Problèmes consécutifs à une concurrence sans controle
###  2.1 Défaut de serialisabilité
  * les mises à jours perdues
  * tuples fantômes

### 2.2 Défaut de recouvrabilité
  
  Une exécution recouvrable introduit un conflit insolvable entre les commit effectué par une T et les rollback d'une autre.
  
  1. Lecture sale
  
  T2 lit une mise à jour de T1 alors que T1 n'a pas validé. La lecture sâle transmet un tuple modifié par T1 à T2.
  T2 peut modifier et valider. Quid d'un rollback de T1?
  T1 devient dépendante de T2
  
  #### Annulation en cascade
  Idée : interdire T2 ayant fait une lecture sâle à partir de T1 de committer avant T1. Si Rollback de T1, on rollback T2.
  Aucun SGBD ne fait d'annulation en cascade. La solution est donc d'interdire les dirty-read.
  
  #### 2 solutions
  Soit T2 lit l'image avant de T1, qui par définition est une valeur validée
  Soit on met en attente les lectures sur des tuples en cours de modification
  
  2. Ecriture sale
  * T1 modifie un tuple, T2 modifie le même tuple sans que T1 valide
  * T1 fait un rollback, que se passe-t-il pour T2?
  * Restauration de l'image avant de T1 et on pert l'écriture de T2
  * ==> L'écriture sale efface un tuple modifié par une autre T par rétablissement de l'image avant.
  
  #### Recouvrabilité strict
  * on peut avoir des transactions sérialisables et non recouvrables et réciproquement
  * le respect des propriétés ACID des T imposent au SGBD d'assurer la sérialisabilité des T et la recouvrabilité dite stricte, sans écriture ni lecture sale
  
  
  
## 3. Les niveaux d'isolation
