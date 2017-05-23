package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Ja on 2017-05-20.
 */

public class Paladin extends Image{

    private int health;
    private int attackDmg;


    private final static int WIDTH = 92;
    private final static int HEIGHT = 150;

    private final static int STARTING_X = 300;
    private final static int STARTING_Y = 300;

    public Paladin() {
        super(new Texture("paladin.png"));
        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);

        health = 300;
        attackDmg = 50;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = attackDmg;
    }

    public int getHealth() {
        if(this.health<=0){
            this.isDead();
        }
        return health;
    }

    private void isDead() {
        //
    }

    public void takeHealth(int health) {
        this.health -=health;
    }

    public void useSkill1(String target){

    }
}
