package com.starfish_studios.naturalist.common.animations;

import com.starfish_studios.naturalist.common.entity.Bear;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public interface IEatingAnimation {
    RawAnimation EAT_ANIM = RawAnimation.begin().thenPlay("eat");
    EntityDataAccessor<Integer> EAT_COUNTER = SynchedEntityData.defineId(Bear.class, EntityDataSerializers.INT);

    default  <E extends GeoAnimatable> PlayState eatPredicate(AnimationState<E> event) {
        if (this.isEating()) {
            event.getController().setAnimation(EAT_ANIM);
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    default boolean isEating() {
        return ((Entity)this).getEntityData().get(EAT_COUNTER) > 0;
    }
}
