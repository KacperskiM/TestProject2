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

public class RangerTooltip extends Actor {
    private BitmapFont font;
    private int tooltipNumber;
    GameplayScreen gpScreen;

    public RangerTooltip(GameplayScreen gpScreen){
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
            font.draw(batch, "Attacks enemy target for " + gpScreen.getRanger().getAttackDamage() + " damage", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.8f);
        } else if (this.tooltipNumber == 2) {
            font.draw(batch, "Deals " + gpScreen.getRanger().getAttackDamage() + " damage to every enemy", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.8f);
        } else if (this.tooltipNumber == 3) {
            font.draw(batch, "Tries to aim for an enemy target's head", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.2f);
            font.draw(batch, "This skill has 60% chance of dealing " + gpScreen.getRanger().getAttackDamage() + " damage,", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.5f);
            font.draw(batch, "10% chance of instantly killing enemy target and", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 2.3f);
            font.draw(batch, "30% chance of not dealing any damage at all", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 4f);
        } else if (this.tooltipNumber == 4) {
            font.draw(batch, "Tries to slow enemy target dealing " + gpScreen.getRanger().getAttackDamage() + " damage and", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.5f);
            font.draw(batch, "there's 50% chance of pushing enemy to the back", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 2.5f);
        } else if (this.tooltipNumber == 5)
            font.draw(batch, "Gets ready for next attack and will dodge it", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 8, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.8f);
        else if (this.tooltipNumber == 6)
            font.draw(batch, "Passes a turn", Gdx.graphics.getWidth() / 4 + borderResized.getWidth() / 3, Gdx.graphics.getHeight() / 6 + borderResized.getHeight() / 1.8f);



        border.dispose();
        borderResized.dispose();

    }


}
