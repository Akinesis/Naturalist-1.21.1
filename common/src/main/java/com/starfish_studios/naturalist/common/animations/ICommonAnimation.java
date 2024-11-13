package com.starfish_studios.naturalist.common.animations;

import software.bernie.geckolib.animation.RawAnimation;

public interface ICommonAnimation {

    RawAnimation WALK_ANIM = RawAnimation.begin().thenPlay("walk");
    RawAnimation RUN_ANIM = RawAnimation.begin().thenPlay("run");
    RawAnimation IDLE_ANIM = RawAnimation.begin().thenPlay("idle");
}
