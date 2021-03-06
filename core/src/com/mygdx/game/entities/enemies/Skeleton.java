package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.screens.GameplayScreen;
import com.mygdx.game.ui.statusBars.HealthBar;

import java.util.Collections;
import java.util.Random;

/**
 * Created by Ja on 2017-05-20.
 */

public class Skeleton extends Entity {

    private Texture unselectedTexture = new Texture("skeleton.png");
    private Texture selectedTexture = new Texture("skeleton_selected.png");
    private Texture isDeadTexture = new Texture("corpse.png");


    private static int HEALTHPOOL = 100;
    private static int MANA_POOL = 44;
    private static int ATTACK_DAMAGE = 11;
    private static int DODGE_CHANCE = 25;
    private static int MAGIC_POWER = 0;


    private final static int WIDTH = Gdx.graphics.getWidth()/10;
    private final static int HEIGHT = Gdx.graphics.getHeight()/4;

    private final static int STARTING_X = GameplayScreen.slot4;
    private final static int STARTING_Y = (int)(Gdx.graphics.getHeight()/2.4);

    private final static int SECOND_SKILL_MANA_COST = 18;
    private final static int THIRD_SKILL_MANA_COST = 15;
    private final static int FOURTH_SKILL_MANA_COST = 5;

    public Skeleton(GameplayScreen gpScreen) {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);

        this.gpScreen = gpScreen;

        this.setHealthPool(HEALTHPOOL);
        this.setManaPool(MANA_POOL);
        this.setAttackDamage(ATTACK_DAMAGE);
        this.setMagicPower(MAGIC_POWER);
        this.setSecondSkillManaCost(SECOND_SKILL_MANA_COST);
        this.setThirdSkillManaCost(THIRD_SKILL_MANA_COST);
        this.setFourthSkillManaCost(FOURTH_SKILL_MANA_COST);

        this.setDead(false);
        this.setDivineShield(false);
    }

    public void setSelected() {
        this.setDrawable(new SpriteDrawable(new Sprite(selectedTexture)));
        this.isSelected = 1;
    }

    public void setUnselected() {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.isSelected = 0;
    }

    public void setToBuff() {
    }

    @Override
    public void setToBuffSelected() {

    }

    @Override
    protected void die() {
        gpScreen.getEnemyCharacterList().remove(gpScreen.getEnemyCharacterList().indexOf(this));
        this.setDrawable(new SpriteDrawable(new Sprite(isDeadTexture)));
        this.move(GameplayScreen.getEnemyPositionArray()[gpScreen.getEnemyCharacterList().size()]);
        this.setDead(true);
        gpScreen.getDeadEnemiesList().add(this);
    }

    @Override
    public void useFirstSkill(Entity target) {  //Auto attack
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " auto attacks " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(this.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useSecondSkill(Entity target) {  //Bleeding
        int skillDamage = this.getAttackDamage();

        Random rand = new Random();
        int i = rand.nextInt(99) + 1;

        this.useMana(getSecondSkillManaCost());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " stabs " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(skillDamage);
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());

        if ( i >= 50) {
            target.setBleeding();
            System.out.println(target.getClassName(target.getClass()) + " starts bleeding!");
        }
    }

    @Override
    public void useThirdSkill(Entity target) {  //Swing
        int skillDamage = this.getAttackDamage();

        this.useMana(getThirdSkillManaCost());
        for (int i = 0; i < gpScreen.getPlayerCharacterList().size(); i++) {
            System.out.println(gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + "'s current health is: " + gpScreen.getPlayerCharacterList().get(i).getCurrentHealth());
            System.out.println(this.getClassName(this.getClass()) + " swings " + gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + " for " + skillDamage);
            gpScreen.getPlayerCharacterList().get(i).receiveDamage(skillDamage);
            System.out.println(gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + "'s current health is: " + gpScreen.getPlayerCharacterList().get(i).getCurrentHealth());
        }
    }

    @Override
    public void useFourthSkill(Entity target) { //Fear

        this.useMana(getFourthSkillManaCost());
        System.out.println(this.getClassName(this.getClass()) + " fears " + target.getClassName(target.getClass()));
        for(int i =0;i<gpScreen.getPlayerCharacterList().size();i++) {
            if(target==gpScreen.getPlayerCharacterList().get(i))
                Collections.swap(gpScreen.getPlayerCharacterList(), i, 0);
        }

    }

    @Override
    public void useFifthSkill(Entity target) {
    }


}

