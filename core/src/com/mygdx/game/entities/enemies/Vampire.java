package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.screens.GameplayScreen;


/**
 * Created by Ja on 2017-05-20.
 */

public class Vampire extends Entity {

    private Texture unselectedTexture = new Texture("vampire.png");
    private Texture selectedTexture = new Texture("vampire_selected.png");

    private static int HEALTHPOOL = 120;
    private static int MANA_POOL = 60;
    private static int ATTACK_DAMAGE = 15;
    private static int DODGE_CHANCE = 15;
    private static int MAGIC_POWER = 20;


    private final static int WIDTH = 122;
    private final static int HEIGHT = 180;

    private final static int STARTING_X=850;
    private final static  int STARTING_Y=300;

    public Vampire(GameplayScreen gpScreen) {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);
        this.setPosition(STARTING_X,STARTING_Y);

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
    public void useSecondSkill(Entity target) {

    }

    @Override
    public void useThirdSkill(Entity target) {

    }

    @Override
    public void useFourthSkill(Entity target) {

    }

    @Override
    public void useFifthSkill(Entity target) {

    }


}

