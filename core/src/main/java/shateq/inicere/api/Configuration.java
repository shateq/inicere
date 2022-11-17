package shateq.inicere.api;

import shateq.inicere.impl.Inicere;

import java.io.File;
import java.io.IOException;

public interface Configuration {
    Inicere setFile(File file);

    File file();
    // TODO: should be configuration
    Inicere bind(Object obj);

    Object bound();

    <R> R get(String key);

    <S> S set(String key, S value) throws IOException;

    <D> D delete(String key) throws IOException;

    /**
     * Delete a file.
     *
     * @return Deleted filename
     */
    String kill() throws IOException; //filename

    void setReadonly(boolean readonly);
}
