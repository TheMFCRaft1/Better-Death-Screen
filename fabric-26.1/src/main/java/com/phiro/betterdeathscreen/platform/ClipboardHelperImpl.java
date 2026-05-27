package com.phiro.betterdeathscreen.platform;

import net.minecraft.client.Minecraft;

@SuppressWarnings("unused")
public class ClipboardHelperImpl {
    public static void setClipboard(String text) {
        Minecraft.getInstance().keyboardHandler.setClipboard(text);
    }
}
