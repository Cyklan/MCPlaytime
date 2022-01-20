package de.cyklan.mctimer.util;

import io.github.cottonmc.cotton.gui.widget.data.Color;

import java.io.Serializable;

public class Config implements Serializable {
    private HorizontalPosition horizontalPosition = HorizontalPosition.LEFT;
    private VerticalPosition verticalPosition = VerticalPosition.TOP;

    private int rgbColor = Color.RED_DYE.toRgb();

    private boolean showHours = true;
    private boolean showMillis = true;

    public HorizontalPosition getHorizontalPosition() {
        return horizontalPosition;
    }

    public void setHorizontalPosition(HorizontalPosition horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    public VerticalPosition getVerticalPosition() {
        return verticalPosition;
    }

    public void setVerticalPosition(VerticalPosition verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    public int getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(int rgbColor) {
        this.rgbColor = rgbColor;
    }

    public boolean getShowHours() {
        return showHours;
    }

    public void setShowHours(boolean showHours) {
        this.showHours = showHours;
    }

    public boolean getShowMillis() {
        return showMillis;
    }

    public void setShowMillis(boolean showMillis) {
        this.showMillis = showMillis;
    }
}
