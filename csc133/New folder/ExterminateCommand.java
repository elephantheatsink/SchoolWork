package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class ExterminateCommand extends Command {
	private GameWorld gw;
	public ExterminateCommand(GameWorld gw) {
		super("Asteroid Exterminate Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.exterminate();
		System.out.println("Exterminate.");
	}
}