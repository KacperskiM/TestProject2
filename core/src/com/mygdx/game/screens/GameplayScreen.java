package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.MyGame;
import com.mygdx.game.com.mygdx.game.enemies.Skeleton;
import com.mygdx.game.com.mygdx.game.enemies.Vampire;
import com.mygdx.game.com.mygdx.game.enemies.Zombie;
import com.mygdx.game.entities.FlyingObject;
import com.mygdx.game.entities.Cleric;
import com.mygdx.game.entities.Paladin;
import com.mygdx.game.entities.Ranger;
import com.mygdx.game.ui.IClickCallback;
import com.mygdx.game.ui.skillButtons.SkillButton1;
import com.mygdx.game.ui.skillButtons.SkillButton2;
import com.mygdx.game.ui.skillButtons.SkillButton3;
import com.mygdx.game.ui.skillButtons.SkillButton4;
import com.mygdx.game.ui.skillButtons.SkillButton5;
import com.mygdx.game.ui.skillButtons.SkillButton6;

import java.util.ArrayList;


public class GameplayScreen extends AbstractScreen{

    protected MyGame game;

    private Image backgroundImage;
    private Cleric cleric;
    private Ranger ranger;
    private Paladin paladin;

    private Skeleton skeleton;
    private Zombie zombie;
    private Vampire vampire;

    private SkillButton1 skillButton1;
    private SkillButton2 skillButton2;
    private SkillButton3 skillButton3;
    private SkillButton4 skillButton4;
    private SkillButton5 skillButton5;
    private SkillButton6 skillButton6;

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
        initFlyObjects();
        initSkillButtons();

        }



    private void initSkillButtons() {
        skillButton1 = new SkillButton1(this, new IClickCallback() {
            @Override
            public void onClick() {

            }
        });

        skillButton2 = new SkillButton2(this, new IClickCallback() {
            @Override
            public void onClick() {

            }
        });

        skillButton3 = new SkillButton3(this, new IClickCallback() {
            @Override
            public void onClick() {

            }
        });

        skillButton4 = new SkillButton4(new IClickCallback() {
            @Override
            public void onClick() {

            }
        });

        skillButton5 = new SkillButton5(new IClickCallback() {
            @Override
            public void onClick() {

            }
        });

        skillButton6 = new SkillButton6(new IClickCallback() {
            @Override
            public void onClick() {

            }
        });
        stage.addActor(skillButton1);
        stage.addActor(skillButton2);
        stage.addActor(skillButton3);
        stage.addActor(skillButton4);
        stage.addActor(skillButton5);
        stage.addActor(skillButton6);
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


    private void initPlayer() {

        paladin = new Paladin();
        stage.addActor(paladin);

        cleric = new Cleric();
        stage.addActor(cleric);

        ranger = new Ranger();
        stage.addActor(ranger);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        if(getTurnToken()==false){
            setPlayersUnselected();
        }
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();


    }

    private void setPlayersUnselected() {
        paladin.setUnSelected();
        cleric.setUnSelected();
        ranger.setUnSelected();
    }

    public Paladin getPaladin(){
        return paladin;
    }

    public Cleric getCleric(){
        return cleric;
    }

    public Ranger getRanger(){
        return ranger;
    }
    private void update() {
        stage.act();

    }
}
