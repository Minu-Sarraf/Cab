package com.cab.alington.cab.splashscreen.splash;

import android.graphics.RectF;

/**
 * Created by abc on 9/16/2016.
 */
public interface TransitionGenerator {
    public Transition generateNextTransition(RectF drawableBounds, RectF viewport);
}
