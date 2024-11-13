package com.starfish_studios.naturalist.client.model;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.common.entity.Boar;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@Environment(EnvType.CLIENT)
public class BoarModel extends GeoModel<Boar> {
    @Override
    public ResourceLocation getModelResource(Boar boar) {
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "geo/boar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Boar boar) {
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "textures/entity/boar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Boar boar) {
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animations/boar.animation.json");
    }

    @Override
    public void setCustomAnimations(Boar animatable, long instanceId, AnimationState<Boar> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        if (animationState == null) return;

        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        GeoBone head = this.getAnimationProcessor().getBone("head");

        if (animatable.isBaby()) {
            head.setScaleX(1.75F);
            head.setScaleY(1.75F);
            head.setScaleZ(1.75F);
        } else {
            head.setScaleX(1.0F);
            head.setScaleY(1.0F);
            head.setScaleZ(1.0F);
        }

        head.setPivotX(extraDataOfType.headPitch() * Mth.DEG_TO_RAD);
        head.setPivotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
