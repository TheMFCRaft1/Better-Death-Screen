package com.phiro.betterdeathscreen.platform;

import com.phiro.betterdeathscreen.fabric.config.ClothConfigIntegration;

@SuppressWarnings("unused")
public class PlatformInitImpl {
    public static void init() {
        ClothConfigIntegration.register();
    }
}
