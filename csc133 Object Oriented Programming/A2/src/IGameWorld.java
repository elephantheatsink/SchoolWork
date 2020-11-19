package com.mycompany.a2;

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
