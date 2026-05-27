package com.phiro.betterdeathscreen.fabric.config;

import com.phiro.betterdeathscreen.BetterDeathScreenMod;
import dev.architectury.platform.Platform;

public final class ClothConfigIntegration {
    private ClothConfigIntegration() {
    }

    public static void register() {
        if (Platform.isModLoaded("cloth-config")) {
            BetterDeathScreenMod.LOG.info("Cloth Config detected; edit {}.json in your config folder.", BetterDeathScreenMod.MOD_ID);
        }
    }
}
