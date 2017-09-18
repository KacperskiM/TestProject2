package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
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
import com.mygdx.game.ui.skillButtons.SkillButton1;
import com.mygdx.game.ui.skillButtons.SkillButton2;
import com.mygdx.game.ui.skillButtons.SkillButton3;
import com.mygdx.game.ui.skillButtons.SkillButton4;
import com.mygdx.game.ui.skillButtons.SkillButton5;
import com.mygdx.game.ui.skillButtons.SkillButton6;
import com.mygdx.game.ui.tooltips.PaladinTooltip;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


public class GameplayScreen extends AbstractScreen {


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

    public final static int slot1 = (int) Math.ceil(Gdx.graphics.getWidth() / 10);
    public final static int slot2 = (int) Math.ceil(Gdx.graphics.getWidth() / 80 + 2 * Gdx.graphics.getWidth() / 10);
    public final static int slot3 = (int) Math.ceil(2 * (Gdx.graphics.getWidth() / 80) + 3 * Gdx.graphics.getWidth() / 10);
    public final static int slot4 = (int) Math.ceil(14 * (Gdx.graphics.getWidth() / 80) + 4 * Gdx.graphics.getWidth() / 10);
    public final static int slot5 = (int) Math.ceil(15 * (Gdx.graphics.getWidth() / 80) + 5 * Gdx.graphics.getWidth() / 10);
    public final static int slot6 = (int) Math.ceil(16 * (Gdx.graphics.getWidth() / 80) + 6 * Gdx.graphics.getWidth() / 10);


    private static int[] allyPositionArray = {slot3, slot2, slot1};
    private static int[] enemyPositionArray = {slot4, slot5, slot6};


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
        initMusic();

        startGame();

    }


    private void initMusic() {
        Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.wav"));
        backgroundMusic.setVolume(0.2f);
        backgroundMusic.setLooping(true);
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

        skillButton1 = new SkillButton1(this);
        skillButton2 = new SkillButton2(this);
        skillButton3 = new SkillButton3(this);
        skillButton4 = new SkillButton4(this);
        skillButton5 = new SkillButton5(this);
        skillButton6 = new SkillButton6(this);

        skillButton1.addListener(new ActorGestureListener());
        skillButton2.addListener(new ActorGestureListener());
        skillButton3.addListener(new ActorGestureListener());
        skillButton4.addListener(new ActorGestureListener());
        skillButton5.addListener(new ActorGestureListener());
        skillButton6.addListener(new ActorGestureListener());

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

        Pixmap backgroundPixmap = new Pixmap(Gdx.files.internal("background1.png"));
        Pixmap backgroundResized = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), backgroundPixmap.getFormat());
        backgroundResized.drawPixmap(backgroundPixmap, 0, 0, backgroundPixmap.getWidth(), backgroundPixmap.getHeight(), 0, 0, backgroundResized.getWidth(), backgroundResized.getHeight());
        backgroundImage = new Image(new Texture(backgroundResized));
        backgroundPixmap.dispose();
        backgroundResized.dispose();

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
        vampire.createManaBar();
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
        zombie.createManaBar();
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
        resurrectedZombie.createHealthBar2();
        resurrectedZombie.createManaBar2();
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
        skeleton.createManaBar();
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
        paladin.createManaBar();
        paladin.createStatusIcons();
        playerCharacterList.add(paladin);

        paladin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!getTurnToken())
                    return;

                for (int i = 0; i < enemyCharacterList.size(); i++)
                    enemyCharacterList.get(i).setUnselected();

                for (int i = 0; i < playerCharacterList.size(); i++) {
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
        cleric.createManaBar();
        cleric.createStatusIcons();
        playerCharacterList.add(cleric);
        cleric.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!getTurnToken())
                    return;
                for (int i = 0; i < enemyCharacterList.size(); i++)
                    enemyCharacterList.get(i).setUnselected();

                for (int i = 0; i < playerCharacterList.size(); i++) {
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
        ranger.createManaBar();
        ranger.createStatusIcons();
        playerCharacterList.add(ranger);
        ranger.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!getTurnToken())
                    return;
                for (int i = 0; i < enemyCharacterList.size(); i++)
                    enemyCharacterList.get(i).setUnselected();

                for (int i = 0; i < playerCharacterList.size(); i++) {
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
        RunnableAction run1 = new RunnableAction();
        run1.setRunnable(new Runnable() {
            @Override
            public void run() {
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


                if (enemyCharacterList.size() == 0)
                    System.exit(0);
                tossTurnToken();
            }
        });
        RunnableAction run2 = new RunnableAction();
        run2.setRunnable(new Runnable() {
            @Override
            public void run() {

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
                    //TODO: check if game ended
                    System.exit(0);
                }
            }
        });
        DelayAction delayAction = new DelayAction();
        delayAction.setDuration(2f);
        stage.addAction(sequence(run1,delayAction, run2));
    }

    private void updateLocation() {
        for (int i = 0; i < playerCharacterList.size(); i++)
            playerCharacterList.get(i).move(allyPositionArray[i]);
        for (int i = 0; i < enemyCharacterList.size(); i++)
            enemyCharacterList.get(i).moveEnemy(enemyPositionArray[i]);

    }

    private void swapList() {
        if (turnToken) {
            playerCharacterList.add(playerCharacterList.get(0));
            playerCharacterList.remove(0);
        } else {
            enemyCharacterList.add(enemyCharacterList.get(0));
            enemyCharacterList.remove(0);
        }
    }


    public Entity getSelectedSource() {
        for (int i = 0; i < playerCharacterList.size(); i++)
            if (playerCharacterList.get(i).getIsSelected() == 1 || playerCharacterList.get(i).getIsSelected() == 3)
                return playerCharacterList.get(i);


        for (int i = 0; i < enemyCharacterList.size(); i++)
            if (enemyCharacterList.get(i).getIsSelected() == 1)
                return enemyCharacterList.get(i);

        return null;
    }

    public Entity getSelectedTarget() {
        for (int i = 0; i < playerCharacterList.size(); i++)
            if (playerCharacterList.get(i).getIsSelected() == 3 || playerCharacterList.get(i).getIsSelected() == 2)
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



    public Cleric getCleric() {
        return cleric;
    }

    public Ranger getRanger() {
        return ranger;
    }

    public Paladin getPaladin() {
        return paladin;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        refreshStage();


    }
}
