package com.exedo.claustrophobia.recipes;

import com.badlogic.gdx.utils.Array;
import com.exedo.claustrophobia.Claustrophobia;
import com.exedo.claustrophobia.sprites.items.Item;
import com.exedo.claustrophobia.sprites.items.TestItem;

public class TestRecipe extends ItemRecipe {
    Claustrophobia game;
    public TestRecipe(Claustrophobia game) {
        this.game = game;
        items = new Array<Item>(2);
        items.add(new TestItem(game));
        items.add(new TestItem(game));
        output = new TestItem(game);
    }
}
