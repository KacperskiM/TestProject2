package com.mygdx.game.ai;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.enemies.Skeleton;
import com.mygdx.game.entities.enemies.Vampire;
import com.mygdx.game.entities.enemies.Zombie;
import com.mygdx.game.screens.GameplayScreen;

/**
 * Created by Ja on 2017-09-05.
 */

public class Ai {

    AiZombie zombieAi;
    AiSkeleton skeletonAi;
    AiVampire vampireAi;

    GameplayScreen gpScreen;

    public Ai(GameplayScreen gpScreen) {
        this.gpScreen = gpScreen;
        this.zombieAi = new AiZombie();
        this.skeletonAi = new AiSkeleton();
        this.vampireAi = new AiVampire();
    }

    public void makeAction(Entity entity) {
        int target = selectTarget();

        if (entity instanceof Skeleton) {
            skeletonAi.selectSkill((Skeleton) entity, target);

        } else if (entity instanceof Vampire) {

        } else if (entity instanceof Zombie) {

        }
    }

    private int selectTarget() {
        Entity target = gpScreen.getPlayerCharacterList().get(0);
        int i = 0;
        for (i = 0; i < gpScreen.getPlayerCharacterList().size(); i++) {
            if (gpScreen.getPlayerCharacterList().get(i).getCurrentHealth() < target.getCurrentHealth())
                i = gpScreen.getPlayerCharacterList().get(i).getCurrentHealth();
        }
        return i;
    }

}
