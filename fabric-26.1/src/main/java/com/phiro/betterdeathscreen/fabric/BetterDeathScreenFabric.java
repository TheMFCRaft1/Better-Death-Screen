package com.phiro.betterdeathscreen.fabric;

import com.phiro.betterdeathscreen.BetterDeathScreenMod;
import net.fabricmc.api.ClientModInitializer;

public class BetterDeathScreenFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BetterDeathScreenMod.initClient();
    }
}
