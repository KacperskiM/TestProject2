package com.mygdx.game.ui.statusBars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Ja on 2017-09-16.
 */

public class BleedIcon extends Image {
    private Texture bleedTexture = new Texture("bleed.png");

    public BleedIcon(int posX, int posY) {
        this.setDrawable(new SpriteDrawable(new Sprite(bleedTexture)));
        this.setOrigin(posX / 2, posY / 2);
        this.setSize(Gdx.graphics.getWidth()/160, Gdx.graphics.getHeight()/160);
        this.setPosition(posX, posY);
    }
}
