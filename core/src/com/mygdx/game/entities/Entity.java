package com.mygdx.game.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Ja on 2017-08-23.
 */

public abstract class Entity extends Image {

    protected int healthPool;
    protected int currentHealth;

    protected int manaPool;
    protected int currentMana;

    protected int attackDamage;
    protected int dodgeChance;

    /*
    0 - not selected
    1 - selected (green or red)
    2 - selected to buff (blue)
    3 - selected and selected to buff (green and blue)
    */
    protected int isSelected;

    public abstract void setSelected();
    public abstract void setUnselected();

    public abstract void setToBuff();
    public abstract void setToBuffSelected();


    public  int getIsSelected(){
        return isSelected;
    }

    protected void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }

    public int getHealthPool() {
        return this.healthPool;
    }


    public int getCurrentHealth(){
        return this.currentHealth;
    }


    protected void setManaPool(int manaPool) {
        this.manaPool = manaPool;

    }

    public int getManaPool() {
        return this.manaPool;
    }

    public void useMana(int manaCost){
        this.currentMana -= manaCost;
    }


    public void receiveDamage(int damageTaken) {
        this.currentHealth -= damageTaken;
        // isAlive();
    }

    public void getHealed(int healthRestored) {
        this.currentHealth += healthRestored;
        if (currentHealth > healthPool)
            currentHealth = healthPool;

    }

    protected void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    protected void setDodgeChance(int dodgeChance) {
        this.dodgeChance=dodgeChance;
    }

    public int getDodgeChance() {
        return this.dodgeChance;
    }

    public abstract void useFirstSkill(Entity target);
}