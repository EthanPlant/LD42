package com.exedo.claustrophobia.ui.inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.exedo.claustrophobia.sprites.items.Item;

public class SlotSource extends Source {
    private Slot sourceSlot;

    public SlotSource(SlotActor actor) {
        super(actor);
        this.sourceSlot = actor.getSlot();
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        if (sourceSlot.getAmount() == 0) {
            return null;
        }

        Payload payload = new Payload();
        Slot payloadSlot = new Slot(sourceSlot.getItem(), sourceSlot.getAmount());
        sourceSlot.take(sourceSlot.getAmount());
        payload.setObject(payloadSlot);

        TextureRegion icon = payloadSlot.getItem().getTexture();

        Actor dragActor = new Image(icon);
        dragActor.setScale(5);
        payload.setDragActor(dragActor);

        Actor validDragActor = new Image(icon);
        validDragActor.setScale(5);
        // validDragActor.setColor(0, 1, 0, 1);
        payload.setValidDragActor(validDragActor);

        Actor invalidDragActor = new Image(icon);
        invalidDragActor.setScale(5);
        // invalidDragActor.setColor(1, 0, 0, 1);
        payload.setInvalidDragActor(invalidDragActor);

        return payload;
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        Slot payloadSlot = (Slot) payload.getObject();
        if (target != null) {
            Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
            if (targetSlot.getItem() == payloadSlot.getItem() || targetSlot.getItem() == null) {
                targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
            } else {
                Item targetType = targetSlot.getItem();
                int targetAmount = targetSlot.getAmount();
                targetSlot.take(targetAmount);
                targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
                sourceSlot.add(targetType, targetAmount);
            }
        } else {
            sourceSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
        }
    }
}
