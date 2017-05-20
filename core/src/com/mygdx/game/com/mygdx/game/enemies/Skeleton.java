package com.mygdx.game.com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Ja on 2017-05-20.
 */

public class Skeleton extends Image {

    private final static int WIDTH = 122;
    private final static int HEIGHT = 180;

    private final static int STARTING_X=650;
    private final static  int STARTING_Y=300;

    public Skeleton() {
        super(new Texture("skeleton.png"));
        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);

        this.setPosition(STARTING_X,STARTING_Y);
    }
}
