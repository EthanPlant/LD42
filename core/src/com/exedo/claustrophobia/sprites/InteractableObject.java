package com.exedo.claustrophobia.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class InteractableObject extends Sprite {
    public InteractableObject(float x, float y) {
        setPosition(x, y);
        setBounds(0, 0, 32, 32);
    }

    public abstract void onInteract(Player player);
}
