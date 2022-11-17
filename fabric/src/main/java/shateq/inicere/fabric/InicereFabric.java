package shateq.inicere.fabric;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;

public class InicereFabric {
    public static @NotNull File getConfigPath(String filename) {
        return Path.of(FabricLoader.getInstance().getConfigDir().toString(), filename).toFile();
    }
}
