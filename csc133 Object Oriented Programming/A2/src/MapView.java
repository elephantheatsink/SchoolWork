package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;


public class MapView extends Container implements Observer{
	
	public MapView() {
		//this.setWidth(1024);
		//this.setHeight(768);
		//this.getStyle().setBgColor(ColorUtil.BLACK);
		//this.getStyle().setBgTransparency(255);
		//this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		
		//repaint();
		
		
		
	}
	public void update(Observable o, Object arg) {
		IGameWorld gw = (IGameWorld)arg;
		SpaceCollection go = gw.getGameObjects();
		IIterator gameIterator = go.getIterator();
		while (gameIterator.hasNext()) {
			System.out.println(gameIterator.getNext());
		}
	}
}
