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

    protected abstract void setHealthPool(int healthPool);
    public abstract int getHealthPool();
    public abstract int getCurrentHealth();

    protected abstract void setManaPool(int manaPool);
    public abstract int getManaPool();
    public abstract void useMana(int manaCost);

    public abstract void receiveDamage(int damageTaken);
    public abstract void getHealed(int healthRestored);

    protected abstract void setAttackDamage(int attackDamage);
    public abstract int getAttackDamage();

    protected abstract void setDodgeChance(int dodgeChance);
    public abstract int getDodgeChance();

    public  int getIsSelected(){
        return isSelected;
    }

    public abstract void useFirstSkill(Entity target);

}