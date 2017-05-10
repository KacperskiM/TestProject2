package com.mygdx.game.ui;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class ScoreLabel extends Label {

    public ScoreLabel() {
        super("", prepareLabelStyle());
        init();
    }

    private static LabelStyle prepareLabelStyle() {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        return labelStyle;
    }

    private void init() {
        this.setX(20);
        this.setY(650);
    }
}
