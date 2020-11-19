package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class WhackCommand extends Command {
	private GameWorld gw;
	public WhackCommand(GameWorld gw) {
		super("Asteroid Whack Saucer Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.whack();
		System.out.println("Whack.");
	}
}