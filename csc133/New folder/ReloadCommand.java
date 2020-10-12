package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class ReloadCommand extends Command{
	private GameWorld gw;
	public ReloadCommand(GameWorld gw) {
		super("New Missile Supply Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.reload();
		System.out.println("Reload.");
	}
}
