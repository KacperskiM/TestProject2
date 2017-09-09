package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.screens.GameplayScreen;

import java.util.Random;

/**
 * Created by Ja on 2017-05-20.
 */

public class Zombie extends Entity {

    Texture unselectedTexture = new Texture("zombie.png");
    Texture selectedTexture = new Texture("zombie_selected.png");

    private static int HEALTHPOOL = 100;
    private static int MANA_POOL = 50;
    private static int ATTACK_DAMAGE = 25;
    private static int DODGE_CHANCE = 5;
    private static int MAGIC_POWER = 0;


    private final static int WIDTH = 122;
    private final static int HEIGHT = 180;

    private final static int STARTING_X = 750;
    private final static int STARTING_Y = 300;

    private final static int SECOND_SKILL_MANA_COST = 10;
    private final static int THIRD_SKILL_MANA_COST = 20;
    private final static int FOURTH_SKILL_MANA_COST = 25;

    private Boolean resurrectBuff;

    public Zombie(GameplayScreen gpScreen) {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);

        this.gpScreen = gpScreen;

        this.setHealthPool(HEALTHPOOL);
        this.setManaPool(MANA_POOL);
        this.setAttackDamage(ATTACK_DAMAGE);
        this.setDodgeChance(DODGE_CHANCE);
        this.setMagicPower(MAGIC_POWER);
        this.setSecondSkillManaCost(SECOND_SKILL_MANA_COST);
        this.setThirdSkillManaCost(THIRD_SKILL_MANA_COST);
        this.setFourthSkillManaCost(FOURTH_SKILL_MANA_COST);
    }

    public void setSelected() {
        this.setDrawable(new SpriteDrawable(new Sprite(selectedTexture)));
        this.isSelected = 1;
    }

    public void setUnselected() {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.isSelected = 0;
    }

    public Boolean getResurrectBuff() {
        return resurrectBuff;
    }

    public void setResurrectBuff(Boolean resurrectBuff) {
        this.resurrectBuff = resurrectBuff;
    }

    @Override
    public void setToBuff() {

    }

    @Override
    public void setToBuffSelected() {

    }

    @Override
    public void useFirstSkill(Entity target) {  //Auto attack
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " auto attacks " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(this.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useSecondSkill(Entity target) {  //Toxic Chop
        int skillDamage = this.getAttackDamage();

        Random rand = new Random();
        int i = rand.nextInt(99) + 1;

        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " uses Toxic Chop on: " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(skillDamage);
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());

        if ( i >= 20) {
            target.setPoisoned(true);
            System.out.println(target.getClassName(target.getClass()) + " is being poisoned!");
            //TODO: set poison time
        }
    }

    public void useThirdSkill() {   //Resurrect Zombie
        gpScreen.initZombie();
    }

    @Override
    public void useThirdSkill(Entity target) {}

    @Override
    public void useFourthSkill(Entity target) {  //Explode
        int skillDamage = 2*this.getAttackDamage();

        for (int i = 0; i < gpScreen.getPlayerCharacterList().size(); i++) {
            System.out.println(gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + "'s current health is: " + gpScreen.getPlayerCharacterList().get(i).getCurrentHealth());
            System.out.println(this.getClassName(this.getClass()) + "explodes " + gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + " for " + skillDamage);
            gpScreen.getPlayerCharacterList().get(i).receiveDamage(skillDamage);
            System.out.println(gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + "'s current health is: " + gpScreen.getPlayerCharacterList().get(i).getCurrentHealth());
        }
        this.receiveDamage(this.getCurrentHealth());

    }

    @Override
    public void useFifthSkill(Entity target) {
        return;
    }


}

