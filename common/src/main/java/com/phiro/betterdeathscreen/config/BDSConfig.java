package com.phiro.betterdeathscreen.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phiro.betterdeathscreen.BetterDeathScreenMod;
import com.phiro.betterdeathscreen.platform.ConfigPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class BDSConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public boolean showInventory = true;
    public boolean showCoordinates = true;
    public boolean showDeathCause = true;
    public boolean showXPLevel = true;
    public boolean enableCopyCoords = true;

    private static BDSConfig INSTANCE = new BDSConfig();

    private BDSConfig() {
    }

    public static BDSConfig get() {
        return INSTANCE;
    }

    public static void load() {
        Path path = ConfigPaths.configFile();
        if (!Files.exists(path)) {
            save();
            return;
        }
        try {
            String json = Files.readString(path);
            BDSConfig loaded = GSON.fromJson(json, BDSConfig.class);
            INSTANCE = loaded != null ? loaded : new BDSConfig();
        } catch (IOException e) {
            BetterDeathScreenMod.LOG.warn("Failed to load config, using defaults", e);
            INSTANCE = new BDSConfig();
        }
    }

    public static void save() {
        Path path = ConfigPaths.configFile();
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(INSTANCE));
        } catch (IOException e) {
            BetterDeathScreenMod.LOG.warn("Failed to save config", e);
        }
    }
}
