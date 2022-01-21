package de.cyklan.mctimer.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

import java.io.Serializable;

public class Position implements Serializable {
    private int x;
    private int y;
    private int height;
    private int width;

    public Position() {

    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void updatePosition(HorizontalPosition horizontal, VerticalPosition vertical, int widgetWidth, int widgetHeight) {
        Window window = MinecraftClient.getInstance().getWindow();
        this.x = this.updateHorizontal(horizontal, window.getScaledWidth(), widgetWidth);
        this.y = this.updateVertical(vertical, window.getScaledHeight(), widgetHeight);
    }

    private int updateVertical(VerticalPosition vertical, int screenHeight, int widgetHeight) {
        switch (vertical) {
            case TOP -> {
                return 10;
            }
            case MIDDLE -> {
                return (screenHeight / 2) - (widgetHeight / 2);
            }
            case BOTTOM -> {
                return screenHeight - widgetHeight - 10;
            }
        }

        return 10;
    }

    private int updateHorizontal(HorizontalPosition horizontalPosition, int screenWidth, int widgetWidth) {
        switch(horizontalPosition) {
            case LEFT -> {
                return 10;
            }
            case CENTER -> {
                return (screenWidth / 2) - (widgetWidth / 2);
            }
            case RIGHT -> {
                return screenWidth - 10 - widgetWidth;
            }
        }
        return 10;
    }
}
