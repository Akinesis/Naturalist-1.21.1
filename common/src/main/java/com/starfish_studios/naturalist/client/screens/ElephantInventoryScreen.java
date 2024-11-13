package com.starfish_studios.naturalist.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.common.entity.Elephant;
import com.starfish_studios.naturalist.common.world.inventory.ElephantInventoryMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class ElephantInventoryScreen extends AbstractContainerScreen<ElephantInventoryMenu> {
    private static final ResourceLocation ELEPHANT_INVENTORY_LOCATION = ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "textures/gui/container/elephant.png");
    private final Elephant elephant;
    private float xMouse;
    private float yMouse;

    public ElephantInventoryScreen(ElephantInventoryMenu elephantInventoryMenu, Inventory inventory, Elephant elephant) {
        super(elephantInventoryMenu, inventory, elephant.getDisplayName());
        this.elephant = elephant;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ELEPHANT_INVENTORY_LOCATION);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(ELEPHANT_INVENTORY_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (this.elephant != null) {
            if (elephant.hasChest()) {
                guiGraphics.blit(ELEPHANT_INVENTORY_LOCATION, i + 79, j + 17, 0, this.imageHeight, elephant.getInventoryColumns() * 18, 54);
            }
        }

        if (this.elephant.isSaddleable()) {
            guiGraphics.blit(ELEPHANT_INVENTORY_LOCATION, i + 7, j + 35 - 18, 18, this.imageHeight + 54, 18, 18);
        }

        if (this.elephant.isWearingBodyArmor()) {
            guiGraphics.blit(ELEPHANT_INVENTORY_LOCATION, i + 7, j + 35, 0, this.imageHeight + 54, 18, 18);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, i + 51, j + 60, i + 78, j + 70, 17, 0.25F, (float)(i + 51) - this.xMouse, (float)(j + 75 - 50) - this.yMouse, this.elephant);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics,mouseX,mouseY,partialTick);
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}