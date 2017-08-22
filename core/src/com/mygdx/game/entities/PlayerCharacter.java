package com.mygdx.game.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Ja on 2017-08-21.
 */

public abstract class PlayerCharacter extends Image {

    public abstract void setSelected() ;

    public abstract void setUnselected();

    public abstract void setToBuff();
}
