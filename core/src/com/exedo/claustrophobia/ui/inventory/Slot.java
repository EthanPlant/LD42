package com.exedo.claustrophobia.ui.inventory;

import com.badlogic.gdx.utils.Array;
import com.exedo.claustrophobia.sprites.items.Item;

public class Slot {
    private Item item;
    private int amount;

    private Array<SlotListener> slotListeners = new Array<SlotListener>();

    public Slot(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public boolean isEmpty() {
        return item == null || amount <= 0;
    }

    public void addListener(SlotListener listener) {
        slotListeners.add(listener);
    }

    public void removeListener(SlotListener listener) {
        slotListeners.removeValue(listener, true);
    }

    public boolean add(Item item, int amount) {
        if (this.item == item || this.item == null) {
            this.item = item;
            this.amount += amount;
            notifyListeners();
            return true;
        }

        return false;
    }

    public boolean take(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
            if (this.amount == 0) {
                item = null;
            }
            notifyListeners();
            return true;
        }

        return false;
    }

    private void notifyListeners() {
        for (SlotListener listener : slotListeners) {
            listener.hasChanged(this);
        }
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
