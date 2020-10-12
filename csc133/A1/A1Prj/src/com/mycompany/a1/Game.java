package com.mycompany.a1;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String; 

public class Game extends Form {
	private GameWorld gw; //game creates a game world. game is composed of a game world
	private boolean isQuitting=false; //used in the 'q' function
	
	
	public Game() {
		gw = new GameWorld(); 
		gw.init();
		play();
	}
	
	
	
	private void play()
	{
		Label myLabel=new Label("Enter anCommand:"); this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				switch (sCommand.charAt(0)){
					case 'a':if (isQuitting==false) {gw.addAsteroid();} //add asteroid
						break;
					
					case 'y':{
						if (isQuitting==false) {  //if 'q' hasn't been pressed yet, or 'q' then 'o' was pressed
							gw.addFlyingSaucer(); //add flying saucer
						}else if (isQuitting==true)System.exit(0); //if 'q' has been pressed, pressing 'y' will quit the game
					}
						break;
														//every command will only work if q is false. pressing q will essentially pause the game
					case 'b':if (isQuitting==false) {gw.addSpaceStation();} //add new blinking space station to the world. 
						break;
					
					case 's':if (isQuitting==false) {gw.addShip();} //add a new ship to the world
						break;

					case 'i':if (isQuitting==false) {gw.increase();} //increase the speed of the ship by a small amount. 
						break;

					case 'd':if (isQuitting==false) {gw.decrease();} //decrease the speed of the ship by a small amount
						break;

					case 'l': if (isQuitting==false) {gw.left();}// turn left by a small amount 
						break;

					case 'r':if (isQuitting==false) { gw.right();}// turn right by a small amount
						break;

					case 'f': if (isQuitting==false) {gw.fireMissile();} //  fire a missile out the front of the ship
						break;

					case 'j': if (isQuitting==false) {gw.jump();} // jump through hyperspace
						break;

					case 'n': {if (isQuitting==false) {gw.reload();// load a new supply of missiles into the ship
						}else {
							System.out.println("Game resumed");
							isQuitting=false; //if game is quitting after pressing 'q', 
						}
					}                           //pressing 'n' will resume the game
						break;

					case 'k': if (isQuitting==false) {gw.kill();}// a missile has struck and killed an asteroid
						break;
					
					case 'e':if (isQuitting==false) {gw.eliminate(); }//a missile has struck and eliminated a flying saucer
						break;

					case 'c': if (isQuitting==false) {gw.crash();} //the ship has crashed into an asteroid
						break;

					case 'h': if (isQuitting==false) {gw.hit();} // the ship has hit a flying saucer
						break;

					case 'x': if (isQuitting==false) {gw.exterminate();}//two asteroids have collided with and exterminated each other
						break;

					case 'w': if (isQuitting==false) {gw.whack();} //one asteroid have whacked another flying sauce
						break;
						
					case 't': if (isQuitting==false) {gw.tick();} //tell the game world that the “game clock” has ticked.
						break;
						
					case 'p':if (isQuitting==false) {gw.printDisplay();} // print a display
						break;
					
					case 'm':if (isQuitting==false) {gw.printMap();} // print a “map” 
						break;
						
					case 'q': {
						System.out.println("Game Paused. Are you sure you want to quit? ");
						System.out.println("Press 'y' to quit. Press 'n' to keep playing.");
						isQuitting=true;       //now pressing 'y' will quit the game instead of making a new flying saucer
					}                          //prompt quit
						break;
						
					
					
					
					
					
					//add code to handle rest of the commands
				} //switch
			} //actionPerformed
			} //new ActionListener()
		); //addActionListener
	
	} //play 
	
	
	

}
