package shateq.java.inicere;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import shateq.java.inicere.api.Action;
import shateq.java.inicere.api.Configuration;
import shateq.java.inicere.api.DefaultAction;
import shateq.java.inicere.api.Key;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Inicere worker.
 */
public class Inicere {
    private final Configuration config;
    private final Path path;
    private DefaultAction defaultAction;
    private DefaultAction subscription;
    // Accessible object
    private Object bound;

    public Inicere(String fileName) {
        this(getConfigPath(fileName));
    }

    public Inicere(Path path) {
        this(path, null);
    }

    public Inicere(Path path, Object obj) {
        this.path = path;
        this.config = new Configuration(this.path);
        this.bound = obj;
        lookup();
    }

//    public Inicere(Object obj) {
//        this.bound = obj;
//        this.path = obj.getClass().getSimpleName();
//        this.config = new Configuration(this.path);
//    }

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
     * Used to access "bound" object, that has to be private.
     * @return Bound object, null (if you didn't bind object to Inicere).
     */
    public Object bound() {
        return this.bound;
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
        act(new Action(Action.Type.DEFAULT), true);
        try {
            config.defaults(bound);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // TODO: fix up the logic
    private void lookup() {
        if(bound == null) return;
        try {
            config.bindValues(bound);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Making class read-only may not be reverted.
     * @return Read-only worker class
     */
    public Inicere makePermanent() {
        config.setReadOnly(true);
        return this;
    }

    public boolean isPermanent() {
        return config.isReadOnly();
    }

    public <R> R get(String key) {
        R value = config.read(key);
        // Event
        act(new Action(key, value, Action.Type.GET), false);
        return value;
    }

    public <S> S set(String key, S value) {
        try {
            config.write(key, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Event
        act(new Action(key, value, Action.Type.SET), false);
        return value;
    }

    public <T> T remove(String key) {
        T removed = config.read(key);
        // Event
        act(new Action(key, removed, Action.Type.DELETE), false);
        return removed;
    }

    public void kill() {
        // Action handling
        act(new Action(Action.Type.KILL), true);
        try {
            config.eliminate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <R> R get(@NotNull Key key) {
        return get(key.toString());
    }

    public <S> S set(@NotNull Key key, S value) {
        return set(key.toString(), value);
    }

    public <T> T remove(@NotNull Key key) {
        return remove(key.toString());
    }

    private static @NotNull Path getConfigPath(@NotNull String file) {
        return Path.of(FabricLoader.getInstance().getConfigDir().toString(), file);
    }

    /**
     * Inicere builder
     * Ability to tweak worker class or use predefined settings.
     */
    public static class Phi {
        protected boolean permanent = false;
        protected Path file;
        protected Object object = null;
        protected DefaultAction action = null;

        @Contract("_ -> new")
        public static Inicere permanentFile(Path path) {
            return new Inicere(path).makePermanent();
        }

        public static Inicere permanentBoundFile(Path path, Object object) {
            return new Inicere(path, object).makePermanent();
        }

        public Inicere build() {
            Inicere inicere = new Inicere(file);
            if(permanent) inicere.makePermanent();
            if(object != null) inicere.bind(object);
            if(action != null) inicere.defaultAction(action);
            return inicere;
        }

        /**
         * File is required for library to work.
         * @param path Path to operational file.
         */
        public Phi setFile(Path path) {
            this.file = path;
            return this;
        }

        public Phi setObject(Object object) {
            this.object = object;
            return this;
        }

        public Phi setDefaultAction(DefaultAction action) {
            this.action = action;
            return this;
        }

        public Phi makePermanent() {
            this.permanent = true;
            return this;
        }
    }
}
