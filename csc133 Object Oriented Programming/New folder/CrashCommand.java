package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class CrashCommand extends Command {
	private GameWorld gw;
	public CrashCommand(GameWorld gw) {
		super("Ship Crashed Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.crash();
		System.out.println("Crash.");
	}
}