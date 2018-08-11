package com.exedo.claustrophobia.ui.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SlotActor extends ImageButton implements SlotListener {

    private Slot slot;

    private Skin skin;

    private SlotTooltip tooltip;

    public SlotActor(Skin skin, Slot slot) {
        super(createStyle(skin, slot));
        this.slot = slot;
        this.skin = skin;

        slot.addListener(this);

        tooltip = new SlotTooltip(slot, skin);
        tooltip.setTouchable(Touchable.disabled); // allows for mouse to hit tooltips in the top-right corner of the screen without flashing
        addListener(new TooltipListener(tooltip, true));
    }

    private static ImageButtonStyle createStyle(Skin skin, Slot slot) {
        TextureRegion image;
        if (slot.getItem() != null) {
            image = slot.getItem().getTexture();
        } else {
            TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("spritesheets/items.atlas"));
            image = atlas.findRegion("empty");
        }
        ImageButtonStyle style = new ImageButtonStyle(skin.get(ButtonStyle.class));
        style.imageUp = new TextureRegionDrawable(image);
        style.imageDown = new TextureRegionDrawable(image);

        return style;
    }

    public Slot getSlot() {
        return slot;
    }

    public SlotTooltip getTooltip() {
        return tooltip;
    }

    @Override
    public void hasChanged(Slot slot) {
        setStyle(createStyle(skin, slot));
    }
}