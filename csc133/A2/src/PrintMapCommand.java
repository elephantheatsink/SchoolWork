package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class PrintMapCommand extends Command {
	private GameWorld gw;
	public PrintMapCommand(GameWorld gw) {
		super("Print Map Button");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.printMap();
	}
}