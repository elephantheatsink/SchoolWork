package com.mycompany.a2;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
public class JumpCommand extends Command{
	private GameWorld gw;
	public JumpCommand(GameWorld gw) {
		super("Ship Jump Hyperspace Command");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gw.jump();
		System.out.println("Jump.");
	}
}
