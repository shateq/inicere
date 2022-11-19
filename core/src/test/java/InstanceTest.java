import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InstanceTest {
    @Test
    void createNew() throws IOException {
        Path path = Paths.get("src/test/resources/test.toml");

        List<String> list = Files.readAllLines(path);
        for (String i :
            list) {
            System.out.println(i);
        }

        System.out.println(path.toAbsolutePath());
        //Inicere inicere = new Inicere(new File(resource.getAbsolutePath()));
        //assertDoesNotThrow(() -> inicere.get("k"));
    }
}
