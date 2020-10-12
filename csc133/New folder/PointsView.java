package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer{
	private Label pointsValueLabel;
	private Label livesValueLabel;
	private Label missilesValueLabel;
	private Label soundValueLabel;
	private Label timeValueLabel;
	private Label emptyLabel;
	
	
	public PointsView(Observable myModel) {
		myModel.addObserver(this);
		//instantiate text labels
		Label pointsTextLabel = new Label("Points: ");
		Label livesTextLabel = new Label("Lives: ");
		Label missilesTextLabel = new Label("Missiles: ");
		Label soundTextLabel = new Label("Sound: ");
		Label timeTextLabel = new Label("Time: ");
		//instantiating value labels
		pointsValueLabel = new Label("XXX");
		pointsValueLabel.getStyle().setPadding(Component.RIGHT,5);
		livesValueLabel = new Label("0");
		livesValueLabel.getStyle().setPadding(Component.RIGHT,5);
		missilesValueLabel = new Label("0");
		missilesValueLabel.getStyle().setPadding(Component.RIGHT,5);
		soundValueLabel = new Label("ON");
		soundValueLabel.getStyle().setPadding(Component.RIGHT,5);
		timeValueLabel = new Label("0");
		timeValueLabel.getStyle().setPadding(Component.RIGHT,5);
		emptyLabel = new Label("      ");
		
		
		//set color
		pointsTextLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		//adding a container with a boxlayout
		Container myContainer = new Container();
		myContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		//adding all labels in order
		myContainer.add(pointsTextLabel);
		myContainer.add(pointsValueLabel);
		myContainer.add(livesTextLabel);
		myContainer.add(livesValueLabel);
		myContainer.add(missilesTextLabel);
		myContainer.add(missilesValueLabel);
		myContainer.add(soundTextLabel);
		myContainer.add(soundValueLabel);
		myContainer.add(timeTextLabel);
		myContainer.add(timeValueLabel);
		myContainer.add(emptyLabel);
		
		
		this.add(myContainer);
	}
	
	
	public void update(Observable o, Object arg) {
		//casting o as a GameWorld
		IGameWorld gw = (IGameWorld) arg;
		
		//getting player score and other values to put on the PointsView Container
		int score = gw.getPlayerScore();
		pointsValueLabel.setText(""+(score > 99 ?"" : "0")+(score > 9 ? "" : "0")+score);
		int lives = gw.getLives();
		livesValueLabel.setText(""+lives);
		int missiles = gw.getPlayerMissiles();
		missilesValueLabel.setText(""+missiles);
		boolean sound = gw.getSound();
		if (sound==true) {
			soundValueLabel.setText("ON");
		}else if(sound==false) {
			soundValueLabel.setText("OFF");
		}
		int time=gw.getTickCount()/10;
		timeValueLabel.setText(""+time);
		

		this.repaint(); //repaint to show new values
		
	}

}
