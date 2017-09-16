package com.mygdx.game.ui.statusBars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Ja on 2017-09-16.
 */

public class PoisonIcon extends Image{
    private Texture poisonTexture = new Texture("poison.png");

    public PoisonIcon(int posX, int posY){
        this.setDrawable(new SpriteDrawable(new Sprite(poisonTexture)));
        this.setOrigin(posX/2, posY/2);
        this.setSize(8, 8);
        this.setPosition(posX, posY);
    }

}
