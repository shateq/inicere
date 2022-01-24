package shateq.java.inicere.annotation;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Key entry into config file.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Element {
    /**
     * @return Specified config key.
     */
    @NotNull String key() default "";

    /**
     * @return true - if field should be Overriden by processor.
     */
    boolean immutable() default false;
}
