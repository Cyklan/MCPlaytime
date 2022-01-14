package de.cyklan.mctimer;

import com.mojang.bridge.game.GameSession;
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

    private String world;

    private boolean isRunning = false;
    private long lastTimerTime;

    private Position position;

    private String getTimerText() {
        long elapsedMillis = Math.abs(new Date().getTime() - timerStartTime.getTime());
        this.lastTimerTime = elapsedMillis;

        StringBuilder sb = new StringBuilder();

        int seconds = (int) (elapsedMillis / 1000) % 60 ;
        int minutes = (int) ((elapsedMillis / (1000*60)) % 60);
        int hours   = (int) ((elapsedMillis / (1000*60*60)) % 24);

        sb.append(String.format("%02d", hours));
        sb.append(":");

        sb.append(String.format("%02d", minutes));
        sb.append(":");

        sb.append(String.format("%02d", seconds));
        sb.append(":");

        sb.append(String.format("%03d", elapsedMillis % 1000));

        return sb.toString();
    }

    public static Timer getInstance() {
        return TIMER;
    }

    public void start() {
        this.loadWorldName();
        MCTimer.LOGGER.info(this.world);
        this.timerStartTime = new Date();
        this.isRunning = true;

        WDynamicLabel label = new WDynamicLabel(() -> this.getTimerText(), Color.RED_DYE.toRgb());
        CottonHud.add(label, 10, 10, label.getWidth(), label.getHeight());
        // CottonHud.add(this.getWidget(), 10 + this.getWidget().getWidth(), 10, this.getWidget().getWidth(), this.getWidget().getHeight());
    }

    public void stop() {
        if (!this.isRunning) return;
        this.isRunning = false;
    }

    // TODO
    private void calculatePositioning() {
    }

    public void updateScreenPosition() {
        // int width = MinecraftClient.getInstance().currentScreen.width;
        // int height = MinecraftClient.getInstance().currentScreen.height;
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
}
