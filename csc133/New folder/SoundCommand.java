package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command{
	private GameWorld myGw;
	public SoundCommand(GameWorld gw) {
		super("Sound");
		myGw=gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt){
	  if (((CheckBox)evt.getComponent()).isSelected())//getComponent() returns the component 					        //that generated the event
	    myGw.turnSoundOn();
	  else
	    myGw.turnSoundOff();
	  }//actionPerformed
}