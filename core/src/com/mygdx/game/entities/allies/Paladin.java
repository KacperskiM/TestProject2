package com.mygdx.game.entities.allies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.screens.GameplayScreen;

import java.util.Collections;

/**
 * Created by Ja on 2017-05-20.
 */

public class Paladin extends Entity {

    private Texture unselectedTexture = new Texture("paladin.png");
    private Texture selectedTexture = new Texture("paladin_selected.png");
    private Texture toBuffTexture = new Texture("paladin_toBuff.png");
    private Texture toBuffSelectedTexture = new Texture("paladin_toBuff_selected.png");

    private static int HEALTHPOOL = 150;
    private static int MANA_POOL = 80;
    private static int ATTACK_DAMAGE = 20;
    private static int DODGE_CHANCE = 5;
    private static int MAGIC_POWER = 20;

    private final static int WIDTH = 92;
    private final static int HEIGHT = 150;

    private final static int STARTING_X = 300;
    private final static int STARTING_Y = 300;

    public Paladin(GameplayScreen gpScreen) {
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

    //Todo: skill's null pointer

    @Override
    public void useFirstSkill(Entity target) {  //Auto attack
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " auto attacks " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(this.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useSecondSkill(Entity target) {  //Divine shield

        System.out.println(this.getClassName(this.getClass()) + " uses divine shield on " + target.getClassName(target.getClass()));
        target.setDivineShield(true);
        //TODO: DIVINE SHIELD CHECK
    }

    @Override
    public void useThirdSkill(Entity target) {  //Pull

        System.out.println(this.getClassName(this.getClass()) + " pulls " + target.getClassName(target.getClass()));
        for(int i =0;i<gpScreen.getEnemyCharacterList().size();i++) {
            if(target==gpScreen.getEnemyCharacterList().get(i))
            Collections.swap(gpScreen.getEnemyCharacterList(), i, 1);
        }
    }

    @Override
    public void useFourthSkill(Entity target) { //Enlightenment
        System.out.println(target.getClassName(target.getClass()) + "'s current attack damage is: " + target.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current magic power is: " + target.getMagicPower());
        System.out.println(this.getClassName(this.getClass()) + " enlightenments " + target.getClassName(target.getClass()));
        target.setMagicPower((int)1.5*target.getMagicPower());
        target.setAttackDamage((int)1.5*target.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current attack damage is: " + target.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current magic power is: " + target.getMagicPower());
    }

    @Override
    public void useFifthSkill(Entity target) {  //Smite
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " smites " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(2 *this.getMagicPower());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

}

