package com.exedo.claustrophobia.ui.inventory;

import com.badlogic.gdx.utils.Array;
import com.exedo.claustrophobia.sprites.items.Item;

public class Inventory {
    private Array<Slot> slots;

    public Inventory() {
        slots = new Array<Slot>(25);
        for (int i = 0; i < 25; i++) {
            slots.add(new Slot(null, 0));
        }
    }

    public int checkInventory(Item item) {
        int amount = 0;

        for (Slot slot : slots) {
            if (slot.getItem() == item) {
                amount += slot.getAmount();
            }
        }

        return amount;
    }

    public boolean store(Item item, int amount) {
        // Check if there's a slot with the item
        Slot itemSlot = firstSlotWithItem(item);
        if (itemSlot != null) {
            itemSlot.add(item, amount);
            return true;
        } else {
            // Check for empty available slot
            Slot emptySlot = firstSlotWithItem(null);
            if (emptySlot != null) {
                emptySlot.add(item, amount);
                return true;
            }
        }

        return  false;
    }

    public Array<Slot> getSlots() {
        return slots;
    }

    private Slot firstSlotWithItem(Item item) {
        for (Slot slot : slots) {
            if (slot.getItem() == item) {
                return slot;
            }
        }
        return null;
    }
}
