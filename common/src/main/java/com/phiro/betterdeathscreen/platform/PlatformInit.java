package com.phiro.betterdeathscreen.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

public final class PlatformInit {
    private PlatformInit() {
    }

    @ExpectPlatform
    public static native void init();
}
