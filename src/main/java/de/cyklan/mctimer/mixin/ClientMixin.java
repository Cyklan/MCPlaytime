package de.cyklan.mctimer.mixin;

import de.cyklan.mctimer.Timer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ClientMixin {
    @Inject(at = @At("TAIL"), method = "onResolutionChanged")
    private void onResolutionChanged(CallbackInfo info) {
        Timer.getInstance().reloadTimer();
    }


}
