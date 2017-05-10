package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.mygdx.game.screens.SplashScreen;


public class MyGdxGame extends Game {
	public final static String gameName = "=> Nazwa gry <=";
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;

	private boolean paused;

	private int points;


	public void addPoint(){
		points++;
	}



	public int getPoints() {
		return points;
	}

	@Override
	public void create(){
		this.setScreen(new SplashScreen(this));
}


	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}








}