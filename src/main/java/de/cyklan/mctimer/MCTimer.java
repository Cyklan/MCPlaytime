package de.cyklan.mctimer;

import io.github.cottonmc.cotton.gui.client.CottonHud;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class MCTimer implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("MCTimer");

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MCTimer");
		Timer.getInstance();
	}
}
