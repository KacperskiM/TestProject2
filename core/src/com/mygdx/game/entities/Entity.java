package com.mygdx.game.entities;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.screens.GameplayScreen;
import com.mygdx.game.ui.healthbar.HealthBar;

/**
 * Created by Ja on 2017-08-23.
 */

public abstract class Entity extends Image {

    protected GameplayScreen gpScreen;

    private int healthPool;
    private int currentHealth;

    private int manaPool;
    private int currentMana;

    private int attackDamage;
    private int dodgeChance;
    private int magicPower;

    private Boolean divineShield;

    private int SecondSkillManaCost;
    private int ThirdSkillManaCost;
    private int FourthSkillManaCost;
    private int FifthSkillManaCost;

    private int bleeding;
    private int poisoned;

    private Boolean isDead;

    protected HealthBar healthBar;

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

    public void setGpScreen(GameplayScreen gpScreen) {
        this.gpScreen = gpScreen;
    }

    public int getIsSelected() {
        return isSelected;
    }

    protected void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
        this.currentHealth = healthPool;
    }

    public int getHealthPool() {
        return this.healthPool;
    }


    public int getCurrentHealth() {
        return this.currentHealth;
    }


    protected void setManaPool(int manaPool) {
        this.manaPool = manaPool;
        this.currentMana = manaPool;

    }


    public void useMana(int manaCost) {
        this.currentMana -= manaCost;
    }


    public void receiveDamage(int damageTaken) {
        if (this.getDivineShield() != true) {
            this.currentHealth -= damageTaken;
            this.healthBar.setValue(this.currentHealth);
            this.isAlive();
        } else {
            System.out.println("Target consumed divine shield!");
            this.setDivineShield(false);
        }
    }

    private void isAlive() {
        if (this.getCurrentHealth() <= 0)
            this.die();
    }

    protected abstract void die();

    public void getHealed(int healthRestored) {
        this.currentHealth += healthRestored;
        this.healthBar.setValue(this.currentHealth);
        if (currentHealth > healthPool)
            currentHealth = healthPool;

    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    protected void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public int getDodgeChance() {
        return this.dodgeChance;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }


    public int getBleeding() {
        return bleeding;
    }

    public void setBleeding() {
        this.bleeding += 2;
    }

    public int getPoisoned() {
        return poisoned;
    }

    public void setPoisoned() {
        this.poisoned += 2;
    }

    public void dealBleedingDamage() {
        this.bleeding -= 1;
        this.receiveDamage(10);
    }

    public void dealPoisonDamage() {
        this.poisoned -= 1;
        this.receiveDamage(10);
    }

    public void curePoisonAndBleed() {
        this.poisoned = 0;
        this.bleeding = 0;
    }

    public Boolean getDivineShield() {
        return divineShield;
    }

    public void setDivineShield(Boolean divineShield) {
        this.divineShield = divineShield;
    }


    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public abstract void useFirstSkill(Entity target);

    public abstract void useSecondSkill(Entity target);

    public abstract void useThirdSkill(Entity target);

    public abstract void useFourthSkill(Entity target);

    public abstract void useFifthSkill(Entity target);

    public void useSixthSkill() {

        //Todo: pass turn effect

        gpScreen.tossTurnToken();
    }

    // returns the class (without the package if any)
    public String getClassName(Class c) {
        String FQClassName = c.getName();
        int firstChar;
        firstChar = FQClassName.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            FQClassName = FQClassName.substring(firstChar);
        }
        return FQClassName;
    }

    public void move(int location_X) {
        Action a = Actions.moveTo(location_X, 300, 0.75f);
        this.addAction(a);
        Action b = Actions.moveTo(location_X+0.5f*(this.getWidth()-healthBar.getWidth()), 300 + this.getHeight() + 20,0.75f);
        this.healthBar.addAction(b);
    }

    public void moveEnemy(int location_X) {

        Action a = Actions.moveTo(location_X, 300, 0.75f);
        DelayAction delayAction = new DelayAction();
        delayAction.setDuration(0.75f);
        SequenceAction sequenceAction = new SequenceAction(delayAction, a);
        this.addAction(sequenceAction);

        Action b = Actions.moveTo(location_X+0.5f*(this.getWidth()-healthBar.getWidth()), 300 + this.getHeight() + 20,0.75f);
        sequenceAction = new SequenceAction(delayAction,b);
        this.healthBar.addAction(sequenceAction);
    }


    public int getSecondSkillManaCost() {
        return SecondSkillManaCost;
    }

    public void setSecondSkillManaCost(int secondSkillManaCost) {
        SecondSkillManaCost = secondSkillManaCost;
    }

    public int getThirdSkillManaCost() {
        return ThirdSkillManaCost;
    }

    public void setThirdSkillManaCost(int thirdSkillManaCost) {
        ThirdSkillManaCost = thirdSkillManaCost;
    }

    public int getFourthSkillManaCost() {
        return FourthSkillManaCost;
    }

    public void setFourthSkillManaCost(int fourthSkillManaCost) {
        FourthSkillManaCost = fourthSkillManaCost;
    }

    public int getFifthSkillManaCost() {
        return FifthSkillManaCost;
    }

    public void setFifthSkillManaCost(int fifthSkillManaCost) {
        FifthSkillManaCost = fifthSkillManaCost;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }

}