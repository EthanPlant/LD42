package com.exedo.claustrophobia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.exedo.claustrophobia.Claustrophobia;
import com.exedo.claustrophobia.sprites.InteractableObject;
import com.exedo.claustrophobia.sprites.Player;
import com.exedo.claustrophobia.sprites.objects.TestObject;
import com.exedo.claustrophobia.ui.inventory.InventoryActor;
import com.exedo.claustrophobia.utils.CollisionHandler;

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

    private Array<InteractableObject> objects;

    private CollisionHandler collisionHandler;

    public GameScreen(Claustrophobia game) {
        this.game = game;

        cam = new OrthographicCamera();
        port = new FitViewport(Claustrophobia.V_WIDTH, Claustrophobia.V_HEIGHT, cam);
        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/room.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        player = new Player(port.getWorldWidth() / 2, port.getWorldHeight() / 2, game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin skin = game.getAssets().get("skins/uiskin.json", Skin.class);
        DragAndDrop dragAndDrop = new DragAndDrop();
        inventoryActor = new InventoryActor(player.getInventory(), dragAndDrop, skin, game);
        stage.addActor(inventoryActor);

        objects = new Array<InteractableObject>();
        objects.add(new TestObject(300, 300, game));

        collisionHandler = new CollisionHandler(this);
    }

    @Override
    public void show() {

    }

    public TiledMap getMap() {
        return map;
    }

    public void handleInput(float delta) {
        float startX = player.getX();
        float startY = player.getY();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setY(player.getY() + 100 * delta);
            player.setDirection(Player.DIRECTION.BACKWARD);
            player.setState(Player.STATE.WALKING);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setY(player.getY() - 100 * delta);
            player.setDirection(Player.DIRECTION.FORWARD);
            player.setState(Player.STATE.WALKING);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setX(player.getX() - 100 * delta);
            player.setDirection(Player.DIRECTION.LEFT);
            player.setState(Player.STATE.WALKING);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setX(player.getX() + 100 * delta);
            player.setDirection(Player.DIRECTION.RIGHT);
            player.setState(Player.STATE.WALKING);
        } else {
            player.setState(Player.STATE.STANDING);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            inventoryActor.setVisible(!inventoryActor.isVisible());
        }

        if (collisionHandler.isColliding(player)) {
            player.setX(startX);
            player.setY(startY);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            for (InteractableObject i : objects) {
                if (player.getBoundingRectangle().overlaps(i.getBoundingRectangle())) {
                    i.onInteract(player);
                }
            }
        }
    }

    public void update(float delta) {
        handleInput(delta);

        inventoryActor.checkCrafting();

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
        game.getBatch().draw(player, player.getX() - 8, player.getY(), 32, 32);
        //player.draw(game.getBatch());
        for (InteractableObject i : objects) {
            i.draw(game.getBatch());
        }
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
