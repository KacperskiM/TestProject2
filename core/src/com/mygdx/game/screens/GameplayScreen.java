package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Player;
import com.mygdx.game.ui.IClickCallback;
import com.mygdx.game.ui.PlayerButton;
import com.mygdx.game.ui.ResetScoreButton;


public class GameplayScreen extends AbstractScreen{

    private Player player;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private Label scoreLabel, resetLabel;

    public GameplayScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void init() {
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

        resetScoreButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.resetGameScore();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void initScoreLabel() {
        Label.LabelStyle labelStyle = new LabelStyle();
        labelStyle.font=new BitmapFont();
        scoreLabel = new Label("", labelStyle );
        scoreLabel.setX(20);
        scoreLabel.setY(650);
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
        stage.draw();
        spriteBatch.end();

    }

    private void update() {
        scoreLabel.setText("Score: " + game.getPoints());
        stage.act();
    }
}
