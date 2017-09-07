package com.mygdx.game.entities;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.screens.GameplayScreen;

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

    private Boolean bleeding;
    private Boolean poisoned;

    private Boolean divineShield;

    private int SecondSkillManaCost;
    private int ThirdSkillManaCost;
    private int FourthSkillManaCost;
    private int FifthSkillManaCost;


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
        this.currentHealth=healthPool;
    }

    protected int getHealthPool() {
        return this.healthPool;
    }


    public int getCurrentHealth(){
        return this.currentHealth;
    }


    protected void setManaPool(int manaPool) {
        this.manaPool = manaPool;
        this.currentMana=manaPool;

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

    public void setAttackDamage(int attackDamage) {
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

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }


    public Boolean getBleeding() {
        return bleeding;
    }

    public void setBleeding(Boolean bleeding) {
        this.bleeding = bleeding;
    }

    public Boolean getPoisoned() {
        return poisoned;
    }

    public void setPoisoned(Boolean poisoned) {
        this.poisoned = poisoned;
    }

    public void dealBleedingDamage(){
        this.receiveDamage(10);
    }

    public void dealPoisonDamage(){
        this.receiveDamage(10);
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
    public void useSixthSkill(){

        //Todo: pass turn effect

        gpScreen.tossTurnToken();
    }

    // returns the class (without the package if any)
    public String getClassName(Class c) {
        String FQClassName = c.getName();
        int firstChar;
        firstChar = FQClassName.lastIndexOf ('.') + 1;
        if ( firstChar > 0 ) {
            FQClassName = FQClassName.substring ( firstChar );
        }
        return FQClassName;
    }

    public void move(int location_X) {
        Action a = Actions.moveTo(location_X, 300, 0.75f);
        this.addAction(a);
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
}