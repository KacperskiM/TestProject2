package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.ai.Ai;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.allies.Cleric;
import com.mygdx.game.entities.allies.FlyingObject;
import com.mygdx.game.entities.allies.Paladin;
import com.mygdx.game.entities.allies.Ranger;
import com.mygdx.game.entities.enemies.Skeleton;
import com.mygdx.game.entities.enemies.Vampire;
import com.mygdx.game.entities.enemies.Zombie;
import com.mygdx.game.ui.IClickCallback;
import com.mygdx.game.ui.skillButtons.SkillButton1;
import com.mygdx.game.ui.skillButtons.SkillButton2;
import com.mygdx.game.ui.skillButtons.SkillButton3;
import com.mygdx.game.ui.skillButtons.SkillButton4;
import com.mygdx.game.ui.skillButtons.SkillButton5;
import com.mygdx.game.ui.skillButtons.SkillButton6;

import java.util.ArrayList;


public class GameplayScreen extends AbstractScreen {

    //protected MyGame game;

    private ArrayList<Entity> playerCharacterList;
    private ArrayList<Entity> enemyCharacterList;

    private ArrayList<Entity> deadEnemiesList;


    private Image backgroundImage;
    private Cleric cleric;
    private Ranger ranger;
    private Paladin paladin;

    private Skeleton skeleton;
    private Zombie zombie;
    private Zombie resurrectedZombie;
    private Vampire vampire;

    private Ai ai;

    private SkillButton1 skillButton1;
    private SkillButton2 skillButton2;
    private SkillButton3 skillButton3;
    private SkillButton4 skillButton4;
    private SkillButton5 skillButton5;
    private SkillButton6 skillButton6;

    private FlyingObject flyingObject1;


    private static int[] allyPositionArray = {300, 200, 100};
    private static int[] enemyPositionArray = {650, 750, 850};


    GameplayScreen(MyGame game) {
        super(game);
    }




    @Override
    protected void init() {
        playerCharacterList = new ArrayList<>();
        enemyCharacterList = new ArrayList<>();
        deadEnemiesList = new ArrayList<>();


        initBackground();
        initPlayer();
        initEnemies();
        initAi();
        //initFlyObjects();
        initSkillButtons();
        initHealthBars();
        initMusic();

        startGame();

    }

    private void initHealthBars() {

    }

    private void initMusic() {
        Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.wav"));
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();
    }

    private void startGame() {
        for (int i = 0; i < enemyCharacterList.size(); i++) {
            enemyCharacterList.get(i).setUnselected();
            playerCharacterList.get(i).setUnselected();
        }
        playerCharacterList.get(0).setSelected();

    }

    private void initAi() {
        this.ai = new Ai(this);
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

        skillButton4 = new SkillButton4(this, new IClickCallback() {
            @Override
            public void onClick() {

            }
        });

        skillButton5 = new SkillButton5(this, new IClickCallback() {
            @Override
            public void onClick() {

            }
        });

        skillButton6 = new SkillButton6(this, new IClickCallback() {
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

    public ArrayList<Entity> getEnemyCharacterList() {
        return enemyCharacterList;
    }

    private void initFlyObjects() {
        flyingObject1 = new com.mygdx.game.entities.allies.FlyingObject(com.mygdx.game.entities.allies.FlyingObject.BAT);
        stage.addActor(flyingObject1);
        flyingObject1.fly();
    }

    private void initBackground() {
        backgroundImage = new Image(new Texture("background1.png"));
        stage.addActor(backgroundImage);
    }

    private void initEnemies() {
        initSkeleton();
        initZombie();
        initVampire();

    }

    private void initVampire() {
        vampire = new Vampire(this);
        stage.addActor(vampire);
        vampire.createHealthBar();
        enemyCharacterList.add(vampire);
        vampire.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (int i = 0; i < enemyCharacterList.size(); i++)
                    enemyCharacterList.get(i).setUnselected();
                for (int i = 0; i < playerCharacterList.size(); i++)
                    playerCharacterList.get(i).setUnselected();

                playerCharacterList.get(0).setSelected();
                if (!vampire.getDead())
                    vampire.setSelected();
            }
        });
    }

