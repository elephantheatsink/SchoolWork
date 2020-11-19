package com.mycompany.a3;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld{
	
	
	private GameWorld realGameWorld;
	public GameWorldProxy(GameWorld gw) {
		realGameWorld =gw;
		
	}
	
	public void addGameObject(GameObject o) {
		realGameWorld.addGameObject(o);
		
	}

	public boolean removeGameObject(GameObject o) {
		return false;
	}
	
	
	public int getPlayerScore() {
		return realGameWorld.getPlayerScore();
	}
	public int getLives() {
		return realGameWorld.getLives();
	}
	public int getPlayerMissiles() {
		return realGameWorld.getPlayerMissiles();
	}
	public boolean getSound() {
		return realGameWorld.getSound();
	}
	public int getTickCount() {
		return realGameWorld.getTickCount();
	}
	public IIterator getIterator() {
		return realGameWorld.getIterator();
	}
	public SpaceCollection getGameObjects() {
		return realGameWorld.getGameObjects();
	}

	
}
