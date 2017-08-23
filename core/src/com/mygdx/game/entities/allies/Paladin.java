package com.mygdx.game.entities.allies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;

/**
 * Created by Ja on 2017-05-20.
 */

public class Paladin extends Entity {

    private Texture unselectedTexture = new Texture("paladin.png");
    private Texture selectedTexture = new Texture("paladin_selected.png");
    private Texture toBuffTexture = new Texture("paladin_toBuff.png");
    private Texture toBuffSelectedTexture = new Texture("paladin_toBuff_selected.png");


    private final static int WIDTH = 92;
    private final static int HEIGHT = 150;

    private final static int STARTING_X = 300;
    private final static int STARTING_Y = 300;

    public Paladin() {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);


    }


    public void setSelected() {
        this.setDrawable(new SpriteDrawable(new Sprite(selectedTexture)));
    }

    public void setUnselected() {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
    }

    public void setToBuff() {
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffTexture)));
    }

    public void setToBuffSelected() {
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffSelectedTexture)));
    }


    @Override
    protected void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }

    @Override
    public int getHealthPool() {
        return this.healthPool;
    }

    @Override
    public int getCurrentHealth(){
        return this.currentHealth;
    }


    @Override
    protected void setManaPool(int manaPool) {
        this.manaPool = manaPool;

    }

    @Override
    public int getManaPool() {
        return this.manaPool;
    }

    @Override
    public void useMana(int manaCost){
        this.currentMana -= manaCost;
    }


    @Override
    public void receiveDamage(int damageTaken) {
        this.currentHealth -= damageTaken;
        // isAlive();
    }

    @Override
    public void getHealed(int healthRestored) {
        this.currentHealth += healthRestored;
        if (currentHealth > healthPool)
            currentHealth = healthPool;

    }

    @Override
    protected void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    @Override
    public int getAttackDamage(int attackDamage) {
        return this.attackDamage;
    }

    @Override
    protected void setDodgeChance(int dodgeChance) {
        this.dodgeChance=dodgeChance;
    }

    @Override
    public int getDodgeChance() {
        return this.dodgeChance;
    }


}
