package com.mygdx.game.entities;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Ja on 2017-05-20.
 */

public class Ranger extends PlayerCharacter {

    Texture unselectedTexture = new Texture("ranger.png");
    Texture selectedTexture = new Texture("ranger_selected.png");
    Texture toBuffTexture = new Texture("ranger_toBuff.png");
    Texture toBuffSelectedTexture = new Texture("ranger_toBuff_selected.png");


    private final static int WIDTH = 122;
    private final static int HEIGHT = 180;

    private final static int STARTING_X = 200;
    private final static int STARTING_Y = 300;

    public Ranger() {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        this.setPosition(STARTING_X, STARTING_Y);
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

