package com.phiro.betterdeathscreen.util;

import java.util.Map;

public final class DimensionNames {
    private static final Map<String, String> NAMES = Map.of(
            "minecraft:overworld", "Overworld",
            "minecraft:the_nether", "The Nether",
            "minecraft:the_end", "The End"
    );

    private DimensionNames() {
    }

    public static String displayName(String dimensionId) {
        return NAMES.getOrDefault(dimensionId, prettify(dimensionId));
    }

    private static String prettify(String dimensionId) {
        String name = dimensionId.contains(":") ? dimensionId.substring(dimensionId.indexOf(':') + 1) : dimensionId;
        name = name.replace('_', ' ');
        if (name.isEmpty()) {
            return dimensionId;
        }
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
