package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends MovableGameObject{	//asteroid is movable
	
	public Asteroid() {						//constructor
		this.setColor(ColorUtil.LTGRAY);	//asteroid color
		this.setSize(6+rand.nextInt(25));	//asteroid size	
		
	}
	 
	public void move() {					//move method same as ship
		
		double deltaX=Math.cos(Math.toRadians(90-this.getDirection()))*this.getSpeed();
		double deltaY=Math.sin(Math.toRadians(90-this.getDirection()))*this.getSpeed();
		this.setLocation(this.getLocation().getX()+deltaX, this.getLocation().getY()+deltaY);

	}	
	public String toString() {				//toString method same as ship except no missile count
		
		String locationDesc = "loc="+Math.round(this.getX()*10.0)/10.0+","+Math.round(this.getY()*10.0)/10.0+" ";
		String colorDesc = "color=" + "[" + ColorUtil.red(getColor()) + ","
		+ ColorUtil.green(getColor()) + ","
		+ ColorUtil.blue(getColor()) + "] ";
		String speedDesc = "speed="+getSpeed()+" ";
		String directionDesc = "direction="+getDirection()+" ";
		String sizeDesc = "size="+getSize()+" ";
		return "Asteroid: " +locationDesc+colorDesc +speedDesc+directionDesc+sizeDesc;
	} 
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		g.fillRoundRect(this.getaX()+pCmpRelPrnt.getX(), 768-(this.getaY()+pCmpRelPrnt.getY()), this.getSize(), this.getSize(), 20, 10);
		
	}
	

}
