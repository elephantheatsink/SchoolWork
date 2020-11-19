package com.mycompany.myapp;

import java.util.Vector;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SelfAnimationContainer extends Container {
	private Vector<WorldObject> theWorld ;
	public SelfAnimationContainer(Vector<WorldObject> theWorld2) {
		theWorld = theWorld2;
	}
	public void DisplayPanel (Vector<WorldObject> world) {
		theWorld = world ;
	}
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(),getY());
		for (WorldObject obj : theWorld) {
			obj.draw(g, pCmpRelPrnt) ;
	    }
    }
}
