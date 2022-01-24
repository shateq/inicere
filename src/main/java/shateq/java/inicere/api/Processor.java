package shateq.java.inicere.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import shateq.java.inicere.annotation.Comment;
import shateq.java.inicere.annotation.DataSection;
import shateq.java.inicere.annotation.Element;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Represents annotation processor.
 */
public class Processor {
    public Processor() {}

    protected @Nullable DataSection getDataSection(@NotNull Object o) {
        return o.getClass().getAnnotation(DataSection.class);
    }

    protected @Nullable Comment getComment(@NotNull Field f) {
        return f.getAnnotation(Comment.class);
    }

    protected @Nullable Element getElement(@NotNull Field f) {
        return f.getAnnotation(Element.class);
    }

    protected Field[] filterElementFields(@NotNull Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        return (Field[]) Arrays.stream(fields).filter(field -> field.isAnnotationPresent(Element.class)).toArray();
    }

    protected boolean isImmutable(@NotNull Element e) {
        return e.immutable();
    }

    protected HashMap<Element, Field> getElements(@NotNull Object o) {
        HashMap<Element, Field> elements = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Element.class)) {
                Element a = f.getAnnotation(Element.class);
                elements.put(a, f);
            }
        }
        return elements;
    }
}
