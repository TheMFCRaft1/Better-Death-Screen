package com.phiro.betterdeathscreen.fabric.mixin;

import com.phiro.betterdeathscreen.event.DeathEventHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    @Inject(method = "die(Lnet/minecraft/world/damagesource/DamageSource;)V", at = @At("HEAD"))
    private void betterDeathScreen$captureDeath(DamageSource source, CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        DeathEventHandler.onPlayerDeath(player, source);
    }
}
