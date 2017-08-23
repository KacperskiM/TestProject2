package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.allies.*;
import com.mygdx.game.entities.enemies.*;
import com.mygdx.game.ui.IClickCallback;
import com.mygdx.game.ui.skillButtons.*;
import java.util.ArrayList;


public class GameplayScreen extends AbstractScreen {

    protected MyGame game;

    private ArrayList<Entity> playerCharacterList;
    private ArrayList<Entity> enemyCharacterList;


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


    GameplayScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        playerCharacterList = new ArrayList<>();
        enemyCharacterList = new ArrayList<>();

        initBackground();
        initPlayer();
        initEnemies();
        initFlyObjects();
        initSkillButtons();

        initTurnToken();

    }

    private void initTurnToken() {
        tossTurnToken();

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
        skeleton = new Skeleton();
        stage.addActor(skeleton);
        enemyCharacterList.add(skeleton);
        skeleton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!getTurnToken())
                    return;

                for (int i = 0; i < enemyCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();
                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                skeleton.setSelected();
            }
        });

        zombie = new Zombie();
        stage.addActor(zombie);
        enemyCharacterList.add(zombie);
        zombie.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!getTurnToken())
                    return;

                for (int i = 0; i < enemyCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();
                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                zombie.setSelected();
            }
        });

        vampire = new com.mygdx.game.entities.enemies.Vampire();
        stage.addActor(vampire);
        enemyCharacterList.add(vampire);
        vampire.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!getTurnToken())
                    return;

                for (int i = 0; i < enemyCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();
                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                vampire.setSelected();
            }
        });
    }

    private void initPlayer() {

        paladin = new com.mygdx.game.entities.allies.Paladin();
        stage.addActor(paladin);
        playerCharacterList.add(paladin);
        paladin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!getTurnToken())
                    return;

                for (int i = 0; i < playerCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();

                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                if (playerCharacterList.get(0) instanceof com.mygdx.game.entities.allies.Paladin)
                    paladin.setToBuffSelected();
                else
                    paladin.setToBuff();
            }
        });

        cleric = new com.mygdx.game.entities.allies.Cleric();
        stage.addActor(cleric);
        playerCharacterList.add(cleric);
        cleric.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!getTurnToken())
                    return;

                for (int i = 0; i < playerCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();
                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                if (playerCharacterList.get(0) instanceof com.mygdx.game.entities.allies.Cleric)
                    cleric.setToBuffSelected();
                else
                    cleric.setToBuff();
            }
        });


        ranger = new com.mygdx.game.entities.allies.Ranger();
        stage.addActor(ranger);
        playerCharacterList.add(ranger);
        ranger.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!getTurnToken())
                    return;

                for (int i = 0; i < playerCharacterList.size(); i++) {
                    enemyCharacterList.get(i).setUnselected();

                    if (i != 0)
                        playerCharacterList.get(i).setUnselected();
                    else
                        playerCharacterList.get(i).setSelected();
                }
                if (playerCharacterList.get(0) instanceof com.mygdx.game.entities.allies.Ranger)
                    ranger.setToBuffSelected();
                else
                    ranger.setToBuff();        }
        });


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        if (!getTurnToken()) {
            setPlayersUnselected();
        }

        refreshStage();

    }


    private void refreshStage() {
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void setPlayersUnselected() {
        for (int i = 0; i < playerCharacterList.size(); i++)
            playerCharacterList.get(i).setUnselected();
    }


    private void update() {
        stage.act();

    }

    public void tossTurnToken() {

        for (int i = 0; i < enemyCharacterList.size(); i++) {
            enemyCharacterList.get(i).setUnselected();
            playerCharacterList.get(i).setUnselected();

            if (!getTurnToken())
                playerCharacterList.get(0).setSelected();
            else
                enemyCharacterList.get(0).setSelected();
        }
        turnToken = !turnToken;
        System.out.println("END OF TURN");
        System.out.println("======================================");
    }

    public Entity getSelectedSource(){
        for(int i=0; i<playerCharacterList.size();i++)
            if(playerCharacterList.get(i).getIsSelected() == 1)
                return playerCharacterList.get(i);

        for(int i=0; i<enemyCharacterList.size();i++)
            if(enemyCharacterList.get(i).getIsSelected() == 1)
                return enemyCharacterList.get(i);

        return null;
    }

    public Entity getSelectedTarget(){
        for(int i=0; i<playerCharacterList.size();i++)
            if(playerCharacterList.get(i).getIsSelected() == 3)
                return playerCharacterList.get(i);

        for(int i=0; i<enemyCharacterList.size();i++)
            if(enemyCharacterList.get(i).getIsSelected() == 1) {
                return enemyCharacterList.get(i);
            }

        return null;
    }
}
