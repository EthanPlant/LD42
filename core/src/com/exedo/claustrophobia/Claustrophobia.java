package com.exedo.claustrophobia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exedo.claustrophobia.screens.Titlescreen;

public class Claustrophobia extends Game {

	private SpriteBatch batch;
	private AssetManager assets;
	public static int V_WIDTH = 1920;
	public static int V_HEIGHT = 1080;

	@Override
	public void create() {
		batch = new SpriteBatch();
		assets = new AssetManager();
		loadAssets();
		setScreen(new Titlescreen(this));
	}

	void loadAssets() {
		assets.load("textures/titlescreen.png", Texture.class);
		assets.finishLoading();
	}

	public SpriteBatch getBatch() {return batch; }
	public AssetManager getAssets() {return  assets; }

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		assets.dispose();
	}
}