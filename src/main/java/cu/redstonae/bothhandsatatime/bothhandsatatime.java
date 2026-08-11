package cu.redstonae.bothhandsatatime;

import net.fabricmc.api.ClientModInitializer;
import static cu.redstonae.bothhandsatatime.config.config.setupConfig;

public class bothhandsatatime implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        setupConfig();
    }
}
