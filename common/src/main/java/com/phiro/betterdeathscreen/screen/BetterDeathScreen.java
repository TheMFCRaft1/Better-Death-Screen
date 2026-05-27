package com.phiro.betterdeathscreen.screen;

import com.phiro.betterdeathscreen.config.BDSConfig;
import com.phiro.betterdeathscreen.data.DeathData;
import com.phiro.betterdeathscreen.platform.ClipboardHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class BetterDeathScreen extends Screen {
    private static final int SLOT_SIZE = 20;
    private static final int GRID_COLS = 9;
    private static final int GRID_ROWS = 5;

    private final DeathData deathData;
    private final boolean hardcore;
    private final Component deathScore;

    private int gridLeft;
    private int gridTop;

    public BetterDeathScreen(DeathData deathData, boolean hardcore, Component deathScore) {
        super(Component.literal("Better Death Screen"));
        this.deathData = deathData;
        this.hardcore = hardcore;
        this.deathScore = deathScore;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int buttonY = this.height - 52;
        int buttonWidth = 98;
        int gap = 4;

        BDSConfig config = BDSConfig.get();
        int buttonCount = config.enableCopyCoords ? 3 : 2;
        int totalWidth = buttonCount * buttonWidth + (buttonCount - 1) * gap;
        int startX = centerX - totalWidth / 2;
        int x = startX;

        if (config.enableCopyCoords) {
            addRenderableWidget(Button.builder(Component.literal("Copy Coords"), btn -> {
                ClipboardHelper.setClipboard(deathData.coordsClipboardText());
            }).bounds(x, buttonY, buttonWidth, 20).build());
            x += buttonWidth + gap;
        }

        addRenderableWidget(Button.builder(Component.translatable("deathScreen.respawn"), btn -> {
            if (minecraft != null && minecraft.player != null) {
                minecraft.player.respawn();
                minecraft.setScreen(null);
            }
        }).bounds(x, buttonY, buttonWidth, 20).build());
        x += buttonWidth + gap;

        Component titleLabel = hardcore
                ? Component.translatable("deathScreen.title.hardcore")
                : Component.translatable("deathScreen.title");
        addRenderableWidget(Button.builder(Component.translatable("deathScreen.titleScreen"), btn -> {
            if (minecraft != null) {
                minecraft.setScreen(new TitleScreen());
            }
        }).bounds(x, buttonY, buttonWidth, 20).build());

        if (BDSConfig.get().showInventory) {
            gridLeft = centerX - (GRID_COLS * SLOT_SIZE) / 2;
            gridTop = this.height / 2 + 8;
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics, mouseX, mouseY, partialTick);
        graphics.fill(0, 0, this.width, this.height, 0xA0000000);

        int centerX = this.width / 2;
        int y = 28;

        Component title = hardcore
                ? Component.translatable("deathScreen.title.hardcore")
                : Component.literal("YOU DIED");
        graphics.drawCenteredString(font, title, centerX, y, 0xFF5555);
        y += 24;

        BDSConfig config = BDSConfig.get();

        if (config.showDeathCause) {
            graphics.drawString(font, Component.literal("Cause:"), centerX - 150, y, 0xAAAAAA, false);
            graphics.drawString(font, Component.literal(deathData.causeOfDeath), centerX - 90, y, 0xFF5555, false);
            y += 14;
        }

        if (config.showCoordinates) {
            graphics.drawString(font, Component.literal("Location:"), centerX - 150, y, 0xAAAAAA, false);
            String coords = String.format("X: %.0f  Y: %.0f  Z: %.0f", deathData.x, deathData.y, deathData.z);
            graphics.drawString(font, Component.literal(coords), centerX - 90, y, 0xFFFF55, false);
            y += 14;
        }

        if (config.showCoordinates) {
            graphics.drawString(font, Component.literal("Dimension:"), centerX - 150, y, 0xAAAAAA, false);
            graphics.drawString(font, Component.literal(deathData.dimensionDisplay), centerX - 90, y, 0x55FFFF, false);
            y += 14;
        }

        if (config.showXPLevel) {
            graphics.drawString(font, Component.literal("XP Lost:"), centerX - 150, y, 0xAAAAAA, false);
            graphics.drawString(font, Component.literal("Level " + deathData.expLevel), centerX - 90, y, 0xFFFFFF, false);
            y += 18;
        }

        if (config.showInventory && deathData.inventory.size() >= 41) {
            graphics.drawCenteredString(font, Component.literal("Lost Items"), centerX, gridTop - 12, 0xFFFFFF);
            renderInventoryGrid(graphics, mouseX, mouseY);
        }

        if (deathScore != null) {
            graphics.drawCenteredString(font, deathScore, centerX, 16, 0xFFFFFF);
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void renderInventoryGrid(GuiGraphics graphics, int mouseX, int mouseY) {
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                int index = row * GRID_COLS + col;
                if (index >= deathData.inventory.size()) {
                    continue;
                }
                int slotX = gridLeft + col * SLOT_SIZE;
                int slotY = gridTop + row * SLOT_SIZE;
                renderSlot(graphics, deathData.inventory.get(index), slotX, slotY, mouseX, mouseY);
            }
        }
    }

    private void renderSlot(GuiGraphics graphics, ItemStack stack, int x, int y, int mouseX, int mouseY) {
        graphics.fill(x, y, x + 18, y + 18, 0x80222222);
        if (!stack.isEmpty()) {
            graphics.renderItem(stack, x + 1, y + 1);
            graphics.renderItemDecorations(font, stack, x + 1, y + 1);
            if (mouseX >= x && mouseX < x + 18 && mouseY >= y && mouseY < y + 18) {
                graphics.renderTooltip(font, stack, mouseX, mouseY);
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
