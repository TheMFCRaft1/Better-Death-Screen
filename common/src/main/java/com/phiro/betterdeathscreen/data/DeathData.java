package com.phiro.betterdeathscreen.data;

import com.phiro.betterdeathscreen.util.DeathCauseFormatter;
import com.phiro.betterdeathscreen.util.DimensionNames;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DeathData {
    public final String causeOfDeath;
    public final double x;
    public final double y;
    public final double z;
    public final String dimension;
    public final String dimensionDisplay;
    public final List<ItemStack> inventory;
    public final long timestamp;
    public final int expLevel;

    public DeathData(
            String causeOfDeath,
            double x,
            double y,
            double z,
            String dimension,
            String dimensionDisplay,
            List<ItemStack> inventory,
            long timestamp,
            int expLevel
    ) {
        this.causeOfDeath = causeOfDeath;
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
        this.dimensionDisplay = dimensionDisplay;
        this.inventory = Collections.unmodifiableList(inventory);
        this.timestamp = timestamp;
        this.expLevel = expLevel;
    }

    public static DeathData capture(Player player, DamageSource source) {
        Component deathMessage = source.getLocalizedDeathMessage(player);
        String rawCause = deathMessage.getString();
        String playerName = player.getName().getString();
        if (rawCause.startsWith(playerName + " ")) {
            rawCause = rawCause.substring(playerName.length() + 1);
        }
        String cause = DeathCauseFormatter.format(rawCause);

        String dimensionId = player.level().dimension().location().toString();
        String dimensionDisplay = DimensionNames.displayName(dimensionId);

        List<ItemStack> items = new ArrayList<>(41);
        var inv = player.getInventory();
        for (int i = 0; i < 36; i++) {
            items.add(inv.getItem(i).copy());
        }
        for (int i = 0; i < 4; i++) {
            items.add(inv.armor.get(i).copy());
        }
        items.add(inv.offhand.get(0).copy());

        return new DeathData(
                cause,
                player.getX(),
                player.getY(),
                player.getZ(),
                dimensionId,
                dimensionDisplay,
                items,
                System.currentTimeMillis(),
                player.experienceLevel
        );
    }

    public String coordsClipboardText() {
        return String.format("X: %.0f, Y: %.0f, Z: %.0f (%s)", x, y, z, dimensionDisplay);
    }

    public static DeathData empty() {
        return new DeathData(
                "Unknown",
                0,
                0,
                0,
                "minecraft:overworld",
                "Overworld",
                Collections.nCopies(41, ItemStack.EMPTY),
                0L,
                0
        );
    }
}
