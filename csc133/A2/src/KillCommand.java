package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class KillCommand extends Command {
	private GameWorld gw;
	public KillCommand(GameWorld gw) {
		super("Killed Asteroid Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.kill();
		System.out.println("Kill.");
	}
}
