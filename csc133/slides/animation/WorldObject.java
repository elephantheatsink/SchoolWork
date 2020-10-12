package com.mycompany.myapp;

import java.io.IOException;
import java.util.Random;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class WorldObject {
	Random myRNG = new Random();
	private int currentX = 10, currentY = 70 ;	// the object's current location (relative to 
											// the origin of the component)
	private int incX = 3, incY = 3 ;		// amount of movement on each move
	private int size = 35 ;			// object size
	// create the image to be used for this object
	Image theImage = null;
	public WorldObject(int currentX, int currentY){
		try {// you should copy happyFace.png directly under the src directory
			theImage = Image.createImage("/car.png");
			this.currentX = currentX;
			this.currentY = currentY; 
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	// move this object within the specified boundaries
	public void move (Dimension dCmpSize) {
		// update the object position
		currentX += incX ;
		currentY += incY ;
		// reverse the next movement direction if the location has reached an edge
		if ( (currentX+size >= dCmpSize.getWidth()) || (currentX < 0) ) 
			incX = -incX ;
		if ( (currentY+size >= dCmpSize.getHeight()) || (currentY < 0) ) 
			incY = -incY ; 
	}

	// draw the representation of this object using the received Graphics context
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.drawImage(theImage, pCmpRelPrnt.getX()+currentX, 
										pCmpRelPrnt.getY()+currentY, size, size);
	}
} 