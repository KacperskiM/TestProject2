package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Player;
import com.mygdx.game.ui.IClickCallback;
import com.mygdx.game.ui.PlayerButton;
import com.mygdx.game.ui.ResetScoreButton;
import com.mygdx.game.ui.ScoreLabel;


public class GameplayScreen extends AbstractScreen{

    private Texture backgroundTexture;

    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private ScoreLabel scoreLabel;

    public GameplayScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void init() {
        backgroundTexture = new Texture("background.png");
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initScoreLabel();
        }

    private void initResetScoreButton() {

        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
            game.resetGameScore();
            }
        });
        stage.addActor(resetScoreButton);
    }

    private void initScoreLabel() {
        scoreLabel = new ScoreLabel();
        stage.addActor(scoreLabel);
    }


    private void initPlayerButton() {

        playerButton=new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.addPoint();
            }
        });

        stage.addActor(playerButton);
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
        spriteBatch.draw(backgroundTexture,0,0);
        spriteBatch.end();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

    }

    private void update() {
        scoreLabel.setText("Score: " + game.getPoints());
        stage.act();
    }
}
