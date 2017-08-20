package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;


/**
 * Created by Ja on 2017-05-06.
 */

public class Cleric extends Image {

    Texture unselectedTexture = new Texture("cleric.png");
    Texture selectedTexture = new Texture("cleric_selected.png");

    private final static int WIDTH = 122;
    private final static int HEIGHT = 180;

    private final static int STARTING_X=100;
    private final static  int STARTING_Y=300;

    public Cleric() {
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);

        this.setPosition(STARTING_X,STARTING_Y);
    }


    public void setSelected(){
        this.setDrawable(new SpriteDrawable(new Sprite(selectedTexture)));
    }

    public void setUnSelected(){
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
    }
}
