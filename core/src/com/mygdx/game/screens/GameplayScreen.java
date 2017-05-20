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
import com.mygdx.game.ui.ResetScoreButton;


public class GameplayScreen extends AbstractScreen{

    private Image backgroundImage;
    private Image button1, button2, button3, button4, button5, button6;
    private Cleric cleric;
    private Ranger ranger;
    private Paladin paladin;
    
    private Skeleton skeleton;
    private Zombie zombie;
    private Vampire vampire;

    private ResetScoreButton resetScoreButton;
    private FlyingObject flyingObject1;

    private final static int WIDTH = 100;
    private final static int HEIGHT = 100;

    private   static int STARTING_X = 210;
    private   final static int STARTING_Y = 10;
    public GameplayScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        initBackground();
        initPlayer();
        initEnemies();
        initResetScoreButton();
        initFlyObjects();
        initButtons();
        }

    private void initButtons() {
        button1 = new Image(new Texture("button1.png"));
        stage.addActor(button1);
        button1.setOrigin(WIDTH / 2, HEIGHT / 2);
        button1.setSize(WIDTH,HEIGHT);
        button1.setPosition(STARTING_X,STARTING_Y);
        STARTING_X+=150;

        button2 = new Image(new Texture("button2.png"));
        stage.addActor(button2);
        button2.setOrigin(WIDTH / 2, HEIGHT / 2);
        button2.setSize(WIDTH,HEIGHT);
        button2.setPosition(STARTING_X,STARTING_Y);
        STARTING_X+=150;

        button3 = new Image(new Texture("button3.png"));
        stage.addActor(button3);
        button3.setOrigin(WIDTH / 2, HEIGHT / 2);
        button3.setSize(WIDTH,HEIGHT);
        button3.setPosition(STARTING_X,STARTING_Y);
        STARTING_X+=150;

        button4 = new Image(new Texture("button4.png"));
        stage.addActor(button4);
        button4.setOrigin(WIDTH / 2, HEIGHT / 2);
        button4.setSize(WIDTH,HEIGHT);
        button4.setPosition(STARTING_X,STARTING_Y);
        STARTING_X+=150;

        button5 = new Image(new Texture("button5.png"));
        stage.addActor(button5);
        button5.setOrigin(WIDTH / 2, HEIGHT / 2);
        button5.setSize(WIDTH,HEIGHT);
        button5.setPosition(STARTING_X,STARTING_Y);

        STARTING_X+=150;
        button6 = new Image(new Texture("button6.png"));
        stage.addActor(button6);
        button6.setOrigin(WIDTH / 2, HEIGHT / 2);
        button6.setSize(WIDTH,HEIGHT);
        button6.setPosition(STARTING_X,STARTING_Y);

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
        backgroundImage = new Image(new Texture("background1.png"));
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
        //
        stage.act();
    }
}
