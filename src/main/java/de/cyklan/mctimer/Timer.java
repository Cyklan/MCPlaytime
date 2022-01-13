package de.cyklan.mctimer;

import de.cyklan.mctimer.GUI.TimerWidget;
import de.cyklan.mctimer.util.Position;
import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import java.util.TimerTask;

public class Timer {
    private static Timer TIMER = new Timer();
    private java.util.Timer timer;
    private int timerDelay = 0;
    private long lastTimerTick;

    private boolean isRunning = false;

    private TimerWidget widget;
    private Position position;

    private int s = 0;
    private int m = 0;
    private int h = 0;

    private Timer() {
        widget = new TimerWidget();
        timer = new java.util.Timer();

    }

    private LiteralText getTimerText() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%02d", h));
        sb.append(":");

        sb.append(String.format("%02d", m));
        sb.append(":");

        sb.append(String.format("%02d", s));

        return new LiteralText(sb.toString());
    }

    public static Timer getInstance() {
        return TIMER;
    }

    public void start() {
        this.timer = new java.util.Timer();
        this.isRunning = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                lastTimerTick = System.currentTimeMillis();
                s += 1;
                if (s == 60) {
                    s = 0;
                    m += 1;
                }

                if (m == 60) {
                    m = 0;
                    h += 0;
                }

                widget.getLabel().setText(getTimerText());
            }
        }, this.timerDelay, 1000);
        CottonHud.add(this.getWidget(), 10 + this.getWidget().getWidth(), 10, this.getWidget().getWidth(), this.getWidget().getHeight());
    }

    public void stop() {
        if (!this.isRunning) return;
        this.timerDelay = (int)(System.currentTimeMillis() - this.lastTimerTick);
        this.timer.cancel();
        this.timer.purge();
        this.isRunning = false;
    }

    // TODO
    private void calculatePositioning() {
    }

    public void updateScreenPosition() {
        int width = MinecraftClient.getInstance().currentScreen.width;
        int height = MinecraftClient.getInstance().currentScreen.height;
    }

    public WWidget getWidget() {
        return this.widget.getLabel();
    }
}
