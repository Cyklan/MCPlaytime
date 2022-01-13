package de.cyklan.mctimer.GUI;

import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.text.LiteralText;

public class TimerLabel extends WLabel {

    private int counter = 0;

    public TimerLabel(String text, int color) {
        super(text, color);
        this.updateText();
    }

    public void updateText() {
        this.text = new LiteralText("" + counter);
    }
}
