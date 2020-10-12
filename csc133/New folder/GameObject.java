package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import java.util.Random;

public abstract class GameObject implements IDrawable{
	private Point myaLocation;
	private int myColor; //every game object has a color
	private Point2D myLocation; //every game object has a location
	private int mySize; //every game object has a size, although only asteroids and flying saucers implement it
	Random rand = new Random(); //random number generator for location
	public GameObject() {       
		myLocation = new Point2D((Math.round(rand.nextDouble()*1024)), Math.round(rand.nextDouble()*768)); 
		myaLocation = new Point((int)Math.round(getX()), (int)Math.round(getY()));
	}                //when game objects are created, it is given a random location, except for ship and missile that 
					 //override this
	
	public Point2D getLocation() { //location getter that returns Point2D value
		return myLocation;
	}
	
	public double getX() { //location getter that returns X
		return myLocation.getX();
		
	}
	public double getY() { //location getter that returns Y
		return myLocation.getY();
	}
	public int getaX() {
		return myaLocation.getX();
	}
	public int getaY() {
		return myaLocation.getY();
	}
	public Point getaLocation() {
		return myaLocation;
	}
	
	public void setLocation(double x, double y) { //location setter that accepts x and y coordinates 
		myLocation.setX(x);                       //instead of a Point2D as I had it earlier because it was much more difficult to make a new point every time i wanted to update a location
		myLocation.setY(y);
	}
	
	public int getColor() {     //color getter
		return myColor;
	}
	
	public void setColor(int newColor) { //color setter, not sure how it will work, if it will accept ColorUtil.[color] but 
		myColor = newColor;              //so far there is no method that sets game object colors except for concrete child class constructors
	}
	
	public int getSize() { //size getter
		return mySize;
	}
	public void setSize(int newSize) { //size setter
		mySize=newSize;
	}
	
	
	
	
	
}
