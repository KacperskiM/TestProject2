package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

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
    public abstract int getAttackDamage(int attackDamage);

    protected abstract void setDodgeChance(int dodgeChance);
    public abstract int getDodgeChance();

}