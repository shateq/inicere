package shateq.inicere.impl;

import org.jetbrains.annotations.NotNull;
import shateq.inicere.api.Configuration;
import shateq.inicere.api.FunctionalAction;
import shateq.inicere.api.Worker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static shateq.inicere.api.ActionBreed.*;

/**
 * Inicere worker.
 */
public class Inicere implements Configuration, Worker {
    // TODO: is config field needed?
    private final Inicere config;

    private FunctionalAction subscribe;
    private Object bound; // Accessible object
    private File file;

    public boolean readonly;

    /**
     * @param file File path
     */
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
        act(new Action(GET, file.getName(), key));//GET action
        return config.get(key);
    }

    @Override
    public <S> S set(String key, S value) throws IOException {
        throwIfReadonly();
        act(new Action(SET, file.getName(), key));//SET action
        return config.set(key, value);
    }

    @Override
    public <D> D delete(String key) throws IOException {
        throwIfReadonly();
        act(new Action(DELETE, file.getName(), key));//DEL action
        return config.delete(key);
    }

    @Override
    public String kill() throws IOException {
        throwIfReadonly();
        throwIfNoFile();
        if (file.delete()) {
            act(new Action(KILL, file.getName(), null));//KILL action
            return file.getName();
        }
        throw new IOException("File could not be deleted.");
    }

    @Override
    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    @Override
    public void act(Action thing) {
        this.subscribe.proceed(thing);
    }

    @Override
    public void subscribe(@NotNull FunctionalAction action) {
        this.subscribe = action;
    }

    // PROTECTED HELPERS
    protected void throwIfReadonly() throws IOException {
        if (readonly || !file.canWrite()) throw new IOException("Write access denied.");
    }

    protected void throwIfUnreadable() throws IOException {
        if (!file.canRead()) throw new IOException("Cannot read the file");
    }

    // TODO: resolve if no file
    protected void throwIfNoFile() throws IOException {
        if (file == null) throw new IOException("File is unreadable.");
    }

    protected void throwIfNoBound() throws IOException {
        if (bound == null) throw new IOException("File template not provided.");
    }
}
