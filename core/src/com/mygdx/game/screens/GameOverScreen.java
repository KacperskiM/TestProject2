package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGame;

/**
 * Created by Ja on 2017-09-18.
 */

public class GameOverScreen extends AbstractScreen {
    private Image backgroundImage;

    public GameOverScreen(final MyGame game) {
        super(game);
        init();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GameplayScreen(game));
            }
        }, 3);
    }

    @Override
    protected void init() {
        Pixmap backgroundPixmap;

        if(game.getResult() == true )
             backgroundPixmap = new Pixmap(Gdx.files.internal("gameOverWon.jpg"));
        else
             backgroundPixmap = new Pixmap(Gdx.files.internal("gameOverLost.jpg"));


        Pixmap backgroundResized = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), backgroundPixmap.getFormat());
        backgroundResized.drawPixmap(backgroundPixmap, 0, 0, backgroundPixmap.getWidth(), backgroundPixmap.getHeight(), 0, 0, backgroundResized.getWidth(), backgroundResized.getHeight());
        backgroundImage = new Image(new Texture(backgroundResized));
        backgroundPixmap.dispose();
        backgroundResized.dispose();

        stage.addActor(backgroundImage);
    }

    private void refreshStage() {
        spriteBatch.begin();
        stage.act();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        refreshStage();
    }
}
