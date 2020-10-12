package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
		
		public ExitCommand () {
			super("Exit");
			
		}
		@Override
    	public void actionPerformed(ActionEvent evt){
			Boolean bOk = Dialog.show("Confirm quit","Are you sure?","Ok","Cancel");
			if (bOk) {
				Display.getInstance().exitApplication();
			}
			
    	}//actionPerformed
	}