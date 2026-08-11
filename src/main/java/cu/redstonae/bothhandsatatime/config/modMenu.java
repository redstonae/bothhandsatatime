package cu.redstonae.bothhandsatatime.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class modMenu implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return theShitThatYouGetToSeeOnScreen::new;
    }
}
