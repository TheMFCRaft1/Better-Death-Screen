package com.phiro.betterdeathscreen.platform;

import com.phiro.betterdeathscreen.BetterDeathScreenMod;
import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public final class ConfigPaths {
    private ConfigPaths() {
    }

    @ExpectPlatform
    public static native Path gameDirectory();

    public static Path configFile() {
        return gameDirectory().resolve("config").resolve(BetterDeathScreenMod.MOD_ID + ".json");
    }
}
