package shateq.inicere.impl;

import org.jetbrains.annotations.NotNull;
import shateq.inicere.api.Configuration;
import shateq.inicere.api.FunctionalAction;
import shateq.inicere.api.Worker;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static shateq.inicere.api.ActionBreed.*;

/**
 * Inicere implementation, in use of java.nio
 */
public class Inicere implements Configuration, Worker {
    private final List<FunctionalAction> subscription = new ArrayList<>();
    public boolean readonly;

    private Object bound; //Accessible object
    private File file;

    /**
     * @param file File path
     */
    public Inicere(File file) {
        this.file = file;
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
    public <R> R get(String key) throws IOException {
        throwIfUnreadable();
        act(new Action(GET, file.getName(), key));//GET action
        //BufferedReader br = new BufferedReader(new FileReader(file));
        return null;
    }

    @Override
    public <S> S set(String key, S value) throws IOException {
        throwIfReadonly();
        act(new Action(SET, file.getName(), key));//SET action
        return null;
    }

    @Override
    public <D> D delete(String key) throws IOException {
        throwIfReadonly();
        act(new Action(DELETE, file.getName(), key));//DEL action
        return null;
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
        if (this.subscription.isEmpty()) return;
        this.subscription.forEach(
            action -> action.proceed(thing)
        );
    }

    @Override
    public void subscribe(@NotNull FunctionalAction action) {
        this.subscription.add(action);
    }

    @Override
    public void unsubscribe() {
        this.subscription.clear();
    }

    // PROTECTED HELPERS
    protected void throwIfReadonly() throws IOException {
        if (readonly || !file.canWrite()) throw new IOException("Write access denied.");
    }

    protected void throwIfUnreadable() throws IOException {
        if (!file.canRead()) throw new IOException("Cannot read the file");
    }

    protected void throwIfNoFile() throws IOException { //TODO: resolve if no file

        if (file == null) throw new IOException("File is unreadable.");
    }

    protected void throwIfNoBound() throws IOException {
        if (bound == null) throw new IOException("File template not provided.");
    }
}
