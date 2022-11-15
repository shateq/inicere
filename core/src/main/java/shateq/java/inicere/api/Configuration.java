package shateq.java.inicere.api;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import shateq.java.inicere.annotation.Comment;
import shateq.java.inicere.annotation.DataSection;
import shateq.java.inicere.annotation.Element;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Operations on TOML files.
 */
public class Configuration {
    private boolean readOnly = false;
    private final Path path;

    /**
     * @param path Should be a path to file with .toml extension.
     */
    public Configuration(Path path) {
        this.path = path;
        prepareAbsent();
    }

    private CommentedFileConfig toml() {
        return CommentedFileConfig.builder(path).autosave().charset(StandardCharsets.UTF_8).build();
    }

    private void prepareAbsent() {
        try {
            if(!Files.exists(path)) {
                Files.createFile(path);
            }
            if(!viable()) {
                final Set<PosixFilePermission> permissions = Set.of(PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_READ, PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ);
                Files.setPosixFilePermissions(path, permissions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void defaults(Object o) throws IllegalAccessException {
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
    }

    public void bindValues(Object o) throws IllegalAccessException {
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

            field.set(o, c.get(key));
        }
        c.close();
    }

    public void write(@NotNull String key, Object value) throws IOException {
        readOnlyException();
        final CommentedFileConfig c = toml();
        c.load();
        c.set(key, value);
        c.close();
    }

    @Nullable
    public <T> T read(@NotNull String key) {
        final CommentedFileConfig c = toml();
        c.load();
        return c.get(key);
    }

    public <T> T retract(@NotNull String key) throws IOException {
        readOnlyException();
        final CommentedFileConfig c = toml();
        c.load();
        T retracted = c.get(key);
        c.remove(key);
        return retracted;
    }

    /**
     * Remove current configuration file.
     */
    public void eliminate() throws IOException {
        readOnlyException();
        Files.deleteIfExists(path);
    }

    private void readOnlyException() throws IOException {
        if(readOnly) {
            throw new IOException("Configuration file is read-only!");
        }
    }

    public boolean viable() {
        return Files.isReadable(path) && Files.isWritable(path);
    }
    // More helpers
    protected static Map<Element, Field> getElements(@NotNull Object o) {
        Map<Element, Field> elements = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Element.class)) {
                Element a = f.getAnnotation(Element.class);
                elements.put(a, f);
            }
        }
        return elements;
    }

    protected static Field[] filterElementFields(@NotNull Object o) {
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
}
