package com.mygdx.game.ui.skillButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.entities.allies.Cleric;
import com.mygdx.game.entities.allies.Paladin;
import com.mygdx.game.entities.allies.Ranger;
import com.mygdx.game.screens.GameplayScreen;
import com.mygdx.game.ui.IClickCallback;

/**
 * Created by Ja on 2017-05-20.
 */

public class SkillButton4 extends Button {
    protected GameplayScreen gpScreen;

    public SkillButton4(GameplayScreen gameplayScreen, final IClickCallback callback) {
        super(prepareSkillButton1Style());
        init(callback);
        this.gpScreen = gameplayScreen;

    }

    private static ButtonStyle prepareSkillButton1Style() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ripoffs.atlas"));
        Skin skin = new Skin(atlas);
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = skin.getDrawable("button4");
        buttonStyle.down = skin.getDrawable("buttonTemplate");

        return buttonStyle;
    }

    private void init(final IClickCallback callback) {

        this.setWidth((100));
        this.setHeight(100);
        this.setX(665);
        this.setY(10);


        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();

                reactOnClick();

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void reactOnClick(){
        if(gpScreen.getSelectedTarget()== null)
            return;
        else if(gpScreen.getSelectedSource() instanceof Cleric || gpScreen.getSelectedSource() instanceof Ranger){
            for(int i =0 ; i<gpScreen.getEnemyCharacterList().size();i++){
                if(gpScreen.getSelectedTarget() == gpScreen.getEnemyCharacterList().get(i)){
                    gpScreen.getSelectedSource().useFourthSkill(gpScreen.getSelectedTarget());
                    gpScreen.playTurn();
                    return;
                }
            }
        }
        else if(gpScreen.getSelectedSource() instanceof Paladin){
            for(int i =0;i<gpScreen.getPlayerCharacterList().size();i++){
                if(gpScreen.getSelectedTarget() == gpScreen.getPlayerCharacterList().get(i)){
                    gpScreen.getSelectedSource().useFourthSkill(gpScreen.getSelectedTarget());
                    gpScreen.playTurn();
                    return;
                }
            }
        }
        else
            System.out.println("BUTTON 4 DOESNT WORK!");
    }
}