package de.cyklan.mctimer;

import com.mojang.bridge.game.GameSession;
import com.mojang.bridge.launcher.SessionEventListener;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.util.WorldSavePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class MCTimer implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("MCTimer");

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MCTimer");
		// new SessionEventListener() {
		// 	@Override
		// 	public void onStartGameSession(GameSession session) {
		// 		SessionEventListener.super.onStartGameSession(session);
		// 		MinecraftClient client = MinecraftClient.getInstance();
		// 		if (session.isRemoteServer()) {
		// 			Timer.getInstance().setWorldName(Objects.requireNonNull(client.getCurrentServerEntry()).address);
		// 		} else {
		// 			Timer.getInstance().setWorldName(Objects.requireNonNull(client.getServer()).getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString());
		// 		}
		// 	}
		// };
	}
}
