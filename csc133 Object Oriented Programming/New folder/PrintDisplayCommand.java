package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class PrintDisplayCommand extends Command {
	private GameWorld gw;
	public PrintDisplayCommand(GameWorld gw) {
		super("Print Display Button");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.printDisplay();
	}
}