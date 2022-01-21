package de.cyklan.mctimer;

import de.cyklan.mctimer.util.Loader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
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
		Loader loader = new Loader();

		loader.createLevelTimesFileIfNotExists();
		Timer.getInstance().setLevelTimes(loader.readTimes());

		loader.createConfigFileIfNotExists();
		Timer.getInstance().setConfig(loader.readConfig());

		KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"MCTimer Options",
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
