package com.mygdx.game.entities.allies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.screens.GameplayScreen;
import com.mygdx.game.ui.statusBars.HealthBar;

import java.util.Random;

/**
 * Created by Ja on 2017-05-20.
 */

public class Ranger extends Entity {

    private Texture unselectedTexture = new Texture("ranger.png");
    private Texture selectedTexture = new Texture("ranger_selected.png");
    private Texture toBuffTexture = new Texture("ranger_toBuff.png");
    private Texture toBuffSelectedTexture = new Texture("ranger_toBuff_selected.png");
    private Texture isDeadTexture = new Texture("corpse.png");


    private static int HEALTHPOOL = 85;
    private static int MANA_POOL = 70;
    private static int ATTACK_DAMAGE = 30;
    private static int DODGE_CHANCE = 30;
    private static int MAGIC_POWER = 0;

    private final static int WIDTH = 122;
    private final static int HEIGHT = 180;

    private final static int STARTING_X = 200;
    private final static int STARTING_Y = 300;

    private final static int SECOND_SKILL_MANA_COST = 20;
    private final static int THIRD_SKILL_MANA_COST = 20;
    private final static int FOURTH_SKILL_MANA_COST = 10;
    private final static int FIFTH_SKILL_MANA_COST = 30;



    public Ranger(GameplayScreen gpScreen) {
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

        this.setSecondSkillManaCost(SECOND_SKILL_MANA_COST);    //Multishot
        this.setThirdSkillManaCost(THIRD_SKILL_MANA_COST);      //Headshot
        this.setFourthSkillManaCost(FOURTH_SKILL_MANA_COST);    //Cripple (enemy may pass his turn)
        this.setFifthSkillManaCost(FIFTH_SKILL_MANA_COST);      //Swiftness

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
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffTexture)));
        this.isSelected = 2;
    }

    public void setToBuffSelected() {
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffSelectedTexture)));
        this.isSelected = 3;
    }

    @Override
    protected void die() {
        gpScreen.getPlayerCharacterList().remove(gpScreen.getPlayerCharacterList().indexOf(this));
        this.setDrawable(new SpriteDrawable(new Sprite(isDeadTexture)));
        this.move(GameplayScreen.getAllyPositionArray()[gpScreen.getPlayerCharacterList().size()]);
        this.setDead(true);
    }


    @Override
    public void useFirstSkill(Entity target) {  //Auto attack
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " auto attacks " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(this.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useSecondSkill(Entity target) {}


    public void useSecondSkill() {  //Multishot
        int skillDamage = this.getAttackDamage();

        this.useMana(getSecondSkillManaCost());
        for (int i = 0; i < gpScreen.getEnemyCharacterList().size(); i++) {
            System.out.println(gpScreen.getEnemyCharacterList().get(i).getClassName(gpScreen.getEnemyCharacterList().get(i).getClass()) + "'s current health is: " + gpScreen.getEnemyCharacterList().get(i).getCurrentHealth());
            System.out.println(this.getClassName(this.getClass()) + " multishots " + gpScreen.getEnemyCharacterList().get(i).getClassName(gpScreen.getEnemyCharacterList().get(i).getClass()) + " for " + skillDamage);
            gpScreen.getEnemyCharacterList().get(i).receiveDamage(skillDamage);
            System.out.println(gpScreen.getEnemyCharacterList().get(i).getClassName(gpScreen.getEnemyCharacterList().get(i).getClass()) + "'s current health is: " + gpScreen.getEnemyCharacterList().get(i).getCurrentHealth());
        }
    }

    @Override
    public void useThirdSkill(Entity target) {  //Headshot
        int skillDamage = 2 * this.getAttackDamage();

        this.useMana(getThirdSkillManaCost());
        Random rand = new Random();
        int i = rand.nextInt(99) + 1;
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " uses headshot on " + target.getClassName(target.getClass()));
        if (i >= 50 && i <= 90) {
            System.out.println(this.getClassName(this.getClass()) + " headshots " + target.getClassName(target.getClass()) + " for " + skillDamage);
            target.receiveDamage(skillDamage);
            System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        } else if (i > 90) {
            System.out.println(this.getClassName(this.getClass()) + " headshots and lethals " + target.getClassName(target.getClass()));
            target.receiveDamage(target.getCurrentHealth());
        } else {
            System.out.println(this.getClassName(this.getClass()) + " misses " + target.getClassName(target.getClass()));
        }
    }

    @Override
    public void useFourthSkill(Entity target) {  //Cripple
        int skillDamage = this.getAttackDamage();

        Random rand = new Random();
        int i = rand.nextInt(99) + 1;

        this.useMana(getFourthSkillManaCost());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " uses cripple on " + target.getClassName(target.getClass()) +" for " + skillDamage);
        target.receiveDamage(skillDamage);
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        if (i > 50) {
            System.out.println(target.getClassName(target.getClass()) + " is moved to the end of queue");
            gpScreen.getEnemyCharacterList().remove(target);
            gpScreen.getEnemyCharacterList().add(target);
            for (int j = 0; j < gpScreen.getEnemyCharacterList().size(); j++)
                gpScreen.getEnemyCharacterList().get(j).move(GameplayScreen.getEnemyPositionArray()[j]);
        }
    }

    @Override
    public void useFifthSkill(Entity target) {}


    public void useFifthSkill() {  //Swiftness

        this.useMana(getFifthSkillManaCost());
        System.out.println(this.getClassName(this.getClass()) + "'s dodge chance is: " + this.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " uses swiftness on himself.");
        this.setDodgeChance(75);
        System.out.println(this.getClassName(this.getClass()) + "'s dodge chance is: " + this.getCurrentHealth());
    }

}


