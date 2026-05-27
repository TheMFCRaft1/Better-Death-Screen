package com.phiro.betterdeathscreen.neoforge;

import com.phiro.betterdeathscreen.BetterDeathScreenMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(BetterDeathScreenMod.MOD_ID)
public class BetterDeathScreenNeoForge {
    public BetterDeathScreenNeoForge() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            BetterDeathScreenMod.initClient();
        }
    }
}
