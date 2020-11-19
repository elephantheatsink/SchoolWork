package com.mycompany.myapp;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;

/** This class creates and binds the Timer to the form and provides a runnable (which is
 * the form itself) for the Timer.  The runnable draws graphical shapes of random sizes at
 * random locations. */
public class TimerGraphics extends Form implements Runnable {
	private AnimationContainer myContainer;
	// private TimerGraphicsContainer myContainer;
	public TimerGraphics() {
		// ...code here to initialize the form which uses border layout...
		// create a container on which to do graphics; put it in the center
		setLayout(new BorderLayout());	
		myContainer = new AnimationContainer();
		// myContainer = new TimerGraphicsContainer();
		add(BorderLayout.CENTER, myContainer);
		//create timer and provide a runnable (which is this form)
		UITimer timer = new UITimer(this);
		//make the timer tick every second and bind it to this form
		timer.schedule(100 , true, this);
		this.show();
	}
		// Entered when the Timer ticks 
	public void run() {
		myContainer.repaint();
	}
}

