package com.exedo.claustrophobia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.exedo.claustrophobia.Claustrophobia;

public class Titlescreen implements Screen {

    private Claustrophobia game;

    private OrthographicCamera cam;
    private FitViewport port;

    public Titlescreen(Claustrophobia game) {
        this.game = game;

        cam = new OrthographicCamera();
        port = new FitViewport(Claustrophobia.V_WIDTH, Claustrophobia.V_HEIGHT, cam);
        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        handleInput();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().draw(game.getAssets().get("textures/titlescreen.png", Texture.class), 0, 0, port.getWorldWidth(), port.getWorldHeight());
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.getAssets().unload("titlescreen.png");
    }
}
