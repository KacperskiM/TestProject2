package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGame;

/**
 * Created by Ja on 2017-05-06.
 */

public class SplashScreen extends AbstractScreen {
    
    private Texture splashImg;
    
    
    public SplashScreen(final MyGame game) {
        super(game);
        init();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GameplayScreen(game));
            }
        }, 1);
    }

    @Override
    protected void init() {
        Pixmap splashPixmap = new Pixmap(Gdx.files.internal("splash.png"));
        Pixmap splashResized = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), splashPixmap.getFormat());
        splashResized.drawPixmap(splashPixmap, 0, 0, splashPixmap.getWidth(), splashPixmap.getHeight(), 0, 0, splashResized.getWidth(), splashResized.getHeight());
        splashImg = new Texture(splashResized);
        splashPixmap.dispose();
        splashResized.dispose();
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg,0,0);
        spriteBatch.end();
    }

    public void tossTurnToken(){}
}
