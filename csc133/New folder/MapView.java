package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Graphics;


public class MapView extends Container implements Observer{
	private IGameWorld gw;
	private SpaceCollection go = new SpaceCollection();
	public MapView() {
		this.setWidth(1024);
		this.setHeight(768);
		//this.getStyle().setBgColor(ColorUtil.BLACK);
		//this.getStyle().setBgTransparency(255);
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		
		repaint();
		
		
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		//SpaceCollection go = gw.getGameObjects();
		IIterator gameIterator = go.getIterator();
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		
		while (gameIterator.hasNext()) {
			//System.out.println(gameIterator.getNext());
			GameObject obj = (GameObject)gameIterator.getNext();
			obj.draw(g, pCmpRelPrnt);
			System.out.println(obj);
		}
		
	}
	
	public void update(Observable o, Object arg) {
		gw = (IGameWorld)arg;
		
		go = gw.getGameObjects();
		/**
		IIterator gameIterator = go.getIterator();
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		
		while (gameIterator.hasNext()) {
			//System.out.println(gameIterator.getNext());
			GameObject obj = (GameObject)gameIterator.getNext();
			obj.draw(g, pCmpRelPrnt);
		}
		*/
		
		repaint();
		
	}
}
