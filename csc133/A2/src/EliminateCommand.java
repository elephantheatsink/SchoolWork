package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class EliminateCommand extends Command {
	private GameWorld gw;
	public EliminateCommand(GameWorld gw) {
		super("Flying Saucer Eliminated Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.eliminate();
		System.out.println("Eliminate.");
	}
}