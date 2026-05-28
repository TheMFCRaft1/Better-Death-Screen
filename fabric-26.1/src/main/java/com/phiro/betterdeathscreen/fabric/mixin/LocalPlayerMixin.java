package com.phiro.betterdeathscreen.fabric.mixin;

import com.phiro.betterdeathscreen.event.DeathEventHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LocalPlayerMixin {
    @Inject(method = "die(Lnet/minecraft/world/damagesource/DamageSource;Z)V", at = @At("HEAD"))
    private void betterDeathScreen$captureDeath(DamageSource source, boolean bl, CallbackInfo ci) {
        if ((Object) this instanceof LocalPlayer player) {
            DeathEventHandler.onPlayerDeath(player, source);
        }
    }
}
