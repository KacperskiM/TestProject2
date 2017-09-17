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

public class SkillButton2 extends Button {

    protected GameplayScreen gpScreen;

    public SkillButton2(GameplayScreen gameplayScreen  ) {
        super(prepareSkillButton1Style());
        init();
        this.gpScreen = gameplayScreen;

    }

    private static ButtonStyle prepareSkillButton1Style() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ripoffs.atlas"));
        Skin skin = new Skin(atlas);
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = skin.getDrawable("button2");
        buttonStyle.down = skin.getDrawable("buttonTemplate");

        return buttonStyle;
    }

    private void init(  ) {

        this.setWidth((100));
        this.setHeight(100);
        this.setX(365);
        this.setY(10);

        this.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event,float x,float y, int count, int button) {
                super.tap(event,x,y,count,button);
                reactOnClick();
            }

            @Override
            public boolean longPress(Actor actor, float x, float y){
                super.longPress(actor,x,y);
                reactOnPress();
                return true;
            }
        });
    }

    private void reactOnPress() {
        //TODO: button tooltip
    }

    private void reactOnClick(){
        if((gpScreen.getSelectedTarget()== null && !(gpScreen.getSelectedSource() instanceof Ranger)) || gpScreen.getSelectedSource().getCurrentMana()< gpScreen.getSelectedSource().getSecondSkillManaCost())
            return;

        else if(gpScreen.getSelectedSource() instanceof Cleric || gpScreen.getSelectedSource() instanceof Paladin){
            for(int i=0; i<gpScreen.getPlayerCharacterList().size();i++){
                if(gpScreen.getSelectedTarget() == gpScreen.getPlayerCharacterList().get(i)){
                    gpScreen.getSelectedSource().useSecondSkill(gpScreen.getSelectedTarget());
                    gpScreen.playTurn();
                    return;
                }
            }
        }
        else if(gpScreen.getSelectedSource() instanceof Ranger){
            ((Ranger) gpScreen.getSelectedSource()).useSecondSkill();
            gpScreen.playTurn();
        }
        else
            System.out.println("BUTTON 2 DOESNT WORK!");
    }
}
