package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.screens.SplashScreen;


public class MyGame extends Game {
	public final static String gameName = "=> Darkest Ripoff (Kappa) <=";

	private boolean paused;
    private boolean result;

	@Override
	public void create(){
        this.setScreen(new SplashScreen(this));
}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}


    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}