package com.exedo.claustrophobia.sprites.items;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.exedo.claustrophobia.Claustrophobia;

public class TestItem extends Item {
    Claustrophobia game;

    public TestItem(Claustrophobia game) {
        super(game.getAssets().get("spritesheets/items.atlas", TextureAtlas.class).findRegion("test"));
        this.game = game;
    }
}
