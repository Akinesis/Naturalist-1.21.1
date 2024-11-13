package com.starfish_studios.naturalist.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.common.entity.Bear;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.util.Color;

@Environment(EnvType.CLIENT)
public class BearShearedLayer extends GeoRenderLayer<Bear> {
    private static final ResourceLocation LAYER = ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "textures/entity/bear/bear_sheared.png");
    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "geo/bear.geo.json");

    public BearShearedLayer(GeoRenderer<Bear> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Bear entitylivingbaseIn, BakedGeoModel bakedModel, @Nullable RenderType renderType, MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (entitylivingbaseIn.isSheared()) {
            poseStack.pushPose();
            this.getRenderer().actuallyRender(poseStack,entitylivingbaseIn,bakedModel,renderType,bufferSource,buffer,false,partialTick,packedLight,OverlayTexture.NO_OVERLAY, Color.ofARGB(1.0F, 1.0F, 1.0F, 1.0F).argbInt());
            poseStack.popPose();
        }
    }
}
