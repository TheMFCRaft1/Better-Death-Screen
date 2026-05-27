package com.phiro.betterdeathscreen.neoforge.client;

import com.phiro.betterdeathscreen.BetterDeathScreenMod;
import com.phiro.betterdeathscreen.event.DeathEventHandler;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = BetterDeathScreenMod.MOD_ID, value = Dist.CLIENT)
public final class NeoForgeClientEvents {
    private NeoForgeClientEvents() {
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof LocalPlayer player) {
            DeathEventHandler.onPlayerDeath(player, event.getSource());
        }
    }
}
