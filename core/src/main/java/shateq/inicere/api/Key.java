package shateq.inicere.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Entry key for config file.
 */
public class Key {
    private final String[] paths;
    public Key(String ...paths) {
        this.paths = paths;
    }

    public static @NotNull Key of(String ...paths) {
        return new Key(paths);
    }

    @Contract(pure = true)
    public static @NotNull String keyedName(@NotNull String s) {
        return s.replace("$", ".").trim();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(paths[0]);
        for(var i = 1; i < paths.length; i++) {
            if(!paths[i].trim().isEmpty()) {
                s.append(".").append(paths[i]);
            }
        }
        return s.toString().trim();
    }
}
