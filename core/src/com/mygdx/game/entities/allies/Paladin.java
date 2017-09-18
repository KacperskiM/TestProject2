package com.mygdx.game.entities.allies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.screens.GameplayScreen;
import com.mygdx.game.ui.tooltips.PaladinTooltip;

/**
 * Created by Ja on 2017-05-20.
 */

public class Paladin extends Entity {

    private Texture unselectedTexture = new Texture("paladin.png");
    private Texture selectedTexture = new Texture("paladin_selected.png");
    private Texture toBuffTexture = new Texture("paladin_toBuff.png");
    private Texture toBuffSelectedTexture = new Texture("paladin_toBuff_selected.png");
    private Texture isDeadTexture = new Texture("corpse.png");


    private static int HEALTHPOOL = 150;
    private static int MANA_POOL = 80;
    private static int ATTACK_DAMAGE = 20;
    private static int MAGIC_POWER = 20;

    private final static int WIDTH = Gdx.graphics.getWidth()/10;
    private final static int HEIGHT = Gdx.graphics.getHeight()/4;

    private final static int STARTING_X = GameplayScreen.slot3;
    private final static int STARTING_Y = (int)(Gdx.graphics.getHeight()/2.4);

    private final static int SECOND_SKILL_MANA_COST = 25;
    private final static int THIRD_SKILL_MANA_COST = 15;
    private final static int FOURTH_SKILL_MANA_COST = 25;
    private final static int FIFTH_SKILL_MANA_COST = 15;

    private PaladinTooltip paladinTooltip;



    public Paladin(GameplayScreen gpScreen) {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);

        this.gpScreen = gpScreen;
        this.paladinTooltip = new PaladinTooltip(gpScreen);

        this.setHealthPool(HEALTHPOOL);
        this.setManaPool(MANA_POOL);
        this.setAttackDamage(ATTACK_DAMAGE);
        this.setMagicPower(MAGIC_POWER);

        this.setSecondSkillManaCost(SECOND_SKILL_MANA_COST);    //Divine shield
        this.setThirdSkillManaCost(THIRD_SKILL_MANA_COST);      //Pull
        this.setFourthSkillManaCost(FOURTH_SKILL_MANA_COST);    //Enlightenment
        this.setFifthSkillManaCost(FIFTH_SKILL_MANA_COST);      //Smite

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

    public void setToBuff() {
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffTexture)));
        this.isSelected = 2;
    }

    public void setToBuffSelected() {
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffSelectedTexture)));
        this.isSelected = 3;
    }

    public PaladinTooltip getPaladinTooltip() {
        return paladinTooltip;
    }

    @Override
    protected void die() {
        this.bleedIcon1.remove();
        this.bleedIcon2.remove();
        this.poisonIcon1.remove();
        this.poisonIcon2.remove();
        gpScreen.getPlayerCharacterList().remove(gpScreen.getPlayerCharacterList().indexOf(this));
        this.setDrawable(new SpriteDrawable(new Sprite(isDeadTexture)));
        this.move(GameplayScreen.getAllyPositionArray()[gpScreen.getPlayerCharacterList().size()]);
        this.setDead(true);
    }


    @Override
    public void useFirstSkill(Entity target) {  //Auto attack
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " auto attacks " + target.getClassName(target.getClass()) + " for " + this.getAttackDamage());
        target.receiveDamage(this.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

    @Override
    public void useSecondSkill(Entity target) {  //Divine shield

        this.useMana(getSecondSkillManaCost());
        System.out.println(this.getClassName(this.getClass()) + " uses divine shield on " + target.getClassName(target.getClass()));
        target.setDivineShield(true);
    }

    @Override
    public void useThirdSkill(Entity target) {  //Pull

        this.useMana(getThirdSkillManaCost());
        System.out.println(this.getClassName(this.getClass()) + " pulls " + target.getClassName(target.getClass()));
        gpScreen.getEnemyCharacterList().remove(target);
        gpScreen.getEnemyCharacterList().add(0, target);

        for (int i = 0; i < gpScreen.getEnemyCharacterList().size(); i++)
            gpScreen.getEnemyCharacterList().get(i).move(GameplayScreen.getEnemyPositionArray()[i]);
    }

    @Override
    public void useFourthSkill(Entity target) {  //Enlightenment
        this.useMana(getFourthSkillManaCost());
        System.out.println(target.getClassName(target.getClass()) + "'s current attack damage is: " + target.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current magic power is: " + target.getMagicPower());
        System.out.println(this.getClassName(this.getClass()) + " enlightenments " + target.getClassName(target.getClass()));
        target.setMagicPower((int) Math.ceil(1.5 * target.getMagicPower()));
        target.setAttackDamage((int) Math.ceil(1.5 * target.getAttackDamage()));
        System.out.println(target.getClassName(target.getClass()) + "'s current attack damage is: " + target.getAttackDamage());
        System.out.println(target.getClassName(target.getClass()) + "'s current magic power is: " + target.getMagicPower());
    }

    @Override
    public void useFifthSkill(Entity target) {  //Smite
        this.useMana(getFifthSkillManaCost());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
        System.out.println(this.getClassName(this.getClass()) + " smites " + target.getClassName(target.getClass()) + " for " + 2*this.getMagicPower());
        target.receiveDamage(2*this.getMagicPower());
        System.out.println(target.getClassName(target.getClass()) + "'s current health is: " + target.getCurrentHealth());
    }

}

