package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGame;
import com.mygdx.game.com.mygdx.game.enemies.Skeleton;
import com.mygdx.game.com.mygdx.game.enemies.Vampire;
import com.mygdx.game.com.mygdx.game.enemies.Zombie;
import com.mygdx.game.entities.FlyingObject;
import com.mygdx.game.entities.Cleric;
import com.mygdx.game.entities.Paladin;
import com.mygdx.game.entities.Ranger;
import com.mygdx.game.ui.IClickCallback;
import com.mygdx.game.ui.PlayerButton;
import com.mygdx.game.ui.ResetScoreButton;
import com.mygdx.game.ui.ScoreLabel;


public class GameplayScreen extends AbstractScreen{

    private Image backgroundImage;
    private Cleric cleric;
    private Ranger ranger;
    private Paladin paladin;
    
    private Skeleton skeleton;
    private Zombie zombie;
    private Vampire vampire;
    private PlayerButton playerButton;
    private ResetScoreButton resetScoreButton;
    private ScoreLabel scoreLabel;
    private FlyingObject flyingObject1;
    public GameplayScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        initBackground();
        initPlayer();
        initEnemies();
        initPlayerButton();
        initResetScoreButton();
        initScoreLabel();
        initFlyObjects();
        }

    private void initEnemies() {
        skeleton = new Skeleton();
        stage.addActor(skeleton);

        zombie = new Zombie();
        stage.addActor(zombie);

        vampire = new Vampire();
        stage.addActor(vampire);
    }

    private void initFlyObjects() {
        flyingObject1 = new FlyingObject(FlyingObject.BAT);
        stage.addActor(flyingObject1);
        flyingObject1.fly();
    }

    private void initBackground() {
        backgroundImage = new Image(new Texture("background.png"));
        stage.addActor(backgroundImage);
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
                //cleric.reactOnClick();
                game.addPoint();
            }
        });

        stage.addActor(playerButton);
    }


    private void initPlayer() {
        cleric = new Cleric();
        stage.addActor(cleric);

        ranger = new Ranger();
        stage.addActor(ranger);

        paladin = new Paladin();
        stage.addActor(paladin);
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
