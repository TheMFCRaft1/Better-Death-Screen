package com.phiro.betterdeathscreen;

import com.phiro.betterdeathscreen.config.BDSConfig;
import com.phiro.betterdeathscreen.platform.PlatformInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BetterDeathScreenMod {
    public static final String MOD_ID = "better_death_screen";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    private BetterDeathScreenMod() {
    }

    public static void initClient() {
        BDSConfig.load();
        PlatformInit.init();
    }
}
