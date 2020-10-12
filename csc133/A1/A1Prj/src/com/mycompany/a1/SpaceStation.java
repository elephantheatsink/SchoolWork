package com.mycompany.a1;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class SpaceStation extends FixedGameObject { 	//space station is a fixed game object
	Random rand = new Random();							//random number generator
	private int blinkRate;								//space station has blink rate
	private boolean visible;							//space station has indicator whether or not its light is on
	
	
	public SpaceStation() {								//constructor
		blinkRate = rand.nextInt(7);					//random blink rate from 0 to 6
		this.setColor(ColorUtil.GREEN);					//space station's color
		visible=true;									//light is on
	}
	
	public int getBlinkRate() {					//blink rate getter
		return blinkRate;
	}
	
	public void setBlinkRate(int newRate) {		//blink rate setter just in case
		blinkRate=newRate;
	}
	
	public void blink() {						//toggle blink
		if(visible)visible=false;
		else visible=true;
	}
	
	public boolean getVisible() {				//visibility getter
		return visible;
	}
	public void setVisibility(boolean newVisible) { //visibility setter
		visible=newVisible;
	}
	
	public String toString() { //toString method same as others but only has location, color, and blinkrate
		
		String locationDesc = "loc="+Math.round(this.getX()*10.0)/10.0+","+Math.round(this.getY()*10.0)/10.0+" ";
		String colorDesc = "color=" + "[" + ColorUtil.red(getColor()) + ","
		+ ColorUtil.green(getColor()) + ","
		+ ColorUtil.blue(getColor()) + "] ";
		
		String rateDesc = "rate="+blinkRate;
		
		return "Space Station: " +locationDesc+colorDesc+rateDesc ;
	}
	
}
