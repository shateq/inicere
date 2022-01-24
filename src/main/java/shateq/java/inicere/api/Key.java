package shateq.java.inicere.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Entry key for config file.
 */
public class Key {
    private final String index;
    @Nullable
    private final String[] paths;
    public Key(String index, String ...path) {
        this.index = index;
        this.paths = path;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull Key of(String index, String ...path) {
        return new Key(index, path);
    }

    public boolean isEmpty() {
        return index.trim().isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(index);
        for(String entry : paths) {
            if(!entry.trim().isEmpty()) {
                s.append(".").append(entry);
            }
        }
        return s.toString();
    }
}
