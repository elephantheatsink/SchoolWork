package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Ship extends MovableGameObject implements ISteerable {  //ship is movable game object and steerable
	private int missileCount;				//declare missile count which is unique to ship
	public Ship() {							//ship constructor
		this.setLocation(512,384);			//ship's spawn location
		this.setColor(ColorUtil.WHITE);		//ship's color
		this.setDirection(0);				//ship's direction at spawn
		this.setSpeed(0);					//ship's speed at spawn
		missileCount = 12;					//ship's missile count at spawn
	}
	public void move() {					//every concrete child class has same move method except missile which decrements fuel level
		
		double deltaX=Math.cos(Math.toRadians(90-this.getDirection()))*this.getSpeed();		//using trigonometry to determine delta x and delta y as legs
		double deltaY=Math.sin(Math.toRadians(90-this.getDirection()))*this.getSpeed();		//of a right triangle where speed is the hypotenuse
		this.setLocation(this.getLocation().getX()+deltaX, this.getLocation().getY()+deltaY); //add delta x and y to current location x and y
		
	}
	public int getMissileCount() {			//missile count getter
		return missileCount;
	}
	public void setMissileCount(int x) { 	//missile count setter
		missileCount=x;
	}
	public void increaseSpeed() {			//increase speed by 1 if speed is less than 10
		if(this.getSpeed()<10) {
			this.setSpeed(this.getSpeed()+1);
		}
	}
	public void decreaseSpeed() {			//decrease speed by 1 if speed is more than 0
		if(this.getSpeed()>0) {
			this.setSpeed(this.getSpeed()-1);
		}
	}
	public void turnLeft() {				//subtract 10 degrees to direction and add 360 if it goes negative
		this.setDirection(this.getDirection()-10);
		if(this.getDirection()<0) {
			setDirection(getDirection()+360);
		}
	}
	public void turnRight() {				//add 10 degrees to direction and subtract 360 if it's more than 360
		this.setDirection(this.getDirection()+10);
		if(this.getDirection()>360) {
			setDirection(getDirection()-360);
		}
	}
	public void reloadMissiles() {			//reload, setting missile count to maximum
		missileCount=12;
	}
	
	public void hyperSpace() {				//for the jump method in gameworld
		this.setLocation(512, 384);			//set everything back to spawn location and spawn condition, but don't change missile count
		setDirection(0);
		setSpeed(0);
	}
	public String toString() {				//toString method, make every component a separate string
		
		String locationDesc = "loc="+Math.round(this.getX()*10.0)/10.0+","+Math.round(this.getY()*10.0)/10.0+" "; //gets x and y location and rounds them
		String colorDesc = "color=" + "[" + ColorUtil.red(this.getColor()) + ","			//get the rgb for the color
		+ ColorUtil.green(this.getColor()) + ","
		+ ColorUtil.blue(this.getColor()) + "] ";
		String speedDesc = "speed="+getSpeed()+" ";											//get speed
		String directionDesc = "direction="+getDirection()+" ";								//get direction 
		String missilesDesc = "missiles="+missileCount;										//get missile count
		return "Ship: " +locationDesc+colorDesc +speedDesc+directionDesc+missilesDesc;		//combine it all to one string and return it
	}
	
}
