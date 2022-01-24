package shateq.java.inicere;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import shateq.java.inicere.api.*;

import java.nio.file.Path;

/**
 * Inicere worker.
 */
public class Inicere {
    private final Configuration config;
    private final Path path;
    private Object bound;
    private DefaultAction defaultAction;
    private DefaultAction subscription;

    public Inicere(String fileName) {
        this(getConfigPath(fileName));
    }

    public Inicere(@NotNull Path path) {
        this.path = getConfigPath(path.toString());
        this.config = new Configuration(this.path);
    }

    public Inicere(Path path, Object obj) {
        this.path = path;
        this.config = new Configuration(this.path);
        this.bound = obj;
        lookup();
    }

    /* I don't know if this should stay here...
    public Inicere fileName(@NotNull String newName) {
        this.fileName = newName;
        this.config = new Configuration(getConfigPath());
        return this;
    }*/

    /**
     * @param obj Object used for searching of config entries.
     * @return Builder class with new properties.
     */
    public Inicere bind(@NotNull Object obj) {
        this.bound = obj;
        lookup();
        return this;
    }

    /**
     * Initialize acton conditions.
     * @param thing - Action context.
     * @return Modified (or not) Action object.
     */
    private Action act(Action thing, boolean defaultAction) {
        if (defaultAction) {
            this.defaultAction.proceed(thing);
            return thing;
        }
        this.subscription.proceed(thing);
        return thing;
    }

    /**
     * Function fired on generating configuration, or resetting one.
     * See also {@link #subscribe(DefaultAction)}
     * @param action Your lambda.
     */
    public Inicere defaultAction(@NotNull DefaultAction action) {
        this.defaultAction = action;
        return this;
    }

    /**
     * Fired before any action done with configuration except resetting it, see {@link #defaultAction(DefaultAction)}
     * @param action Your lambda.
     */
    public Inicere subscribe(@NotNull DefaultAction action) {
        this.subscription = action;
        return this;
    }

    public void resetToDefaults() {
        // Action handling
        act(new Action(Action.Sort.DEFAULT), true);
        try {
            config.defaults(bound);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void lookup() {
        try {
            config.bindValues(bound);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Inicere readOnly(boolean readOnly) {
        config.setReadOnly(readOnly);
        return this;
    }

    public boolean readOnly() {
        return config.isReadOnly();
    }

    public <R> R get(String key) {
        R value = config.read(key);
        // Event
        act(new Action(key, value, Action.Sort.GET), false);
        return value;
    }

    public <S> S set(String key, S value) {
        try {
            config.write(key, value);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        // Event
        act(new Action(key, value, Action.Sort.SET), false);
        return value;
    }

    public <T> T remove(String key) {
        T removed = config.read(key);
        // Event
        act(new Action(key, removed, Action.Sort.DELETE), false);
        return removed;
    }

    public void eliminate() {
        // Action handling
        act(new Action(Action.Sort.KILL), true);
        try {
            config.kill();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public <R> R get(@NotNull Trail key) {
        return get(key.toString());
    }

    public <S> S set(@NotNull Trail key, S value) {
        return set(key.toString(), value);
    }

    public <T> T remove(@NotNull Trail key) {
        return remove(key.toString());
    }

    /* TODO: Check out #defaultAction() */

    private static @NotNull Path getConfigPath(@NotNull Path path) {
        return Path.of(FabricLoader.getInstance().getConfigDir().toString(), path.toString());
    }

    private static @NotNull Path getConfigPath(@NotNull String file) {
        if(!file.endsWith(".toml")) {
            file += ".toml";
        }
        return Path.of(FabricLoader.getInstance().getConfigDir().toString(), file);
    }
}
