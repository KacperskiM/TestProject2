package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.screens.GameplayScreen;
import com.mygdx.game.ui.statusBars.BleedIcon;
import com.mygdx.game.ui.statusBars.HealthBar;
import com.mygdx.game.ui.statusBars.ManaBar;
import com.mygdx.game.ui.statusBars.PoisonIcon;

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
    protected ManaBar manaBar;

    protected BleedIcon bleedIcon1, bleedIcon2;
    protected PoisonIcon poisonIcon1, poisonIcon2;

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

    protected int getManaPool() {
        return manaPool;
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
        this.manaBar.setValue(this.getCurrentMana());
    }


    public void receiveDamage(int damageTaken) {
        if (!this.getDivineShield()) {
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
        if (currentHealth > healthPool)
            currentHealth = healthPool;
        this.getHealthBar().setValue(currentHealth);
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
        this.bleeding = 2;
        this.bleedIcon1.setVisible(true);
        this.bleedIcon2.setVisible(true);
    }

    public int getPoisoned() {
        return poisoned;
    }

    public void setPoisoned() {
        this.poisoned = 2;
        this.poisonIcon1.setVisible(true);
        this.poisonIcon2.setVisible(true);
    }

    public void dealBleedingDamage() {
        this.bleeding -= 1;
        this.receiveDamage(10);
        if (this.bleeding == 1)
            this.bleedIcon2.setVisible(false);
        if (this.bleeding == 0)
            this.bleedIcon1.setVisible(false);
    }

    public void dealPoisonDamage() {
        this.poisoned -= 1;
        this.receiveDamage(10);
        if (this.poisoned == 1)
            this.poisonIcon2.setVisible(false);
        if (this.poisoned == 0)
            this.poisonIcon1.setVisible(false);
    }

    public void curePoisonAndBleed() {
        this.poisoned = 0;
        this.poisonIcon1.setVisible(false);
        this.poisonIcon2.setVisible(false);

        this.bleeding = 0;
        this.bleedIcon1.setVisible(false);
        this.bleedIcon2.setVisible(false);
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

        a = Actions.moveTo(location_X + 0.5f * (this.getWidth() - healthBar.getWidth()), (int)(Gdx.graphics.getHeight()/2.4) + this.getHeight() + 20, 0.75f);
        this.healthBar.addAction(a);

        a = Actions.moveTo(location_X + 0.5f * (this.getWidth() - manaBar.getWidth()), (int)(Gdx.graphics.getHeight()/2.4) + this.getHeight() + 10, 0.75f);
        this.manaBar.addAction(a);

        if (poisonIcon1 != null) {
            a = Actions.moveTo(location_X + 0.5f * (this.getWidth() - healthBar.getWidth()) + this.healthBar.getWidth() +1, (int) (this.healthBar.getY()), 0.75f);
            this.poisonIcon1.addAction(a);
        }
        if (poisonIcon2 != null) {
            a = Actions.moveTo(location_X + 0.5f * (this.getWidth() - healthBar.getWidth()) + this.healthBar.getWidth() + this.poisonIcon1.getWidth()+ 2, (int) (this.healthBar.getY()), 0.75f);
            this.poisonIcon2.addAction(a);
        }
        if(this.bleedIcon1!=null){
            a = Actions.moveTo(location_X + 0.5f * (this.getWidth() - manaBar.getWidth()) + this.manaBar.getWidth() +1, (int) (this.manaBar.getY()), 0.75f);
            this.bleedIcon1.addAction(a);
        }
        if(this.bleedIcon2!=null){
            a = Actions.moveTo(location_X + 0.5f * (this.getWidth() - manaBar.getWidth()) + this.manaBar.getWidth() + this.bleedIcon1.getWidth() +2, (int) (this.manaBar.getY()), 0.75f);
            this.bleedIcon2.addAction(a);
        }
    }

    public void moveEnemy(int location_X) {

        Action a = Actions.moveTo(location_X, (int)(Gdx.graphics.getHeight()/2.4), 0.75f);
        DelayAction delayAction = new DelayAction();
        delayAction.setDuration(2f);
        SequenceAction sequenceAction = new SequenceAction(delayAction, a);
        this.addAction(sequenceAction);

        Action b = Actions.moveTo(location_X + 0.5f * (this.getWidth() - healthBar.getWidth()), (int)(Gdx.graphics.getHeight()/2.4) + this.getHeight() + 20, 0.75f);
        sequenceAction = new SequenceAction(delayAction, b);
        this.healthBar.addAction(sequenceAction);

        Action c = Actions.moveTo(location_X + 0.5f * (this.getWidth() - manaBar.getWidth()), (int)(Gdx.graphics.getHeight()/2.4) + this.getHeight() + 10, 0.75f);
        sequenceAction = new SequenceAction(delayAction, c);
        this.manaBar.addAction(sequenceAction);
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

    public void createHealthBar() {
        this.healthBar = new HealthBar(this.getHealthPool());
        healthBar.setPosition(this.getX() + 0.5f * (this.getWidth() - healthBar.getWidth()), this.getY() + this.getHeight() + 20);
        getStage().addActor(healthBar);
    }


    public void createManaBar() {
        this.manaBar = new ManaBar(this.getManaPool());
        manaBar.setPosition(this.getX() + 0.5f * (this.getWidth() - manaBar.getWidth()), this.getY() + this.getHeight() + 10);
        getStage().addActor(manaBar);
    }

    public void createStatusIcons() {
        this.poisonIcon1 = new PoisonIcon((int) (this.healthBar.getX() + this.healthBar.getWidth() + 2), (int) (this.healthBar.getY()));
        this.poisonIcon2 = new PoisonIcon((int) (this.poisonIcon1.getX() + this.poisonIcon1.getWidth() + 1), (int) (this.poisonIcon1.getY()));

        this.bleedIcon1 = new BleedIcon((int) (this.manaBar.getX() + this.manaBar.getWidth() + 2), (int) (this.manaBar.getY()));
        this.bleedIcon2 = new BleedIcon((int) (this.bleedIcon1.getX() + this.bleedIcon1.getWidth() + 1), (int) (this.bleedIcon1.getY()));
        poisonIcon1.setVisible(false);
        poisonIcon2.setVisible(false);
        bleedIcon1.setVisible(false);
        bleedIcon2.setVisible(false);
        getStage().addActor(poisonIcon1);
        getStage().addActor(poisonIcon2);
        getStage().addActor(bleedIcon1);
        getStage().addActor(bleedIcon2);
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }


    public HealthBar getHealthBar() {
        return healthBar;
    }

    public ManaBar getManaBar() {
        return manaBar;
    }
}