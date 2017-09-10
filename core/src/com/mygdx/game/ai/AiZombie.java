package com.mygdx.game.ai;

import com.mygdx.game.entities.enemies.Zombie;
import com.mygdx.game.screens.GameplayScreen;

import java.util.Random;

/**
 * Created by Ja on 2017-09-07.
 */

public class AiZombie {
    GameplayScreen gpScreen;

    public AiZombie(GameplayScreen gpScreen){
        this.gpScreen=gpScreen;
    }

    public void act(Zombie entity) {

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
        else if(entity.getCurrentMana()>=entity.getThirdSkillManaCost() && gpScreen.getEnemyCharacterList().size()<3){   // Use Resurrection
            entity.useThirdSkill();
            return;
        }
        else if(entity.getCurrentMana()>=entity.getFourthSkillManaCost() && entity.getCurrentHealth()<=0.2*entity.getHealthPool()){  // Use Explode
            entity.useFourthSkill(gpScreen.getPlayerCharacterList().get(randomTarget));
        }
        else if(entity.getCurrentMana()>=entity.getSecondSkillManaCost()) {  //Use Toxic Chop
            entity.useSecondSkill(gpScreen.getPlayerCharacterList().get(randomTarget));
        }
        else                                                                 // Use Auto attack
            entity.useFirstSkill(gpScreen.getPlayerCharacterList().get(randomTarget));
    }
}
