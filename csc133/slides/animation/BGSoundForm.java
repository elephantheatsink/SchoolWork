package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class BGSoundForm extends Form implements ActionListener{

	private BGSound bgSound;
	private boolean bPause = false;
	public BGSoundForm() {
		Button bButton = new Button("Pause/Play");
		//...[style and add bButton to the form]
		this.add(bButton);
		// create action listener
		bButton.addActionListener(this);
		bgSound = new BGSound("alarm.wav");
		bgSound.play();
		this.show();
	}

	public void actionPerformed(ActionEvent evt) {
		bPause = !bPause;
		if (bPause)
			bgSound.pause();
		else
			bgSound.play();
	}
}
