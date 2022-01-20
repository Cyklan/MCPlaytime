package de.cyklan.mctimer.GUI;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class ConfigScreen {
    public ConfigScreen() {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(MinecraftClient.getInstance().currentScreen)
                .setTitle(new LiteralText("MCTimer Settings"))
                .setSavingRunnable(() -> {

                });

        ConfigCategory general = this.createCategory(builder);
    }

    private ConfigCategory createCategory(ConfigBuilder builder) {
        return builder.getOrCreateCategory(new LiteralText("General"));
    }
}
