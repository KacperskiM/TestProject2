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
        this.zombieAi = new AiZombie(gpScreen);
        this.skeletonAi = new AiSkeleton(gpScreen);
        this.vampireAi = new AiVampire(gpScreen);
    }

    public void makeAction(Entity entity) {

        if (entity instanceof Skeleton) {
            skeletonAi.act((Skeleton) entity);

        } else if (entity instanceof Vampire) {

        } else if (entity instanceof Zombie) {

        }
    }



}
