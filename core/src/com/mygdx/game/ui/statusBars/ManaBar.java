package com.mygdx.game.ui.statusBars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Ja on 2017-09-12.
 */

public class ManaBar extends ProgressBar {
    private int height = Gdx.graphics.getWidth() / 160;
    private int width = Gdx.graphics.getHeight() / 10;
    private Pixmap pixmap;

    public ManaBar(float max){
        super(0f, max, 1, false, new ProgressBar.ProgressBarStyle());

        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0f,0f,(float)153/255,1f));
        pixmap.fill();
        getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap = new Pixmap(0,height, Pixmap.Format.RGB888);
        pixmap.setColor(new Color((float)51/255,(float)153/255,1f,1f));
        pixmap.fill();
        getStyle().knob = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap = new Pixmap(width,height, Pixmap.Format.RGB888);
        pixmap.setColor(new Color((float)51/255,(float)153/255,1f,1f));
        pixmap.fill();
        getStyle().knobBefore = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap.dispose();

        setWidth(width);
        setHeight(height);
        setAnimateDuration(0.0f);
        setValue(max);
        setAnimateDuration(0.25f);
    }

    public ManaBar(float max, float current){
        super(0f, max, 1, false, new ProgressBar.ProgressBarStyle());

        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0f,0f,(float)153/255,1f));
        pixmap.fill();
        getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap = new Pixmap(0,height, Pixmap.Format.RGB888);
        pixmap.setColor(new Color((float)51/255,(float)153/255,1f,1f));
        pixmap.fill();
        getStyle().knob = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap = new Pixmap(width,height, Pixmap.Format.RGB888);
        pixmap.setColor(new Color((float)51/255,(float)153/255,1f,1f));
        pixmap.fill();
        getStyle().knobBefore = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap.dispose();

        setWidth(width);
        setHeight(height);
        setAnimateDuration(0.0f);
        setValue(current);
        setAnimateDuration(0.25f);
    }
}
