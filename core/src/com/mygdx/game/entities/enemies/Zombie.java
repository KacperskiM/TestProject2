package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.screens.GameplayScreen;
import com.mygdx.game.ui.statusBars.HealthBar;
import com.mygdx.game.ui.statusBars.ManaBar;

import java.util.Random;

/**
 * Created by Ja on 2017-05-20.
 */

public class Zombie extends Entity {

    private Texture unselectedTexture = new Texture("zombie.png");
    private Texture selectedTexture = new Texture("zombie_selected.png");
    private Texture isDeadTexture = new Texture("corpse.png");


    private static int HEALTHPOOL = 100;
    private static int MANA_POOL = 50;
    private static int ATTACK_DAMAGE = 20;
    private static int DODGE_CHANCE = 5;
    private static int MAGIC_POWER = 0;

    private final static int WIDTH = Gdx.graphics.getWidth()/10;
    private final static int HEIGHT = Gdx.graphics.getHeight()/4;

    private final static int STARTING_X = GameplayScreen.slot5;
    private final static int STARTING_Y = (int)(Gdx.graphics.getHeight()/2.4);

    private final static int SECOND_SKILL_MANA_COST = 10;
    private final static int THIRD_SKILL_MANA_COST = 30;
    private final static int FOURTH_SKILL_MANA_COST = 25;


    public Zombie(GameplayScreen gpScreen, Boolean resurrected) {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        this.gpScreen = gpScreen;

        this.setManaPool(MANA_POOL);
        this.setHealthPool(HEALTHPOOL);

        if (resurrected) {
            this.setCurrentHealth((int) Math.ceil(0.5 * HEALTHPOOL));
            this.setCurrentMana((int) Math.ceil(0.5 * MANA_POOL));
            this.setPosition(GameplayScreen.getEnemyPositionArray()[2], STARTING_Y);
        } else {

            this.setPosition(STARTING_X, STARTING_Y);
        }

        this.setAttackDamage(ATTACK_DAMAGE);
        this.setMagicPower(MAGIC_POWER);
        this.setSecondSkillManaCost(SECOND_SKILL_MANA_COST);
        this.setThirdSkillManaCost(THIRD_SKILL_MANA_COST);
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

    public void createHealthBar2() {
        System.out.println(this.getCurrentHealth() + "   " + this.getHealthPool());
        this.healthBar = new HealthBar(this.getHealthPool(), 0.5f * this.getHealthPool());
        healthBar.setPosition(this.getX() + 0.5f * (this.getWidth() - healthBar.getWidth()), this.getY() + this.getHeight() + 20);
        getStage().addActor(healthBar);
    }

    public void createManaBar2() {
        this.manaBar = new ManaBar(this.getManaPool(), 0.5f * this.getManaPool());
        manaBar.setPosition(this.getX() + 0.5f * (this.getWidth() - manaBar.getWidth()), this.getY() + this.getHeight() + 10);
        getStage().addActor(manaBar);
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
    public void useSecondSkill(Entity target) {  //Toxic Chop
        int skillDamage = this.getAttackDamage();

        Random rand = new Random();
        int i = rand.nextInt(99) + 1;

        this.useMana(getSecondSkillManaCost());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " uses Toxic Chop on: " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(skillDamage);
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());

        if (i >= 20) {
            target.setPoisoned();
            System.out.println(target.getClassName(target.getClass()) + " is being poisoned!");
        }
    }

    public void useThirdSkill() {   //Resurrect Zombie
        this.useMana(getThirdSkillManaCost());
        gpScreen.getDeadEnemiesList().get(gpScreen.getDeadEnemiesList().size() - 1).getHealthBar().remove();
        gpScreen.getDeadEnemiesList().get(gpScreen.getDeadEnemiesList().size() - 1).getManaBar().remove();
        gpScreen.getDeadEnemiesList().get(gpScreen.getDeadEnemiesList().size() - 1).remove();


        gpScreen.initResurrectedZombie();
        System.out.println(this.getClassName(this.getClass()) + " resurrects a zombie from corpse");
    }

    @Override
    public void useThirdSkill(Entity target) {
    }

    @Override
    public void useFourthSkill(Entity target) {  //Explode
        int skillDamage = 2 * this.getAttackDamage();

        this.useMana(getFourthSkillManaCost());
        for (int i = 0; i < gpScreen.getPlayerCharacterList().size(); i++) {
            System.out.println(gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + "'s current health is: " + gpScreen.getPlayerCharacterList().get(i).getCurrentHealth());
            System.out.println(this.getClassName(this.getClass()) + " explodes " + gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + " for " + skillDamage);
            gpScreen.getPlayerCharacterList().get(i).receiveDamage(skillDamage);
            System.out.println(gpScreen.getPlayerCharacterList().get(i).getClassName(gpScreen.getPlayerCharacterList().get(i).getClass()) + "'s current health is: " + gpScreen.getPlayerCharacterList().get(i).getCurrentHealth());
        }
        this.receiveDamage(this.getCurrentHealth());

    }

    @Override
    public void useFifthSkill(Entity target) {
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }

}

