package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.screens.SplashScreen;


public class MyGame extends Game {
	public final static String gameName = "=> Darkest Ripoff (Kappa) <=";
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;

    public final static String GAME_PREFS = "com.mygdx.game.prefs";
    public final static String GAME_SCORE = "com.mygdx.game.prefs.score";

	private boolean paused;

	private int points;

    private Preferences prefs;

/*
    private void updateSavedScoreInPrefs() {
        prefs.putInteger(GAME_SCORE,points);
        prefs.flush();

    }
*/

    public int getPoints() {
		return points;
	}

	@Override
	public void create(){
		init();
        this.setScreen(new SplashScreen(this));
}

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
    }

    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);
    }

	public void setPaused(boolean paused) {
		this.paused = paused;
	}





}