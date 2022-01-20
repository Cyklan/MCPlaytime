package de.cyklan.mctimer.GUI;

import de.cyklan.mctimer.Timer;
import de.cyklan.mctimer.util.Config;
import de.cyklan.mctimer.util.Loader;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.ColorEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

public class ConfigScreen {

    private final Config config = Timer.getInstance().getConfig();

    public ConfigScreen() {
        ConfigBuilder builder = this.createConfigBuilder();
        ConfigCategory colorCategory = this.createCategory(builder, "Color");
        ConfigCategory displayCategory = this.createCategory(builder, "Display");
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        colorCategory.addEntry(this.colorOption(entryBuilder));
        displayCategory.addEntry(this.hoursOption(entryBuilder));
        displayCategory.addEntry(this.millisOption(entryBuilder));


        Screen screen = builder.build();
        MinecraftClient.getInstance().setScreen(screen);
    }

    private ConfigBuilder createConfigBuilder() {
        return ConfigBuilder.create()
                .setParentScreen(MinecraftClient.getInstance().currentScreen)
                .setTitle(new LiteralText("MCTimer Settings"))
                .setSavingRunnable(() -> {
                    Timer timer = Timer.getInstance();
                    timer.setConfig(config);
                    timer.reloadTimer();
                    Loader loader = new Loader();
                    loader.writeConfig(config);
                });
    }

    private ConfigCategory createCategory(ConfigBuilder builder, String text) {
        return builder.getOrCreateCategory(new LiteralText(text));
    }

    private BooleanListEntry hoursOption(ConfigEntryBuilder builder) {
        return builder.startBooleanToggle(new LiteralText("Always Show Hours"), config.getShowHours())
                .setDefaultValue(config.getShowHours())
                .setSaveConsumer((value) -> config.setShowHours(value))
                .build();
    }

    private BooleanListEntry millisOption(ConfigEntryBuilder builder) {
        return builder.startBooleanToggle(new LiteralText("Show Milliseconds"), config.getShowMillis())
                .setDefaultValue(config.getShowMillis())
                .setSaveConsumer((value) -> config.setShowMillis(value))
                .build();
    }

    private ColorEntry colorOption(ConfigEntryBuilder builder) {
        return builder.startAlphaColorField(new LiteralText("Color"), config.getRgbColor())
                .setDefaultValue(config.getRgbColor())
                .setSaveConsumer((value) -> config.setRgbColor(value))
                .build();

    }
}
