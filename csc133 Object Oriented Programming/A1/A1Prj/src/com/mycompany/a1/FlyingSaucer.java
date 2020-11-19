package com.mycompany.a1;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class FlyingSaucer extends MovableGameObject{		//flying saucer is movable game object
	Random rand = new Random();								//random number generator
	public FlyingSaucer() {									//constructor
		int sizeSelector = rand.nextInt(2);					//select the size, either 1 or 2, for 10 or 20
		if (sizeSelector==0)setSize(10);
			else setSize(20);
		setColor(ColorUtil.YELLOW);							//set color
	}
	
	public void move() {									//move method same as ship
		
		double deltaX=Math.cos(Math.toRadians(90-this.getDirection()))*this.getSpeed();
		double deltaY=Math.sin(Math.toRadians(90-this.getDirection()))*this.getSpeed();
		this.setLocation(this.getLocation().getX()+deltaX, this.getLocation().getY()+deltaY);

	}
	
	public String toString() {								//toString method same as asteroid
		
		String locationDesc = "loc="+Math.round(this.getX()*10.0)/10.0+","+Math.round(this.getY()*10.0)/10.0+" ";
		String colorDesc = "color=" + "[" + ColorUtil.red(getColor()) + ","
		+ ColorUtil.green(getColor()) + ","
		+ ColorUtil.blue(getColor()) + "] ";
		String speedDesc = "speed="+getSpeed()+" ";
		String directionDesc = "direction="+getDirection()+" ";
		String sizeDesc = "size="+getSize()+" ";
		return "Flying Saucer: " +locationDesc+colorDesc +speedDesc+directionDesc+sizeDesc;
	}
}
