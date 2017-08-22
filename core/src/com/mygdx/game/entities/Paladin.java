package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Ja on 2017-05-20.
 */

public class Paladin extends PlayerCharacter{

    Texture unselectedTexture = new Texture("paladin.png");
    Texture selectedTexture = new Texture("paladin_selected.png");
    Texture toBuffTexture = new Texture("paladin_toBuff.png");
    Texture toBuffSelectedTexture = new Texture("paladin_toBuff_selected.png");


    private int health;
    private int attackDmg;

    private final static int WIDTH = 92;
    private final static int HEIGHT = 150;

    private final static int STARTING_X = 300;
    private final static int STARTING_Y = 300;

    public Paladin() {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
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
        this.getHealth();
    }

    public void useSkil1(String target){
    }



    public void setSelected(){
        this.setDrawable(new SpriteDrawable(new Sprite(selectedTexture)));
    }

    public void setUnselected(){
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
    }
    public void setToBuff(){
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffTexture)));
    }
    public void setToBuffSelected(){
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffSelectedTexture)));
    }
}
