package com.exedo.claustrophobia.ui.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.exedo.claustrophobia.Claustrophobia;
import com.exedo.claustrophobia.sprites.items.Item;


public class InventoryActor extends Dialog {
    private Array<Slot> craftingSlots;
    private Table craftingTable;

    private CraftingHandler craftingHandler;
    private Slot outputSlot;

    private Inventory inventory;

    public InventoryActor(Inventory inventory, DragAndDrop dragAndDrop, Skin skin, Claustrophobia game) {
        super("Inventory", skin);

        craftingSlots = new Array<Slot>(2);
        craftingTable = new Table();
        craftingTable.setFillParent(true);

        craftingHandler = new CraftingHandler(game);

        this.inventory = inventory;

        TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
        getButtonTable().add(closeButton).height(getPadTop());

        setPosition(400, 100);
        setScale(4);
        defaults().space(8);
        row().fill().expandX();

        add(craftingTable);

        for (int i = 0; i < 2; i++) {
            Slot slot = new Slot(null, 1);
            craftingSlots.add(slot);
            SlotActor slotActor = new SlotActor(skin, slot);
            dragAndDrop.addSource(new SlotSource(slotActor));
            dragAndDrop.addTarget(new SlotTarget(slotActor));
            add(slotActor).expandX().fillX();
        }

        outputSlot = new Slot(null, 1);
        SlotActor outputSlotActor = new SlotActor(skin, outputSlot);
        dragAndDrop.addSource(new SlotSource(outputSlotActor));
        add(outputSlotActor).expandX().fillX();

        row();

        int i = 0;
        for (Slot slot : inventory.getSlots()) {
            SlotActor slotActor = new SlotActor(skin, slot);
            dragAndDrop.addSource(new SlotSource(slotActor));
            dragAndDrop.addTarget(new SlotTarget(slotActor));
            add(slotActor);

            i++;
            if (i % 5 == 0) {
                row();
            }
        }

        pack();

        setVisible(false);
    }

    public void checkCrafting() {
        Item itemOne = null, itemTwo = null;
        for (Slot slot : craftingSlots) {
            if (!slot.isEmpty()) {
                if (itemOne == null) itemOne = slot.getItem();
                else if (itemTwo == null) itemTwo = slot.getItem();
            }
        }
        if (itemOne != null && itemTwo != null) {
            outputSlot.add(craftingHandler.checkRecipe(itemOne, itemTwo), 1);
            if (!outputSlot.isEmpty()) {
                for (Slot slot : craftingSlots) {
                    slot.take(slot.getAmount());
                }
            }
        }
    }
}
