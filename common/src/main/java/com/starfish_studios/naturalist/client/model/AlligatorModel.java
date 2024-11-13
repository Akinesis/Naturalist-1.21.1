package com.starfish_studios.naturalist.client.model;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.common.entity.Alligator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


@Environment(EnvType.CLIENT)
public class AlligatorModel extends GeoModel<Alligator> {

    @Override
    public ResourceLocation getModelResource(Alligator alligator) {
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "geo/alligator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Alligator alligator) {
        if (alligator.isBaby()) {
            return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "textures/entity/baby_alligator.png");
        }
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "textures/entity/alligator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Alligator alligator) {
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animations/alligator.animation.json");
    }

    @Override
    public void setCustomAnimations(Alligator alligator, long instanceId, software.bernie.geckolib.animation.AnimationState<Alligator> animationState) {
        super.setCustomAnimations(alligator, instanceId, animationState);

        if (animationState == null) return;

        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        GeoBone head = this.getAnimationProcessor().getBone("head");

        if (alligator.isBaby()) {
            head.setScaleX(1.5F);
            head.setScaleY(1.5F);
            head.setScaleZ(1.5F);
        } else {
            head.setScaleX(1.0F);
            head.setScaleY(1.0F);
            head.setScaleZ(1.0F);
        }

        head.setPivotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
