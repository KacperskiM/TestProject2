package com.mygdx.game.entities.allies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.entities.Entity;


/**
 * Created by Ja on 2017-05-06.
 */

public class Cleric extends Entity {

    private Texture unselectedTexture = new Texture("cleric.png");
    private Texture selectedTexture = new Texture("cleric_selected.png");
    private Texture toBuffTexture = new Texture("cleric_toBuff.png");
    private Texture toBuffSelectedTexture = new Texture("cleric_toBuff_selected.png");


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

    public void setUnselected(){
        this.setDrawable(new SpriteDrawable(new Sprite(unselectedTexture)));
    }

    public void setToBuff(){
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffTexture)));
    }
    public void setToBuffSelected(){
        this.setDrawable(new SpriteDrawable(new Sprite(toBuffSelectedTexture)));
    }


    @Override
    public void useFirstSkill(Entity target) {

    }
}
