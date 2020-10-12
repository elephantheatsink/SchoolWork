package com.mycompany.a1;
import java.util.Random;

public abstract class MovableGameObject extends GameObject implements IMovable{		//movable game object is a game object and is movable
	
	private int mySpeed;		//movable objects have speed
	private int myDirection;	//movable objects have direction
	
	Random rand = new Random();		//random number generator
	public MovableGameObject() {	//constructor
		mySpeed = rand.nextInt(11); 		//randomly generate speed from 0-10
		myDirection = rand.nextInt(360);	//randomly generate direction from 0-360
	}
	
	public int getSpeed() {			//speed getter
		return mySpeed;
	}
	
	public int getDirection() {		//direction getter
		return myDirection;
	}
	
	public void setSpeed(int newSpeed) {			//speed setter
		mySpeed = newSpeed;
	}
	
	public void setDirection(int newDirection) {	//direction setter
		myDirection = newDirection;
	}
	
}
