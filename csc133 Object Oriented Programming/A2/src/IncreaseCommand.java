package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class IncreaseCommand extends Command{
	private GameWorld gw;
	public IncreaseCommand(GameWorld gw) {
		super("Increase Ship Speed");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.increase();
		System.out.println("Increase Ship Speed.");
	}
}
