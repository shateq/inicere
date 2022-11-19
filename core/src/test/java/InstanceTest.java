import org.junit.jupiter.api.Test;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InstanceTest {
    @Test
    void createNew() throws IOException {
        Path path = Paths.get("src/test/resources/test.toml");
        System.out.println("path = "+path);
        // serialize toml using MutableTomlTable that is private passing entryKeySet

        TomlParseResult result = Toml.parse(path);
        System.out.println("table.key = "+ result.get("table.key"));
        System.out.println(result.toToml());
        //assertDoesNotThrow(() -> inicere.get("k"));
    }
}
