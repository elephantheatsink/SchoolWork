package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	public AboutCommand () {
		super("About");
	}
	public void actionPerformed(ActionEvent evt){
		Dialog.show("Beomjin Han", "CSC 133 - Assignment 2", "Ok","Cancel");
	}
}
