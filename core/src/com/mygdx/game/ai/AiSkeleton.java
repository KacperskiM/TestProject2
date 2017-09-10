package com.mygdx.game.ai;

import com.mygdx.game.entities.enemies.Skeleton;
import com.mygdx.game.screens.GameplayScreen;
import java.util.Random;

/**
 * Created by Ja on 2017-09-07.
 */

public class AiSkeleton {

    GameplayScreen gpScreen;

    public AiSkeleton(GameplayScreen gpScreen){
        this.gpScreen=gpScreen;
    }

    public void act(Skeleton entity) {

        // Checking if AA is enough to kill player character
        for(int i=0;i<gpScreen.getPlayerCharacterList().size();i++){
            if(gpScreen.getPlayerCharacterList().get(i).getCurrentHealth()<= entity.getAttackDamage()){
                entity.useFirstSkill(gpScreen.getPlayerCharacterList().get(i));
                return;
            }
        }
        Random rand = new Random();
        int randomTarget = rand.nextInt(gpScreen.getPlayerCharacterList().size());

        // Checking if entity has mana for any skill
        if(entity.getCurrentMana()<entity.getSecondSkillManaCost() && entity.getCurrentMana()<entity.getThirdSkillManaCost() && entity.getCurrentMana()<entity.getFourthSkillManaCost()) {
            entity.useFirstSkill((gpScreen.getPlayerCharacterList().get(randomTarget)));
            return;
        }

        else if(entity.getCurrentMana()>=entity.getThirdSkillManaCost()){   // Use Swing
            entity.useThirdSkill(gpScreen.getPlayerCharacterList().get(randomTarget));
            return;
        }
        else if(entity.getCurrentMana()>=entity.getSecondSkillManaCost()){  // Use Bleeding
            entity.useSecondSkill(gpScreen.getPlayerCharacterList().get(randomTarget));
        }
        else if(entity.getCurrentMana()>=entity.getFourthSkillManaCost()) {  //Use Fear
            entity.useFourthSkill(gpScreen.getPlayerCharacterList().get(0));
        }
        else                                                                 // Use Auto attack
            entity.useFirstSkill(gpScreen.getPlayerCharacterList().get(randomTarget));
    }
}
