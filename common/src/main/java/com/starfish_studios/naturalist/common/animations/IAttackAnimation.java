package com.starfish_studios.naturalist.common.animations;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public interface IAttackAnimation {


    RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlayXTimes("attack", 1);
    RawAnimation BITE_ANIM = RawAnimation.begin().thenPlayXTimes("bite",1);;

    default <E extends GeoAnimatable> PlayState attackPredicate(AnimationState<E> event) {
        if (((LivingEntity)this).swinging && event.getController().getAnimationState().equals(PlayState.STOP)) {
            event.getController().setAnimation(ATTACK_ANIM);
            ((LivingEntity)this).swinging = false;
        }
        return PlayState.CONTINUE;
    }

    default <E extends GeoAnimatable> PlayState bitePredicate(AnimationState<E> event) {
        if (((LivingEntity)this).swinging && event.getController().getAnimationState().equals(PlayState.STOP)) {
            event.getController().setAnimation(BITE_ANIM);
            ((LivingEntity)this).swinging = false;
        }
        return PlayState.CONTINUE;
    }
}
