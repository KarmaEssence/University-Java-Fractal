package utils.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation interface pour les object serialisable
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSerializable {

    /**
     * @return Le chemin pour chercher cette element
     */
    String path() default "";

    /**
     * Definie si ce sont des champs obligatoire ou non
     * @return true si c est le cas, false sinon.
     */
    boolean necessary() default true;

    /**
     * @return le type de serialisation
     */
    JsonSerializableType getType() default JsonSerializableType.Both;

}