package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGame;


public abstract class AbstractScreen implements Screen {

    protected MyGame game;
    protected Stage stage;
    private Boolean turnToken;
    private OrthographicCamera camera;
    protected SpriteBatch spriteBatch;

    public AbstractScreen(MyGame game) {
        this.game = game;
        this.turnToken=true;
        createCamera();
        stage = new Stage(new StretchViewport(MyGame.WIDTH, MyGame.HEIGHT,camera));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        init();
    }

    protected abstract void init();

    private void createCamera() {
        camera=new OrthographicCamera();
        camera.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        camera.update();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void dispose() {
        game.dispose();
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    public void tossTurnToken(){
        turnToken=!turnToken;
    }

    public Boolean getTurnToken(){
        return turnToken;
    }
}

