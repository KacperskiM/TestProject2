package com.mygdx.game.screens;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Player;

/**
 * Created by Ja on 2017-05-06.
 */

public class GameplayScreen extends AbstractScreen{

    private Player player;

    public GameplayScreen(MyGdxGame game) {
        super(game);
        init();
        
    }

    private void init() {
        initPlayer();
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

    }

    private void update() {
        stage.act();
    }
}
