package com.exedo.claustrophobia.recipes;

import com.badlogic.gdx.utils.Array;
import com.exedo.claustrophobia.sprites.items.Item;

public abstract class ItemRecipe {
    protected Array<Item> items;
    protected Item output;

    public Array<Item> getItems() {
        return items;
    }

    public Item getOutput() {
        return output;
    }
}
