package shateq.mods.iniceretest;

import net.fabricmc.api.ClientModInitializer;
import shateq.java.inicere.annotation.Comment;
import shateq.java.inicere.annotation.Element;
import shateq.java.inicere.Inicere;

import java.nio.file.Path;

public class TestMod implements ClientModInitializer {
    @Element @Comment("Some comment")
    String toBeSaved = "haha";
    @Element(key = "integer.path")
    int hahInt = 2;
    @Element(key = "doubles.path")
    double doubles$usingTrail = 6.0;

    @Override
    public void onInitializeClient() {
        Inicere hah = new Inicere("config.toml")
                .subscribe((a -> System.out.println("Key "+a.getKey()+" with value: "+a.getValue())));

        hah.set("zabawna", "wartość");
        System.out.println((String) hah.get("zabawna"));

        System.out.println(Path.of("raz", "dwa", "trzy.js"));
        System.out.println(Path.of("raz", "dwa", "trzy.js"));
        System.out.println(Path.of("raz", "dwa", "trzy.js"));
    }
}
