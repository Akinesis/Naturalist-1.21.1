package com.starfish_studios.naturalist.client.model;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.common.entity.Elephant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ElephantModel extends GeoModel<Elephant> {

    @Override
    public ResourceLocation getModelResource(Elephant elephant) {
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "geo/elephant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Elephant elephant) {
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, /*elephant.isDirty() ? "textures/entity/elephant_dirt.png" :*/ "textures/entity/elephant.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Elephant elephant) {
        return ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animations/elephant.animation.json");
    }

    @Override
    public void setCustomAnimations(Elephant elephant, long uniqueID, @Nullable AnimationState<Elephant> animationState) {
        super.setCustomAnimations(elephant, uniqueID, animationState);

        if (animationState == null) return;

        //List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        GeoBone head = this.getAnimationProcessor().getBone("head");
        GeoBone bigTusks = this.getAnimationProcessor().getBone("tusks");
        GeoBone smallTusks = this.getAnimationProcessor().getBone("baby_tusks");
        GeoBone babyTrunk = this.getAnimationProcessor().getBone("trunk4");
        GeoBone leftEar = this.getAnimationProcessor().getBone("left_ear");
        GeoBone rightEar = this.getAnimationProcessor().getBone("right_ear");
        GeoBone chests = this.getAnimationProcessor().getBone("chests");
        GeoBone saddle = this.getAnimationProcessor().getBone("saddle");

        if (elephant.isBaby()) {
            head.setScaleX(1.3F); head.setScaleY(1.3F); head.setScaleZ(1.3F);
            leftEar.setScaleX(1.2F); leftEar.setScaleY(1.2F); leftEar.setScaleZ(1.2F);
            rightEar.setScaleX(1.2F); rightEar.setScaleY(1.2F); rightEar.setScaleZ(1.2F);
            head.setScaleX(1.5F);
            head.setScaleY(1.5F);
            head.setScaleZ(1.5F);
        } else {
            head.setScaleX(1.0F);
            head.setScaleY(1.0F);
            head.setScaleZ(1.0F);
        }

        chests.setHidden(!elephant.hasChest() || elephant.isBaby());
        saddle.setHidden(!elephant.isSaddled() || elephant.isBaby());

        bigTusks.setHidden(elephant.isBaby());
        smallTusks.setHidden(elephant.isBaby());
        smallTusks.setHidden(!elephant.isBaby());

        babyTrunk.setHidden(elephant.isBaby());

//        head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD);
        head.setPivotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
