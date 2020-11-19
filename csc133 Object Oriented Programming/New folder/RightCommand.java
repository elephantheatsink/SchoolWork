package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RightCommand extends Command{
	private GameWorld gw;
	public RightCommand(GameWorld gw) {
		super("Turn Ship Right");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.left();
		System.out.println("Turn Ship Right.");
	}
}
