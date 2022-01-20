package de.cyklan.mctimer;

import com.mojang.bridge.game.GameSession;
import de.cyklan.mctimer.util.Config;
import de.cyklan.mctimer.util.LevelTime;
import de.cyklan.mctimer.util.Loader;
import de.cyklan.mctimer.util.Position;
import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.data.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.WorldSavePath;

import java.util.Date;
import java.util.Objects;

public class Timer {
    private static final Timer TIMER = new Timer();
    private Date timerStartTime;

    WDynamicLabel widget;

    private String world;
    private LevelTime times;
    private Config config;

    private boolean isRunning = false;
    private long lastTimerTime;
    private long worldTime;

    private Position position;

    private String getTimerText() {
        long elapsedMillis = this.worldTime + Math.abs(new Date().getTime() - timerStartTime.getTime());
        this.lastTimerTime = elapsedMillis;

        StringBuilder sb = new StringBuilder();

        int millis = (int) (elapsedMillis % 1000);
        int seconds = (int) (elapsedMillis / 1000) % 60 ;
        int minutes = (int) ((elapsedMillis / (1000*60)) % 60);
        int hours   = (int) ((elapsedMillis / (1000*60*60)) % 24);

        if (this.config.getShowHours()) {
            sb.append(String.format("%02d", hours));
            sb.append(":");
        }

        sb.append(String.format("%02d", minutes));
        sb.append(":");

        sb.append(String.format("%02d", seconds));

        if (this.config.getShowMillis()) {
            sb.append(":");
            sb.append(String.format("%03d", millis));
        }

        return sb.toString();
    }

    public static Timer getInstance() {
        return TIMER;
    }

    public void start() {
        this.loadWorldName();
        MCTimer.LOGGER.info(this.world);
        this.worldTime = this.times.getTime(this.world);
        this.timerStartTime = new Date();
        this.isRunning = true;

        this.widget = new WDynamicLabel(this::getTimerText, Color.RED_DYE.toRgb());
        CottonHud.add(this.widget, 10, 10, this.widget.getWidth(), this.widget.getHeight());
    }

    public void stop() {
        if (!this.isRunning) return;
        this.isRunning = false;
        this.times.setTime(this.world, this.lastTimerTime);
        this.lastTimerTime = 0;
        Loader loader = new Loader();
        loader.writeTimes(this.times);
    }

    public String getWorldName() {
        return this.world;
    }

    public void loadWorldName() {
        GameSession session = MinecraftClient.getInstance().getGame().getCurrentSession();
        if (session.isRemoteServer()) {
            this.world = Objects.requireNonNull(MinecraftClient.getInstance().getCurrentServerEntry()).address;
        } else {
            this.world = Objects.requireNonNull(MinecraftClient.getInstance().getServer()).getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString();
        }
    }

    public void setWorldName(String world) {
        this.world = world;
        MCTimer.LOGGER.info("World name: " + world);
    }

    public void setLevelTimes(LevelTime times) {
        this.times = times;
    }

    public LevelTime getTimes() {
        return this.times;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }
}
