package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class HitCommand extends Command {
	private GameWorld gw;
	public HitCommand(GameWorld gw) {
		super("Ship Hit Flying Saucer Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.hit();
		System.out.println("Hit.");
	}
}