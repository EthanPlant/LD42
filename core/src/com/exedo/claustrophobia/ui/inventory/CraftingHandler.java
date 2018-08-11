package com.exedo.claustrophobia.ui.inventory;

import com.badlogic.gdx.utils.Array;
import com.exedo.claustrophobia.Claustrophobia;
import com.exedo.claustrophobia.recipes.ItemRecipe;
import com.exedo.claustrophobia.recipes.TestRecipe;
import com.exedo.claustrophobia.sprites.items.Item;

public class CraftingHandler {

    private Array<ItemRecipe> recipes;

    private Claustrophobia game;

    public CraftingHandler(Claustrophobia game) {
        this.game = game;
        recipes = new Array<ItemRecipe>();
        recipes.add(new TestRecipe(game));
    }

    public Item checkRecipe(Item itemOne, Item itemTwo) {
        for (ItemRecipe recipe : recipes) {
            for (Item item : recipe.getItems()) {
                if (itemOne != null && itemOne.getClass() == item.getClass()) itemOne = null;
                else if (itemTwo != null && itemTwo.getClass() == item.getClass()) itemTwo = null;
            }
            if (itemOne == null && itemTwo == null) {
                return recipe.getOutput();
            }
        }

        return null;
    }
}
