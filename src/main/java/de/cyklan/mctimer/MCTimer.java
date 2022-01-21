package de.cyklan.mctimer;

import de.cyklan.mctimer.util.KeyBinds;
import de.cyklan.mctimer.util.Loader;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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

		new KeyBinds();
	}
}
