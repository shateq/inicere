package shateq.inicere.impl;

import org.jetbrains.annotations.NotNull;
import shateq.inicere.api.Configuration;
import shateq.inicere.api.DefaultAction;
import shateq.inicere.api.Worker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Inicere worker.
 */
public class Inicere implements Configuration, Worker {
    // TODO: is config field needed?
    private final Inicere config;
    private DefaultAction defaultAction;
    private DefaultAction subscription;

    private Object bound; // Accessible object
    private File file;

    public boolean readonly;

    public Inicere(File file) {
        this.file = file;
        this.config = new Inicere(file);
    }

    public Inicere(@NotNull Path path) {
        this(path.toFile());
    }

    public Inicere(File file, Object obj) {
        this(file);
        this.bound = obj;
    }

    @Override
    public Inicere setFile(File file) {
        this.file = file;
        return this;
    }

    @Override
    public File file() {
        return file;
    }

    @Override
    public Inicere bind(Object obj) {
        this.bound = obj;
        return this;
    }

    @Override
    public Object bound() {
        return bound;
    }

    @Override
    public <R> R get(String key) {
        return config.get(key);
    }

    @Override
    public <S> S set(String key, S value) throws IOException {
        throwIfReadonly();
        return config.set(key, value);
    }

    @Override
    public <D> D delete(String key) throws IOException {
        throwIfReadonly();
        return config.delete(key);
    }

    @Override
    public String kill() throws IOException {
        throwIfReadonly();
        throwIfNoFile();
        if (file.delete()) {
            return file.getName();
        }
        throw new IOException("File could not be deleted.");
    }

    @Override
    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    //TODO: should be in Worker interface

    /**
     * Initialize acton conditions.
     *
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
     *
     * @param action Your lambda.
     */
    public Inicere defaultAction(@NotNull DefaultAction action) {
        this.defaultAction = action;
        return this;
    }

    /**
     * Fired before any action done with configuration except resetting it, see {@link #defaultAction(DefaultAction)}
     *
     * @param action Your lambda.
     */
    public Inicere subscribe(@NotNull DefaultAction action) {
        this.subscription = action;
        return this;
    }

    /*
    Helpers
     */
    private void throwIfReadonly() throws IOException {
        if (readonly) throw new IOException("Write access denied.");
    }

    // TODO: resolve if no file
    private void throwIfNoFile() throws IOException {
        if (file == null) throw new IOException("File is null.");
    }

    private void throwIfNoBound() throws IOException {
        if (bound == null) throw new IOException("File scaffolding not provided.");
    }
}
