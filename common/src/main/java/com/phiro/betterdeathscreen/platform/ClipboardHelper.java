package com.phiro.betterdeathscreen.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

public final class ClipboardHelper {
    private ClipboardHelper() {
    }

    @ExpectPlatform
    public static native void setClipboard(String text);
}
