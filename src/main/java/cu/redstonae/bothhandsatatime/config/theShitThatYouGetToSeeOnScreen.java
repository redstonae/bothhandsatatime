package cu.redstonae.bothhandsatatime.config;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.UIComponents;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static cu.redstonae.bothhandsatatime.config.config.returnConfig;

import static cu.redstonae.bothhandsatatime.config.config.setConfig;

public class theShitThatYouGetToSeeOnScreen extends BaseOwoScreen<FlowLayout> {
    public theShitThatYouGetToSeeOnScreen(Screen parent) {
        // as you can tell, i have 0 idea on what im doing. this "works" (as long as you consider "working" as booting you off to the main menu when pressing escape)
    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, UIContainers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout root) {
        root
                .surface(Surface.optionsBackground())
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.TOP);

        /*
        rootComponent.child(
                UIComponents.texture(Identifier.of("bothhandsatatime:textures/ui/triangle.png"),0,0,1024,502,1024,1024)
        );

        rootComponent.child(
                UIContainers.grid(Sizing.content(), Sizing.content(), 1, 2)
                        .child(UIComponents.texture(Identifier.of("bothhandsatatime:textures/ui/triangle.png"),0,502,814,20,1024,1024), 0, 0)
                                .child(
                            UIContainers.stack(Sizing.content(), Sizing.content())
                                    .child(UIComponents.texture(Identifier.of("bothhandsatatime:textures/ui/triangle.png"),814,502,210,20,1024,1024))
                                    .child(UIComponents.button(
                                            Text.literal("test button"),
                                            button -> { System.out.println("click"); }
                                    )), 0, 1
                                )
        );

        rootComponent.child(
                UIComponents.texture(Identifier.of("bothhandsatatime:textures/ui/triangle.png"),0,522,1024,472,1024,1024)
        );
        */
        // ^ i've spent WAY too much time on this ui that doesn't work in gui scales 3 and above. i don't have the heart to delete is

        root.child(
                UIComponents.label(
                        Text.literal(" ")
                )
        );
        root.child(
                UIComponents.label(Text.translatable("redstonae.bothhandsatatime.config.title"))
                        .shadow(true)
        );

        root.child(
                UIComponents.spacer(42)
        );

        if (returnConfig("bothHand").equals("true")) {
            root.child(
                    UIContainers.stack(Sizing.content(), Sizing.content())
                            .child(UIComponents.texture(Identifier.ofVanilla("textures/map/map_background.png"), 0, 0, 128, 128, 128, 128))
                            .child(UIComponents.texture(Identifier.of("bothhandsatatime:textures/ui/both_hands.png"), 0, 0, 132, 132, 132, 132))
                            .horizontalAlignment(HorizontalAlignment.CENTER)
                            .verticalAlignment(VerticalAlignment.CENTER)
            );

            root.child(
                    UIContainers.verticalFlow(Sizing.content(), Sizing.content())
                            .child(
                                    UIComponents.button(
                                            Text.translatable("redstonae.bothhandsatatime.button.both_hands"),
                                            button -> {
                                                setConfig("bothHand", "false");
                                                root.clearChildren();
                                                build(root);
                                            })
                            )
                            .padding(Insets.of(8))
            );
        } else {
            root.child(
                    UIContainers.stack(Sizing.content(), Sizing.content())
                            .child(UIComponents.texture(Identifier.ofVanilla("textures/map/map_background.png"), 0, 0, 128, 128, 128, 128))
                            .child(UIComponents.texture(Identifier.of("bothhandsatatime:textures/ui/one_hand.png"), 0, 0, 132, 132, 132, 132))
                            .horizontalAlignment(HorizontalAlignment.CENTER)
                            .verticalAlignment(VerticalAlignment.CENTER)
            );

            root.child(
                    UIContainers.verticalFlow(Sizing.content(), Sizing.content())
                            .child(
                                    UIComponents.button(
                                            Text.translatable("redstonae.bothhandsatatime.button.one_hand"),
                                            button -> {
                                                setConfig("bothHand", "true");
                                                root.clearChildren();
                                                build(root);
                                            })
                            )
                            .padding(Insets.of(8))
            );
        }
    }
}
