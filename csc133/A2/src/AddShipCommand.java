package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class AddShipCommand extends Command{
	private GameWorld gw;
	public AddShipCommand(GameWorld gw) {
		super("Add Space Ship command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.addShip();
		System.out.println("Add Ship.");
	}
}
