package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class DecreaseCommand extends Command{
	private GameWorld gw;
	public DecreaseCommand(GameWorld gw) {
		super("Decrease Ship Speed");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.decrease();
		System.out.println("Decrease Ship Speed.");
	}
}
