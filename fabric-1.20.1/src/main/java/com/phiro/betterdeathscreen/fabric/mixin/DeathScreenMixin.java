package com.phiro.betterdeathscreen.fabric.mixin;

import com.phiro.betterdeathscreen.data.DeathDataHolder;
import com.phiro.betterdeathscreen.screen.BetterDeathScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public abstract class DeathScreenMixin {
    @Shadow
    @Final
    private boolean hardcore;

    @Shadow
    private Component score;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void betterDeathScreen$replaceScreen(CallbackInfo ci) {
        Minecraft.getInstance().setScreen(new BetterDeathScreen(
                DeathDataHolder.getCurrent(),
                hardcore,
                score
        ));
        ci.cancel();
    }
}
