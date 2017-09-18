package com.mygdx.game.ui.skillButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.mygdx.game.entities.allies.Cleric;
import com.mygdx.game.entities.allies.Paladin;
import com.mygdx.game.entities.allies.Ranger;
import com.mygdx.game.screens.GameplayScreen;

/**
 * Created by Ja on 2017-05-20.
 */

public class SkillButton6 extends Button {
    protected GameplayScreen gpScreen;

    public SkillButton6(GameplayScreen gameplayScreen) {
        super(prepareSkillButton1Style());
        init();
        this.gpScreen = gameplayScreen;

    }

    private static ButtonStyle prepareSkillButton1Style() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ripoffs.atlas"));
        Skin skin = new Skin(atlas);
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = skin.getDrawable("button6");
        buttonStyle.down = skin.getDrawable("buttonTemplate");

        return buttonStyle;
    }

    private void init() {

        this.setWidth((100));
        this.setHeight(100);
        this.setX(965);
        this.setY(10);

        this.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                reactOnClick();
            }

            @Override
            public boolean longPress(Actor actor, float x, float y){
                super.longPress(actor,x,y);
                reactOnPress();
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int count, int button) {
                if (gpScreen.getPaladin().getPaladinTooltip() != null)
                    gpScreen.getPaladin().getPaladinTooltip().remove();

                if (gpScreen.getCleric().getClericTooltip() != null)
                    gpScreen.getCleric().getClericTooltip().remove();

                if (gpScreen.getRanger().getRangerTooltip() != null)
                    gpScreen.getRanger().getRangerTooltip().remove();
            }
        });
    }

    private void reactOnPress() {

        if (gpScreen.getSelectedSource() instanceof Paladin) {
            this.getStage().addActor(gpScreen.getPaladin().getPaladinTooltip());
            this.getStage().getBatch().begin();
            gpScreen.getPaladin().getPaladinTooltip().drawTooltip(this.getStage().getBatch(), 1f, 6);
        } else if (gpScreen.getSelectedSource() instanceof Ranger) {
            this.getStage().addActor(gpScreen.getRanger().getRangerTooltip());
            this.getStage().getBatch().begin();
            gpScreen.getRanger().getRangerTooltip().drawTooltip(this.getStage().getBatch(), 1f, 6);
        } else if (gpScreen.getSelectedSource() instanceof Cleric) {
            this.getStage().addActor(gpScreen.getCleric().getClericTooltip());
            this.getStage().getBatch().begin();
            gpScreen.getCleric().getClericTooltip().drawTooltip(this.getStage().getBatch(), 1f, 6);
        }
        this.getStage().getBatch().end();
    }

    private void reactOnClick() {
        gpScreen.playTurn();
    }
}
