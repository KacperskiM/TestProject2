package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Player;


public class GameplayScreen extends AbstractScreen{

    private Player player;
    private Button playerButton;
    private Label scoreLabel;

    public GameplayScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void init() {
        initPlayer();
        initPlayerButton();
        initScoreLabel();
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
        playerButton = new Button(new ButtonStyle());
        playerButton.setWidth((460));
        playerButton.setHeight(360);
        playerButton.setX(10);
        playerButton.setY(170);
        playerButton.setDebug(true);

        stage.addActor(playerButton);
        playerButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                player.reactOnClick();
                game.addPoint();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

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
