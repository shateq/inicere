package shateq.mods.iniceretest;

import net.fabricmc.api.ClientModInitializer;
import shateq.java.inicere.Inicere;
import shateq.java.inicere.annotation.Comment;
import shateq.java.inicere.annotation.Element;

public class TestMod implements ClientModInitializer {
    @Element @Comment("Some comment")
    String toBeSaved = "haha";
    @Element("integer.path")
    int hahInt = 2;
    @Element("doubles.path")
    double doubles$usingTrail = 6.0;

    @Override
    public void onInitializeClient() {
        Inicere hah = new Inicere("config.toml")
                .subscribe((a -> System.out.println("Key "+a.getKey()+" with value: "+a.getValue())));

        System.out.println(hah.bound());
    }
}
