package com.exedo.claustrophobia.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.exedo.claustrophobia.screens.GameScreen;
import com.exedo.claustrophobia.sprites.Player;

public class CollisionHandler {
    private TiledMap map;

    public CollisionHandler(GameScreen screen) {
        map = screen.getMap();
    }

    public boolean isColliding(Player player) {
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (player.getBoundingRectangle().overlaps(rect)) {
                return true;
            }
        }

        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (player.getBoundingRectangle().overlaps(rect)) {
                return true;
            }
        }

        return false;
    }
}
