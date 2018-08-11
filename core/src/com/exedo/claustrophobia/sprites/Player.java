package com.exedo.claustrophobia.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exedo.claustrophobia.Claustrophobia;

public class Player extends Sprite {
    public enum DIRECTION {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

    public enum STATE {
        STANDING,
        WALKING
    }


    private Animation<TextureRegion> forwardWalk;
    private Animation<TextureRegion> backwardsWalk;
    private Animation<TextureRegion> rightWalk;
    private Animation<TextureRegion> leftWalk;

    private DIRECTION direction;
    private STATE state;

    private float elapsedTime;

    public Player(float x, float y, Claustrophobia game) {
        TextureRegion[][]tmp = TextureRegion.split(game.getAssets().get("spritesheets/player.png", Texture.class),
                game.getAssets().get("spritesheets/player.png", Texture.class).getWidth() / 8,
                game.getAssets().get("spritesheets/player.png", Texture.class).getHeight() / 2);

        TextureRegion[] forwardFrames = new TextureRegion[4];
        TextureRegion[] backwardFrames = new TextureRegion[4];
        TextureRegion[] rightFrames = new TextureRegion[4];
        TextureRegion[] leftFrames = new TextureRegion[4];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                backwardFrames[index++] = tmp[i][j];
            }
        }

        index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 4; j < 8; j++) {
                forwardFrames[index++] = tmp[i][j];
            }
        }

        index = 0;
        for (int i = 1; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                leftFrames[index++] = tmp[i][j];
            }
        }

        index = 0;
        for (int i = 1; i < 2; i++) {
            for (int j = 4; j < 8; j++) {
                rightFrames[index++] = tmp[i][j];
            }
        }

        forwardWalk = new Animation<TextureRegion>(1/7f, forwardFrames);
        backwardsWalk = new Animation<TextureRegion>(1/7f, backwardFrames);
        rightWalk = new Animation<TextureRegion>(1/7f, rightFrames);
        leftWalk = new Animation<TextureRegion>(1/7f, leftFrames);

        direction = DIRECTION.FORWARD;
        state = STATE.STANDING;

        setRegion(forwardWalk.getKeyFrame(0));
        setPosition(x, y);
        setBounds(0, 0, 32, 32);
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public void update(float delta) {
        elapsedTime += delta;

        if (state == STATE.STANDING) {
            switch (direction) {
                case FORWARD:
                    setRegion(forwardWalk.getKeyFrame(0));
                    break;
                case BACKWARD:
                    setRegion(backwardsWalk.getKeyFrame(0));
                    break;
                case RIGHT:
                    setRegion(rightWalk.getKeyFrame(0));
                    break;
                case LEFT:
                    setRegion(leftWalk.getKeyFrame(0));
                    break;
            }
        } else {
            switch (direction) {
                case FORWARD:
                    setRegion(forwardWalk.getKeyFrame(elapsedTime, true));
                    break;
                case BACKWARD:
                    setRegion(backwardsWalk.getKeyFrame(elapsedTime, true));
                    break;
                case RIGHT:
                    setRegion(rightWalk.getKeyFrame(elapsedTime, true));
                    break;
                case LEFT:
                    setRegion(leftWalk.getKeyFrame(elapsedTime, true));
                    break;
            }
        }

        setPosition(getX(), getY());
    }
}
