package com.phiro.betterdeathscreen.event;

import com.phiro.betterdeathscreen.data.DeathData;
import com.phiro.betterdeathscreen.data.DeathDataHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public final class DeathEventHandler {
    private DeathEventHandler() {
    }

    public static void onPlayerDeath(Player player, DamageSource source) {
        if (player.level().isClientSide()) {
            DeathDataHolder.set(DeathData.capture(player, source));
        }
    }
}
