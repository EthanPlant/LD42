package com.exedo.claustrophobia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.exedo.claustrophobia.screens.Titlescreen;

public class Claustrophobia extends Game {
	private SpriteBatch batch;
	private AssetManager assets;
	public static int V_WIDTH = 640;
	public static int V_HEIGHT = 360;

	@Override
	public void create() {
		batch = new SpriteBatch();
		assets = new AssetManager();
		loadAssets();
		setScreen(new Titlescreen(this));
	}

	void loadAssets() {
		assets.load("textures/titlescreen.png", Texture.class);
		assets.load("spritesheets/player.png", Texture.class);
		assets.load("skins/uiskin.json", Skin.class);
		assets.load("spritesheets/items.atlas", TextureAtlas.class);
		assets.load("textures/testobj.png", Texture.class);
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