package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

/*This time instead of drawing shapes of random sizes at random locations, 
 * we will draw the same image (a simple filled shape) that moves on a path. 
 * The form is the same as above example except that the tick would happen every 100 ms… */
public class AnimationContainer extends Container {
	private int currentX = 0, currentY = 0 ;	// image location (relative to the origin 											//of the component)
	private int incX = 3, incY = 3 ;		// amount of movement 
	private int size = 20 ;
	// update the image on the container
	public void paint(Graphics g) {
		super.paint (g);
		// draw the image (a simple filled rounded corner rect) at the current location.
		g.setColor(ColorUtil.BLACK);
		g.fillRoundRect(getX()+currentX, getY()+currentY, size, size, 20, 10);
		// update the image position for the next draw
		currentX += incX ;
		currentY += incY ;
		// reverse the movement direction if the image reaches an edge
		if ( (currentX+size >= getWidth()) || (currentX < 0) ) 
			incX = -incX ;
		if ( (currentY+size >= getHeight()) || (currentY < 0) )
			incY = -incY ;
	}
}