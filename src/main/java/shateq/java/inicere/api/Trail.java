package shateq.java.inicere.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Entry key for config file.
 */
public class Trail {
    private final String index;
    @Nullable
    private final String[] paths;
    public Trail(String index, String ...path) {
        this.index = index;
        this.paths = path;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull Trail of(String index, String ...path) {
        return new Trail(index, path);
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
