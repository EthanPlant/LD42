package com.exedo.claustrophobia.sprites.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Item {
    private TextureRegion texture;

    public Item(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureRegion getTexture() {
        return texture;
    }
}
