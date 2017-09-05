package com.mygdx.game.entities.allies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.enemies.Skeleton;
import com.mygdx.game.entities.enemies.Vampire;
import com.mygdx.game.entities.enemies.Zombie;
import com.mygdx.game.screens.GameplayScreen;


/**
 * Created by Ja on 2017-05-06.
 */

public class Cleric extends Entity {

    private Texture unselectedTexture = new Texture("cleric.png");
    private Texture selectedTexture = new Texture("cleric_selected.png");
    private Texture toBuffTexture = new Texture("cleric_toBuff.png");
    private Texture toBuffSelectedTexture = new Texture("cleric_toBuff_selected.png");

    private static int HEALTHPOOL = 70;
    private static int MANA_POOL = 150;
    private static int ATTACK_DAMAGE = 10;
    private static int DODGE_CHANCE = 10;
    private static int MAGIC_POWER = 30;

    private final static int WIDTH = 122;
    private final static int HEIGHT = 180;

    private final static int STARTING_X=100;
    private final static  int STARTING_Y=300;

    public Cleric(GameplayScreen gpScreen) {
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

    public void setToBuff() {
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffTexture)));
        this.isSelected = 2;
    }

    public void setToBuffSelected() {
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffSelectedTexture)));
        this.isSelected = 3;
    }

        //Todo cleric's null pointer

    @Override
    public void useFirstSkill(Entity target) {  //Auto attack
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " auto attacks " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(this.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useSecondSkill(Entity target) { //Heal
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " heals " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.getHealed(40);
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useThirdSkill(Entity target) {  //Cure bleeding/poison
        System.out.println(target.getClassName(target.getClass()) + "'s bleed status: " + target.getBleeding());
        System.out.println(target.getClassName(target.getClass()) + "'s poison status: " + target.getPoisoned());
        System.out.println(this.getClassName(this.getClass()) + " cures bleed & poison of " + target.getClassName(target.getClass()));
        target.setBleeding(false);
        target.setPoisoned(false);
        System.out.println(target.getClassName(target.getClass()) + "'s bleed status: " + target.getBleeding());
        System.out.println(target.getClassName(target.getClass()) + "'s poison status: " + target.getPoisoned());
    }

    @Override
    public void useFourthSkill(Entity target) { // Banish Undead
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " uses Banish Undead on " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        if(target instanceof Skeleton  || target instanceof Zombie)
            target.receiveDamage((int)(1.5*this.getMagicPower()));
        else if(target instanceof Vampire)
            target.receiveDamage(this.getMagicPower());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useFifthSkill(Entity target) {  // Flash
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        if(target instanceof Vampire) {
            target.receiveDamage((int) (1.5 * this.getMagicPower()));
            System.out.println(this.getClassName(this.getClass()) + " uses Flash on " + target.getClassName(target.getClass()) + " for " + 1.5*this.getMagicPower());
        }
        else if(target instanceof Skeleton  || target instanceof Zombie) {
            target.receiveDamage(this.getMagicPower());
            System.out.println(this.getClassName(this.getClass()) + " uses Flash on " + target.getClassName(target.getClass()) + " for " + this.getMagicPower());

        }
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }


}
