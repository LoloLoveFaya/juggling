import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(Modifiees.class)
//Pour pouvoir conserver les informations de
// l'anntation on est obligé de les sauvegarder en métaannotant
@Retention(RetentionPolicy.RUNTIME)
public @interface Modifiee {

    String auteur() default "inconnu";
}
