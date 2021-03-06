package de.cyklan.mctimer.util;

import de.cyklan.mctimer.Timer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {
    public KeyBinds() {
        this.registerOptions();
        this.registerToggleTimerVisibility();
        this.registerToggleTimerRunning();
    }

    private void registerToggleTimerRunning() {
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Start / Pause Timer",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "MCTimer"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.wasPressed()) {
                Timer.getInstance().toggleTimer();
            }
        });
    }

    private void registerToggleTimerVisibility() {
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
           "Toggle Timer Visibility",
           InputUtil.Type.KEYSYM,
           GLFW.GLFW_KEY_V,
           "MCTimer"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.wasPressed()) {
                Timer.getInstance().toggleTimerVisibility();
            }
        });
    }

    private void registerOptions() {
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Options",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                "MCTimer"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                Timer.getInstance().openConfig();
            }
        });
    }
}
