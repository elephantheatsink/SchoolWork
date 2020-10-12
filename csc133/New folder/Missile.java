package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Missile extends MovableGameObject {	//missile is a movable game object
	protected int fuelLevel;						//unique attribute fuel level
	public Missile() {								//constructor
		this.setColor(ColorUtil.MAGENTA);			//missile color
		fuelLevel = 10;								//initial missile fuel level
	}
	public void move() {							//move method same as all the other movable game objects, except
		if(fuelLevel>0) {							//only moves when it has fuel and decrements fuel level
			double deltaX=Math.cos(Math.toRadians(90-this.getDirection()))*this.getSpeed();
			double deltaY=Math.sin(Math.toRadians(90-this.getDirection()))*this.getSpeed();
			this.setLocation(this.getLocation().getX()+deltaX, this.getLocation().getY()+deltaY);
			this.fuelLevel--;
		}
	}
	public String toString() {						//toString method similar to asteroid, but shows the fuel level
		
		String locationDesc = "loc="+Math.round(this.getX()*10.0)/10.0+","+Math.round(this.getY()*10.0)/10.0+" ";
		String colorDesc = "color=" + "[" + ColorUtil.red(getColor()) + ","
		+ ColorUtil.green(getColor()) + ","
		+ ColorUtil.blue(getColor()) + "] ";
		String speedDesc = "speed="+getSpeed()+" ";
		String directionDesc = "direction="+getDirection()+" ";
		String fuelDesc = "fuel="+fuelLevel;
		return "Missile: " +locationDesc+colorDesc +speedDesc+directionDesc+fuelDesc;
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
	}
	
}
