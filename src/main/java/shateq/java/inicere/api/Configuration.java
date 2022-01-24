package shateq.java.inicere.api;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileConfig;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import shateq.java.inicere.annotation.Element;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.Set;

/**
 * Operations on TOML files.
 */
public class Configuration extends Processor {
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
        HashMap<Element, Field> elements = getElements(o);
        final CommentedFileConfig c = toml();
        c.load();

        for(Element e : elements.keySet()) {
            String key = e.value();
            Field field = elements.get(e);
            field.setAccessible(true);

            if(key.isEmpty()) {
                key = keyedName(field.getName());
            }

            c.set(key, field.get(o));
        }
        c.close();
    }

    public void bindValues(Object o) throws IllegalAccessException {
        HashMap<Element, Field> elements = getElements(o);
        final CommentedFileConfig c = toml();
        c.load();

        for(Element e : elements.keySet()) {
            String key = e.value();
            Field field = elements.get(e);
            field.setAccessible(true);

            if(key.isEmpty()) {
                key = keyedName(field.getName());
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
        final FileConfig c = toml();
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
    public void kill() throws IOException {
        readOnlyException();
        Files.deleteIfExists(path);
    }

    private void readOnlyException() throws IOException {
        if(readOnly) {
            throw new IOException("Configuration file is read-only!");
        }
    }

    @Contract(pure = true)
    private @NotNull String keyedName(@NotNull String s) {
        return s.replace("$", ".");
    }

    public boolean viable() {
        return Files.isReadable(path) && Files.isWritable(path);
    }
}
