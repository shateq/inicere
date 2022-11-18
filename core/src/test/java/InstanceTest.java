import org.junit.jupiter.api.Test;
import shateq.inicere.impl.Inicere;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InstanceTest {
    @Test
    void createNew() throws URISyntaxException, IOException {
        ClassLoader loader = getClass().getClassLoader();
        File resource = new File(loader.getResource("test.toml").getFile());

        System.out.println(resource.getAbsolutePath());
        Inicere inicere = new Inicere(new File(resource.getAbsolutePath()));

        assertDoesNotThrow(() -> inicere.get("k"));
    }
}
