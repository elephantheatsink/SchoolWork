package com.mycompany.a1;

public abstract class FixedGameObject extends GameObject { //fixed game object is a game object
	private int id;						//every fixed object has an id
	public FixedGameObject() {}			//constructor
	public int getId() {				//id getter
		return id;
	}
}
