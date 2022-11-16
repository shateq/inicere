package shateq.inicere.fabric.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import shateq.inicere.impl.Inicere;
import shateq.inicere.annotate.Comment;
import shateq.inicere.annotate.Element;

import java.nio.file.Path;

public class TestMod implements ClientModInitializer {
    @Element
    @Comment("Some comment")
    String toBeSaved = "Some message";
    @Element("integer.path")
    int hahInt = 2;
    @Element("doubles.path")
    double doubles$usingTrail = 6.0;

    @Override
    public void onInitializeClient() {
        Inicere inicere = new Inicere("config.toml");
        System.out.println("Instantiated");
    }
    // TODO: use fabric inicere wrapper
    static Path configPath(String path) {
        return Path.of(FabricLoader.getInstance().getConfigDir().toString(), path);
    }
}
