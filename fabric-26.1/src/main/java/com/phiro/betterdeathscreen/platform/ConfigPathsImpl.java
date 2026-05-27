package com.phiro.betterdeathscreen.platform;

import net.minecraft.client.Minecraft;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class ConfigPathsImpl {
    public static Path gameDirectory() {
        return Minecraft.getInstance().gameDirectory.toPath();
    }
}
