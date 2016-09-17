package com.cab.alington.cab.splashscreen;

/**
 * Created by abc on 9/15/2016.
 */
public class Runtimeexception extends RuntimeException {
    private static final long serialVersionUID = 234608108593115395L;

    public Runtimeexception() {
        super("Can't perform Ken Burns effect on rects with distinct aspect ratios!");
    }
}
