package de.cyklan.mctimer;

import com.mojang.bridge.game.GameSession;
import de.cyklan.mctimer.GUI.ConfigScreen;
import de.cyklan.mctimer.util.Config;
import de.cyklan.mctimer.util.LevelTime;
import de.cyklan.mctimer.util.Loader;
import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.WorldSavePath;

import java.util.Date;
import java.util.Objects;

public class Timer {
    private static final Timer TIMER = new Timer();
    private Date timerStartTime;
    private Date timerStopTime;

    private WDynamicLabel widget;
    private boolean isWidgetVisible;

    private String world;
    private LevelTime times;
    private Config config;

    private boolean isRunning = false;
    private long lastTimerTime;
    private long worldTime;

    private String getTimerText() {
        long elapsedMillis;
        if (this.isRunning) {
            elapsedMillis = this.worldTime + Math.abs(new Date().getTime() - timerStartTime.getTime());
        } else {
            elapsedMillis = this.worldTime + Math.abs(timerStopTime.getTime() - timerStartTime.getTime());
        }
        this.lastTimerTime = elapsedMillis;

        StringBuilder sb = new StringBuilder();

        int millis = (int) (elapsedMillis % 1000);
        int seconds = (int) (elapsedMillis / 1000) % 60 ;
        int minutes = (int) ((elapsedMillis / (1000*60)) % 60);
        int hours   = (int) ((elapsedMillis / (1000*60*60)) % 24);

        if (this.config.getShowHours() || hours > 0) {
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

    public void toggleTimer() {
        if (this.isRunning) {
            this.stop(false);
        } else {
            this.start(false);
        }
    }

    public void toggleTimerVisibility() {
        if (this.isWidgetVisible) {
            this.removeTimer();
        } else {
            this.showTimer();
        }
    }

    public void start() {
        this.start(true);
    }

    public void start(boolean showTimer) {
        this.loadWorldName();
        this.worldTime = this.times.getTime(this.world);
        this.timerStartTime = new Date();
        this.isRunning = true;

        if (showTimer) {
            this.showTimer();
        }
    }

    public void stop() {
        this.stop(true);
    }

    public void stop(boolean hideTimer) {
        if (!this.isRunning) return;
        this.timerStopTime = new Date();
        this.isRunning = false;
        this.times.setTime(this.world, this.lastTimerTime);
        this.lastTimerTime = 0;
        Loader loader = new Loader();
        loader.writeTimes(this.times);

        if (hideTimer) {
            this.removeTimer();
        }
    }

    public void showTimer() {
        if (!this.isRunning) return;
        this.isWidgetVisible = true;
        this.widget = new WDynamicLabel(this::getTimerText, this.config.getRgbColor());
        this.updateHorizontalAlignment();
        this.config.getPosition().updatePosition(
                this.config.getHorizontalPosition(),
                this.config.getVerticalPosition(),
                this.widget.getWidth(),
                this.widget.getHeight()
        );
        CottonHud.add(
                this.widget,
                this.config.getPosition().getX(),
                this.config.getPosition().getY(),
                this.widget.getWidth(),
                this.widget.getHeight()
        );
    }

    private void updateHorizontalAlignment() {
        switch (this.config.getHorizontalPosition()) {
            case LEFT -> this.widget.setAlignment(HorizontalAlignment.LEFT);
            case CENTER -> this.widget.setAlignment(HorizontalAlignment.CENTER);
            case RIGHT -> this.widget.setAlignment(HorizontalAlignment.RIGHT);
        }
    }

    public void removeTimer() {
        this.isWidgetVisible = false;
        CottonHud.remove(this.widget);
    }

    public void reloadTimer() {
        this.removeTimer();
        this.showTimer();
    }

    public void openConfig() {
        new ConfigScreen();
    }

    public void loadWorldName() {
        GameSession session = MinecraftClient.getInstance().getGame().getCurrentSession();
        if (session != null && session.isRemoteServer()) {
            this.world = Objects.requireNonNull(MinecraftClient.getInstance().getCurrentServerEntry()).address;
        } else {
            this.world = Objects.requireNonNull(MinecraftClient.getInstance().getServer()).getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString();
        }
    }


    public void setLevelTimes(LevelTime times) {
        this.times = times;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }
}