    private void initZombie() {
        zombie = new Zombie(this, false);
        stage.addActor(zombie);
        zombie.createHealthBar();
        enemyCharacterList.add(zombie);
        zombie.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (int i = 0; i < enemyCharacterList.size(); i++)
                    enemyCharacterList.get(i).setUnselected();
                for (int i = 0; i < playerCharacterList.size(); i++)
                    playerCharacterList.get(i).setUnselected();

                playerCharacterList.get(0).setSelected();
                if (!zombie.getDead())
                    zombie.setSelected();
            }
        });
    }

    public void initResurrectedZombie() {
        resurrectedZombie = new Zombie(this, true);
        stage.addActor(resurrectedZombie);
        resurrectedZombie.createHealthBar();
        enemyCharacterList.add(resurrectedZombie);
        resurrectedZombie.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (int i = 0; i < enemyCharacterList.size(); i++)
                    enemyCharacterList.get(i).setUnselected();
                for (int i = 0; i < playerCharacterList.size(); i++)
                    playerCharacterList.get(i).setUnselected();

                playerCharacterList.get(0).setSelected();
                if (!resurrectedZombie.getDead())
                    resurrectedZombie.setSelected();
            }
        });
    }

    private void initSkeleton() {
        skeleton = new Skeleton(this);
        stage.addActor(skeleton);
        skeleton.createHealthBar();
        enemyCharacterList.add(skeleton);
        skeleton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (int i = 0; i < enemyCharacterList.size(); i++)
                    enemyCharacterList.get(i).setUnselected();
                for (int i = 0; i < playerCharacterList.size(); i++)
                    playerCharacterList.get(i).setUnselected();

                playerCharacterList.get(0).setSelected();
                if (!skeleton.getDead())
                    skeleton.setSelected();
            }
        });
    }

    private void initPlayer() {

        paladin = new Paladin(this);
        stage.addActor(paladin);
        paladin.createHealthBar();
        playerCharacterList.add(paladin);
        paladin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!getTurnToken())
                    return;

                for (int i = 0; i < playerCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();

                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                if (playerCharacterList.get(0) instanceof Paladin)
                    paladin.setToBuffSelected();
                else
                    paladin.setToBuff();
            }
        });

        cleric = new Cleric(this);
        stage.addActor(cleric);
        cleric.createHealthBar();
        playerCharacterList.add(cleric);
        cleric.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!getTurnToken())
                    return;

                for (int i = 0; i < playerCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();
                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                if (playerCharacterList.get(0) instanceof Cleric)
                    cleric.setToBuffSelected();
                else
                    cleric.setToBuff();
            }
        });


        ranger = new Ranger(this);
        stage.addActor(ranger);
        ranger.createHealthBar();
        playerCharacterList.add(ranger);
        ranger.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!getTurnToken())
                    return;

                for (int i = 0; i < playerCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();

                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                if (playerCharacterList.get(0) instanceof Ranger)
                    ranger.setToBuffSelected();
                else
                    ranger.setToBuff();
            }
        });


    }

    private void refreshStage() {
        spriteBatch.begin();
        stage.act();
        stage.draw();
        spriteBatch.end();
    }

    private void setPlayersUnselected() {
        for (int i = 0; i < playerCharacterList.size(); i++)
            playerCharacterList.get(i).setUnselected();
    }


    public void playTurn() {
        if (playerCharacterList.get(0).getBleeding() > 0)
            playerCharacterList.get(0).dealBleedingDamage();
        if (playerCharacterList.get(0).getPoisoned() > 0)
            playerCharacterList.get(0).dealPoisonDamage();

        swapList();
        updateLocation();

        for (int i = 0; i < playerCharacterList.size(); i++) {
            playerCharacterList.get(i).setUnselected();
        }


        System.out.println("END OF PLAYER'S TURN");
        System.out.println("======================================");

        tossTurnToken();


        setPlayersUnselected();
        ai.makeAction(getEnemyCharacterList().get(0));
        System.out.println("END OF ENEMY'S TURN");
        System.out.println("======================================");

        for (int i = 0; i < enemyCharacterList.size(); i++) {
            enemyCharacterList.get(i).setUnselected();
        }
        if (playerCharacterList.size() > 0) {
            playerCharacterList.get(0).setSelected();
            swapList();
            updateLocation();
            tossTurnToken();
        } else {
            System.exit(0);
        }
    }

    private void updateLocation() {
        if (turnToken) {
            for (int i = 0; i < playerCharacterList.size(); i++)
                playerCharacterList.get(i).move(allyPositionArray[i]);
        } else {
            for (int i = 0; i < enemyCharacterList.size(); i++)
                enemyCharacterList.get(i).moveEnemy(enemyPositionArray[i]);
        }
    }

    private void swapList() {
        if (turnToken) {
            playerCharacterList.add(playerCharacterList.get(0));
            playerCharacterList.remove(0);
        }
        else {
            enemyCharacterList.add(enemyCharacterList.get(0));
            enemyCharacterList.remove(0);
        }
    }


    public Entity getSelectedSource() {
        for (int i = 0; i < playerCharacterList.size(); i++)
            if (playerCharacterList.get(i).getIsSelected() == 1)
                return playerCharacterList.get(i);


        for (int i = 0; i < enemyCharacterList.size(); i++)
            if (enemyCharacterList.get(i).getIsSelected() == 1)
                return enemyCharacterList.get(i);

        return null;
    }

    public Entity getSelectedTarget() {
        for (int i = 0; i < playerCharacterList.size(); i++)
            if (playerCharacterList.get(i).getIsSelected() == 3)
                return playerCharacterList.get(i);

        for (int i = 0; i < enemyCharacterList.size(); i++)
            if (enemyCharacterList.get(i).getIsSelected() == 1) {
                return enemyCharacterList.get(i);
            }

        return null;
    }


    public ArrayList<Entity> getPlayerCharacterList() {
        return playerCharacterList;
    }


    public static int[] getAllyPositionArray() {
        return allyPositionArray;
    }

    public static int[] getEnemyPositionArray() {
        return enemyPositionArray;
    }

    public ArrayList<Entity> getDeadEnemiesList() {
        return deadEnemiesList;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        refreshStage();


    }
}
