package com.mygdx.game.com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Ja on 2017-08-21.
 */

public abstract class Enemy extends Image {
    public abstract void setSelected();


    public abstract void setUnselected();
}
