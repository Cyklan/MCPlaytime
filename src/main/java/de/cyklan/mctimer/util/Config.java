package de.cyklan.mctimer.util;

import java.io.Serializable;

public class Config implements Serializable {
    private HorizontalPosition horizontalPosition = HorizontalPosition.LEFT;
    private VerticalPosition verticalPosition = VerticalPosition.TOP;

    // a nice red tone :)
    private int rgbColor = 0xFFF07167;

    private boolean showHours = true;
    private boolean showMillis = true;

    private Position position = new Position(10, 10);

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
