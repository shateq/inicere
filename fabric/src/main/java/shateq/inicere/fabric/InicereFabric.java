package shateq.inicere.fabric;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class InicereFabric {
    // TODO: make use
    private static @NotNull Path getConfigPath(@NotNull String file) {
        return Path.of(FabricLoader.getInstance().getConfigDir().toString(), file);
    }
}
