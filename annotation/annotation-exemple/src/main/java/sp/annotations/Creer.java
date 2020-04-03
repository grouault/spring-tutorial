package sp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Target : l'endroit où l'on peut mettre l'annotation
// TYPE : Classes, interfaces
// CONSTRUCTOR : sur le constructeur
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, })
// La Retention : A quel moment de son cycle de vie nous pouvons utiliser cette annotation
// code source, compilation ou execution
@Retention(RetentionPolicy.RUNTIME)
public @interface Creer {

}
