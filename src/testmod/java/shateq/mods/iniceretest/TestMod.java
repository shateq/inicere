package shateq.mods.iniceretest;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import shateq.java.inicere.Inicere;
import shateq.java.inicere.annotation.Comment;
import shateq.java.inicere.annotation.Element;

import java.nio.file.Path;

public class TestMod implements ClientModInitializer {
    @Element @Comment("Some comment")
    String toBeSaved = "Some message";
    @Element("integer.path")
    int hahInt = 2;
    @Element("doubles.path")
    double doubles$usingTrail = 6.0;

    @Override
    public void onInitializeClient() {
        Inicere hah = Inicere.Phi.justKeyValue(configPath("config.toml"));
        Inicere builder = new Inicere.Phi()
                .setFile(FabricLoader.getInstance().getConfigDir().resolve("file-toml.toml"))
                .build();

        System.out.println(hah.bound());
    }

    static Path configPath(String path) {
        return Path.of(FabricLoader.getInstance().getConfigDir().toString(), path);
    }
}
