package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


/**
 * Created by Ja on 2017-05-06.
 */

public class Cleric extends Image {


    private final static int WIDTH = 122;
    private final static int HEIGHT = 180;

    private final static int STARTING_X=100;
    private final static  int STARTING_Y=300;

    public Cleric() {
        super(new Texture("cleric.png"));
        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);

        this.setPosition(STARTING_X,STARTING_Y);
    }


    /**
    public void reactOnClick() {
        int xMoveAmount = MathUtils.random(-130, 130);
        int yMoveAmount = 10;
        float moveActionTime = 0.3f;
        Action moveAction = Actions.sequence(
                Actions.moveBy(xMoveAmount, yMoveAmount, moveActionTime, Interpolation.circleOut),
                Actions.moveBy(-xMoveAmount, -yMoveAmount, moveActionTime, Interpolation.circle)
        );

        int xGrowAmount = MathUtils.random(-30, 100);
        int yGrowAmount = 20;
        float growActionTime = 0.2f;
        Action growAction =  Actions.sequence(
                Actions.sizeBy(xGrowAmount, yGrowAmount, growActionTime, Interpolation.circleOut),
                Actions.sizeBy(-xGrowAmount, -yGrowAmount, growActionTime, Interpolation.circle)
        );

        this.addAction(moveAction);
        this.addAction(growAction);

        if(this.getHeight() > 170){
            this.addAction(Actions.rotateBy(MathUtils.randomSign() * 360, 0.4f));
        }
    }
     **/
}