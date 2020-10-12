package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class AddSpaceStationCommand extends Command{
	private GameWorld gw;
	public AddSpaceStationCommand(GameWorld gw) {
		super("Add Station command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.addSpaceStation();
		System.out.println("Add Space Station.");
	}
}
