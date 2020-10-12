package com.mycompany.a3;

public interface IGameWorld {
	IIterator getIterator();
	void addGameObject(GameObject o);
	boolean removeGameObject(GameObject o);
	
	int getPlayerScore();
	int getLives();
	int getPlayerMissiles();
	boolean getSound();
	int getTickCount();
	SpaceCollection getGameObjects();
	
}
