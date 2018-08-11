package com.exedo.claustrophobia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.exedo.claustrophobia.Claustrophobia;
import com.exedo.claustrophobia.sprites.Player;
import com.exedo.claustrophobia.sprites.items.TestItem;
import com.exedo.claustrophobia.ui.inventory.Inventory;
import com.exedo.claustrophobia.ui.inventory.InventoryActor;
import com.exedo.claustrophobia.ui.inventory.Slot;

public class GameScreen implements Screen {
    private Claustrophobia game;

    private OrthographicCamera cam;
    private FitViewport port;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Player player;

    private static Stage stage;
    private InventoryActor inventoryActor;
    private Inventory inventory;

    private TestItem testItem;

    public GameScreen(Claustrophobia game) {
        this.game = game;

        cam = new OrthographicCamera();
        port = new FitViewport(Claustrophobia.V_WIDTH, Claustrophobia.V_HEIGHT, cam);
        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("test.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        player = new Player(port.getWorldWidth() / 2, port.getWorldHeight() / 2, game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin skin = game.getAssets().get("skins/uiskin.json", Skin.class);
        DragAndDrop dragAndDrop = new DragAndDrop();
        inventory = new Inventory();
        inventoryActor = new InventoryActor(inventory, dragAndDrop, skin);
        stage.addActor(inventoryActor);

        testItem = new TestItem(game);
        inventory.store(testItem, 25);
    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setY((player.getY() + 5));
            player.setDirection(Player.DIRECTION.BACKWARD);
            player.setState(Player.STATE.WALKING);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setY((player.getY() - 5));
            player.setDirection(Player.DIRECTION.FORWARD);
            player.setState(Player.STATE.WALKING);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setX((player.getX() - 5));
            player.setDirection(Player.DIRECTION.LEFT);
            player.setState(Player.STATE.WALKING);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setX((player.getX() + 5));
            player.setDirection(Player.DIRECTION.RIGHT);
            player.setState(Player.STATE.WALKING);
        } else {
            player.setState(Player.STATE.STANDING);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            inventoryActor.setVisible(!inventoryActor.isVisible());
        }
    }

    public void update(float delta) {
        handleInput();

        cam.position.set(player.getX(), player.getY(), 0);
        cam.update();
        renderer.setView(cam);
        player.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        player.draw(game.getBatch());
        game.getBatch().end();

        stage.getBatch().setProjectionMatrix(cam.combined);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        stage.getViewport().update(width, height);
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

    }
}
