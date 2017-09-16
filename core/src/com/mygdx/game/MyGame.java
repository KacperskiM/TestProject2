package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.screens.SplashScreen;


public class MyGame extends Game {
	public final static String gameName = "=> Darkest Ripoff (Kappa) <=";
	//public final static int WIDTH = 1280;
	//public final static int HEIGHT = 720;



	private boolean paused;



/*
    private void updateSavedScoreInPrefs() {
        prefs.putInteger(GAME_SCORE,points);
        prefs.flush();

    }
*/


	@Override
	public void create(){
        this.setScreen(new SplashScreen(this));
}



	public void setPaused(boolean paused) {
		this.paused = paused;
	}





}