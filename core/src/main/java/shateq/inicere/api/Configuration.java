package shateq.inicere.api;

import shateq.inicere.impl.Inicere;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public interface Configuration {
    Charset charset = StandardCharsets.UTF_8;

    Inicere setFile(Path file);

    Path file();

    Inicere bind(Object obj); //TODO: should return configuration

    Object bound();

    <R> R get(String key) throws IOException;

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
