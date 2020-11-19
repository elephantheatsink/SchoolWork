package com.mycompany.myapp;

import java.util.Vector;

import com.codename1.ui.Form;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;

public class SelfDrawerAnimationForm extends Form implements Runnable {
	private SelfAnimationContainer myContainer ;
    BGSound bgSound = new BGSound("car_race.mp3");
	private Vector<WorldObject> theWorld = new Vector<WorldObject>();
	public SelfDrawerAnimationForm() {
		//...code here to initialize the frame with a BorderLayout
		setLayout(new BorderLayout());
		// create a world containing a self-drawing object
		theWorld.add( new WorldObject(1,1) );
		theWorld.add( new WorldObject(66,77) );
		//create a container on which the world will be drawn
		myContainer = new SelfAnimationContainer(theWorld) ;
		add(BorderLayout.CENTER, myContainer);
		// create a Timer and schedule it
       	UITimer timer = new UITimer (this);
		timer.schedule(15, true, this);
		this.show();
	}
	// called for each timer tick: tells object to move itself, then repaints the container
	public void run () {
		Dimension dCmpSize = new Dimension(myContainer.getWidth(), myContainer.getHeight());
		for (WorldObject obj : theWorld) {
			obj.move(dCmpSize);
		}
		myContainer.repaint();	
        bgSound.play();
    }
}	
