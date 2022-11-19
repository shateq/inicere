package shateq.inicere.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import shateq.inicere.annotate.Comment;
import shateq.inicere.annotate.DataSection;
import shateq.inicere.annotate.Element;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Annotations {
    protected static @NotNull Map<Element, Field> getElements(@NotNull Object o) {
        Map<Element, Field> elements = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Element.class)) {
                Element a = f.getAnnotation(Element.class);
                elements.put(a, f);
            }
        }
        return elements;
    }

    protected static Field @NotNull [] filterElementFields(@NotNull Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        return (Field[]) Arrays.stream(fields).filter(field -> field.isAnnotationPresent(Element.class)).toArray();
    }

    protected static @Nullable Comment getComment(@NotNull Field f) {
        return f.getAnnotation(Comment.class);
    }

    protected static @Nullable Element getElement(@NotNull Field f) {
        return f.getAnnotation(Element.class);
    }

    protected static @Nullable DataSection getDataSection(@NotNull Object o) {
        return o.getClass().getAnnotation(DataSection.class);
    }
    /*public void defaults(Object o) throws IllegalAccessException {
        Map<Element, Field> elements = getElements(o);
        final CommentedFileConfig c = toml();
        c.load();

        for(Element e : elements.keySet()) {
            String key = e.value();
            Field field = elements.get(e);
            field.setAccessible(true);

            if(key.isEmpty()) {
                key = Key.keyedName(field.getName());
            }

            c.set(key, field.get(o));
        }
        c.close();
    }*/
}
