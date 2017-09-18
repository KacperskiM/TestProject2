package com.mygdx.game.ui.tooltips;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.screens.GameplayScreen;

/**
 * Created by Ja on 2017-09-17.
 */

public class ClericTooltip extends Actor {
    private BitmapFont font;
    private int tooltipNumber;
    GameplayScreen gpScreen;

    public ClericTooltip(GameplayScreen gpScreen) {
        font = new BitmapFont();
        font.setColor((float) 204 / 255, (float) 204 / 255, 0f, 1f);
        this.gpScreen = gpScreen;
    }

    public void drawTooltip(Batch batch, float parentAlpha, int tooltipNumber) {
        this.tooltipNumber = tooltipNumber;
        this.draw(batch, parentAlpha);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        Pixmap border = new Pixmap(Gdx.files.internal("border.png"));
        Pixmap borderResized = new Pixmap(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 4, border.getFormat());
        borderResized.drawPixmap(border, 0, 0, border.getWidth(), border.getHeight(), 0, 0, borderResized.getWidth(), borderResized.getHeight());
        batch.draw(new Texture(borderResized), Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 6);

        font.getData().setScale(1.5f);

        if (this.tooltipNumber == 1) {
            font.draw(batch, "Attacks enemy target for " + gpScreen.getCleric().getAttackDamage() + " damage", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.8f);
        } else if (this.tooltipNumber == 2) {
            font.draw(batch, "Heals allied target for 40 points", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.8f);
        } else if (this.tooltipNumber == 3) {
            font.draw(batch, "Cures bleed and poison status from allied target", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.8f);
        } else if (this.tooltipNumber == 4) {
            font.draw(batch, "Banishes enemy skeleton or zombie for " + (int)( 1.5 * gpScreen.getCleric().getMagicPower()) + " damage", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.5f);
            font.draw(batch, "or enemy vampire for " + gpScreen.getCleric().getMagicPower() + " damage", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 2.5f);
        } else if (this.tooltipNumber == 5) {
            font.draw(batch, "Flashes enemy vampire for " + (int) 1.5 * gpScreen.getCleric().getMagicPower() + " damage", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.5f);
            font.draw(batch, "Flashes enemy skeleton or zombie for " + gpScreen.getCleric().getMagicPower() + " damage", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 2.5f);
        } else if (this.tooltipNumber == 6)
            font.draw(batch, "Passes a turn", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 3, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.8f);


        border.dispose();
        borderResized.dispose();

    }


}
