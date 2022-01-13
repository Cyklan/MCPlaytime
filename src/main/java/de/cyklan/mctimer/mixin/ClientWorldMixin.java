package de.cyklan.mctimer.mixin;

import de.cyklan.mctimer.Timer;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    private static boolean hasStartedTicking = false;

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(BooleanSupplier shouldKeepTicking, CallbackInfo info) {
        if (!this.hasStartedTicking) {
            this.hasStartedTicking = true;
            Timer.getInstance().start();
        }
    }
    @Inject(at = @At("HEAD"), method = "disconnect")
    private void disconnect(CallbackInfo ci) {
        this.hasStartedTicking = false;
        Timer.getInstance().stop();
    }
}