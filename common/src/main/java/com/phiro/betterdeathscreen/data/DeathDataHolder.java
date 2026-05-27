package com.phiro.betterdeathscreen.data;

public final class DeathDataHolder {
    private static DeathData current;

    private DeathDataHolder() {
    }

    public static void set(DeathData data) {
        current = data;
    }

    public static DeathData getCurrent() {
        return current != null ? current : DeathData.empty();
    }

    public static void clear() {
        current = null;
    }
}
