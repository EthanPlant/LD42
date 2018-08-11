package com.exedo.claustrophobia.sprites.objects;

import com.badlogic.gdx.graphics.Texture;
import com.exedo.claustrophobia.Claustrophobia;
import com.exedo.claustrophobia.sprites.InteractableObject;
import com.exedo.claustrophobia.sprites.Player;
import com.exedo.claustrophobia.sprites.items.TestItem;

public class TestObject extends InteractableObject {
    Claustrophobia game;
    TestItem item;

    public TestObject(float x, float y, Claustrophobia game) {
        super(x, y);
        this.game = game;
        setRegion(game.getAssets().get("textures/testobj.png", Texture.class));
        item = new TestItem(game);
    }

    @Override
    public void onInteract(Player player) {
        player.getInventory().store(new TestItem(game), 1);
    }
}
