package de.cyklan.mctimer.GUI;


import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Color;
import net.minecraft.text.LiteralText;

public class TimerWidget {
    private WLabel label;

    public TimerWidget() {
        this.label = new TimerLabel("00:00:00", Color.RED_DYE.toRgb());
    }

    public WLabel getLabel() {
        return this.label;
    }
}
