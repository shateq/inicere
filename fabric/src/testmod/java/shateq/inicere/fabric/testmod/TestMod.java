package shateq.inicere.fabric.testmod;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shateq.inicere.annotate.Comment;
import shateq.inicere.annotate.Element;
import shateq.inicere.api.ActionBreed;
import shateq.inicere.fabric.InicereFabric;
import shateq.inicere.impl.Inicere;

import java.io.File;

public class TestMod implements ClientModInitializer {
    public static final Logger log = LoggerFactory.getLogger(TestMod.class);

    @Comment("Some comment")
    String toBeSaved = "Some message";
    @Element("integer.path")
    int number = 2;
    @Element("doubles.path")
    double doubles$usingTrail = 6.0;

    @Override
    public void onInitializeClient() {
        File filePath = InicereFabric.getConfigPath("testmod-config.toml");

        Inicere inicere = new Inicere(filePath);
        log.info("Instance");

        inicere.subscribe(action -> {
            if (action.type() == ActionBreed.GET) {
                log.debug("{} key updated in the file {}", action.key(), action.filename());
            }
        });
        //inicere.get("testkey");
    }
}
