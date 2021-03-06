package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.Gdx;
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
    private Texture isDeadTexture = new Texture("corpse.png");


    private static int HEALTHPOOL = 120;
    private static int MANA_POOL = 60;
    private static int ATTACK_DAMAGE = 15;
    private static int DODGE_CHANCE = 15;
    private static int MAGIC_POWER = 20;


    private final static int WIDTH = Gdx.graphics.getWidth()/10;
    private final static int HEIGHT = Gdx.graphics.getHeight()/4;

    private final static int STARTING_X = GameplayScreen.slot6;
    private final static int STARTING_Y = (int)(Gdx.graphics.getHeight()/2.4);

    private final static int SECOND_SKILL_MANA_COST = 15;
    private final static int FOURTH_SKILL_MANA_COST = 10;


    public Vampire(GameplayScreen gpScreen) {
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

    @Override
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
    public void useSecondSkill(Entity target) {  //Life drain
        int skillDamage = 2 * this.getMagicPower();

        this.useMana(getSecondSkillManaCost());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + "'s current health is: " + this.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " drains " + target.getClassName(target.getClass()) + " for " + skillDamage);
        target.receiveDamage(skillDamage);
        this.getHealed((int) Math.ceil(0.3 * skillDamage));
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + "'s current health is: " + this.getCurrentHealth());

    }

    @Override
    public void useThirdSkill(Entity target) {
    }

    public void useThirdSkill() {  //Regeneration
        System.out.println(this.getClassName(this.getClass()) + "'s current health is: " + this.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " regenerates himself");
        this.getHealed(this.getHealthPool());
        System.out.println(this.getClassName(this.getClass()) + "'s current health is: " + this.getCurrentHealth());
        this.useMana(this.getCurrentMana());
    }


    @Override
    public void useFourthSkill(Entity target) {  //Vicious Strike
        this.useMana(getFourthSkillManaCost());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " uses Vicious Strike on: " + target.getClassName(target.getClass()) + " for " + (this.getAttackDamage() + this.getMagicPower()));
        target.receiveDamage(this.getAttackDamage() + this.getMagicPower());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useFifthSkill(Entity target) {
    }


}

