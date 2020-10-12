package com.mycompany.myapp;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class TimerGraphicsContainer extends Container{
	public void paint(Graphics g){
		super.paint(g);
		Random myRNG = new Random();
		g.setColor(ColorUtil.BLACK);
		int iShapeX = myRNG.nextInt(getWidth()); //shape location (relative to the
		int iShapeY = myRNG.nextInt(getHeight());//the origin of the container)
		int xSize = myRNG.nextInt (50);
		int ySize = myRNG.nextInt (25);
		//draw a random-sized rounded corner rectangle at a random location
		g.drawRoundRect(getX()+ iShapeX, getY()+ iShapeY,xSize,ySize,20,10);}
}
