package de.cyklan.mctimer.mixin;

import de.cyklan.mctimer.MCTimer;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.world.level.storage.LevelSummary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldListWidget.Entry.class)
public class WorldListWidgetMixin {

    LevelSummary deletedLevel;

    @Inject(at = @At("TAIL"), method = "<init>")
    public void constructorTail(WorldListWidget worldListWidget, WorldListWidget levelList, LevelSummary level, CallbackInfo ci) {
        this.deletedLevel = level;
    }

    @Inject(at = @At("TAIL"), method = "delete")
    public void delete(CallbackInfo info) {
        MCTimer.LOGGER.info("Deleting World: " + this.deletedLevel.getName());
    }
}
