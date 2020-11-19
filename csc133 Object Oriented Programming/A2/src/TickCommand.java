package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class TickCommand extends Command {
	private GameWorld gw;
	public TickCommand(GameWorld gw) {
		super("Tick Clock Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.tick();
		System.out.println("Tick.");
	}
}