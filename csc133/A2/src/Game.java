package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String; 

public class Game extends Form {
	private GameWorld gw; //game creates a game world. game is composed of a game world
	private boolean isQuitting=false; //used in the 'q' function
	
	//instantiate buttons
	private Button addAsteroidButton = new Button();
	private Button addSpaceShipButton = new Button();
	private Button addStationButton = new Button();
	private Button addFlyingSaucerButton = new Button();
	private Button increaseButton = new Button();
	private Button decreaseButton = new Button();
	private Button leftButton = new Button();
	private Button rightButton = new Button();
	private Button jumpButton = new Button();
	private Button fireButton = new Button();
	private Button reloadButton = new Button();
	private Button killButton = new Button();
	private Button crashButton = new Button();
	private Button eliminateButton = new Button();
	private Button hitButton = new Button();
	private Button exterminateButton = new Button();
	private Button whackButton = new Button();
	private Button tickButton = new Button();
	private Button exitButton = new Button();
	
	
	
	
	
	public Game() {
		GameWorld gw= new GameWorld(); //game creates a game world. game is composed of a game world
		gw.init();
		PointsView pv = new PointsView(gw);
		MapView mv = new MapView();
		gw.addObserver(mv);
		gw.addObserver(pv);
		
		//Declare all the needed commands for buttons, keys, and side bar
		AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
		AddFlyingSaucerCommand myAddFlying = new AddFlyingSaucerCommand(gw);
		AddShipCommand myAddShip = new AddShipCommand(gw);
		AddSpaceStationCommand myAddStation = new AddSpaceStationCommand(gw);
		FireMissileCommand myFireMissile = new FireMissileCommand(gw);
		IncreaseCommand myIncrease = new IncreaseCommand(gw);
		DecreaseCommand myDecrease = new DecreaseCommand(gw);
		LeftCommand myLeft = new LeftCommand(gw);
		RightCommand myRight = new RightCommand(gw);
		JumpCommand myJump = new JumpCommand(gw);
		ReloadCommand myReload = new ReloadCommand(gw);
		KillCommand myKill = new KillCommand(gw);
		EliminateCommand myEliminate = new EliminateCommand(gw);
		HitCommand myHit = new HitCommand(gw);
		CrashCommand myCrash = new CrashCommand(gw);
		ExterminateCommand myExterminate = new ExterminateCommand(gw); 
		WhackCommand myWhack = new WhackCommand(gw);
		TickCommand myTick = new TickCommand(gw);
		
		ExitCommand myExit = new ExitCommand();
		SoundCommand mySound = new SoundCommand(gw);
		NewCommand myNew = new NewCommand();
		SaveCommand mySave = new SaveCommand();
		UndoCommand myUndo = new UndoCommand();
		AboutCommand myAbout = new AboutCommand();
		
		
		
		
		//Then the commands to the buttons
		addAsteroidButton.setCommand(myAddAsteroid);
		addFlyingSaucerButton.setCommand(myAddFlying);
		addSpaceShipButton.setCommand(myAddShip);
		addStationButton.setCommand(myAddStation);
		fireButton.setCommand(myFireMissile);
		increaseButton.setCommand(myIncrease);
		decreaseButton.setCommand(myDecrease);
		leftButton.setCommand(myLeft);
		rightButton.setCommand(myRight);
		jumpButton.setCommand(myJump);
		reloadButton.setCommand(myReload);
		killButton.setCommand(myKill);
		eliminateButton.setCommand(myEliminate);
		hitButton.setCommand(myHit);
		crashButton.setCommand(myCrash);
		exterminateButton.setCommand(myExterminate);
		whackButton.setCommand(myWhack);
		tickButton.setCommand(myTick);
		
		exitButton.setCommand(myExit);
		
		//button styles
		addAsteroidButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		addAsteroidButton.getUnselectedStyle().setBgTransparency(100);
		addAsteroidButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		addFlyingSaucerButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		addFlyingSaucerButton.getUnselectedStyle().setBgTransparency(100);
		addFlyingSaucerButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		addSpaceShipButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		addSpaceShipButton.getUnselectedStyle().setBgTransparency(100);
		addSpaceShipButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		addStationButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		addStationButton.getUnselectedStyle().setBgTransparency(100);
		addStationButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		fireButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		fireButton.getUnselectedStyle().setBgTransparency(100);
		fireButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		increaseButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		increaseButton.getUnselectedStyle().setBgTransparency(100);
		increaseButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		decreaseButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		decreaseButton.getUnselectedStyle().setBgTransparency(100);
		decreaseButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		leftButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		leftButton.getUnselectedStyle().setBgTransparency(100);
		leftButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		rightButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		rightButton.getUnselectedStyle().setBgTransparency(100);
		rightButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		jumpButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		jumpButton.getUnselectedStyle().setBgTransparency(100);
		jumpButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		reloadButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		reloadButton.getUnselectedStyle().setBgTransparency(100);
		reloadButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		killButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		killButton.getUnselectedStyle().setBgTransparency(100);
		killButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		eliminateButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		eliminateButton.getUnselectedStyle().setBgTransparency(100);
		eliminateButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		hitButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		hitButton.getUnselectedStyle().setBgTransparency(100);
		hitButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		crashButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		crashButton.getUnselectedStyle().setBgTransparency(100);
		crashButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		exterminateButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		exterminateButton.getUnselectedStyle().setBgTransparency(100);
		exterminateButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		whackButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		whackButton.getUnselectedStyle().setBgTransparency(100);
		whackButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		tickButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		tickButton.getUnselectedStyle().setBgTransparency(100);
		tickButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		exitButton.getUnselectedStyle().setFgColor(ColorUtil.MAGENTA);
		exitButton.getUnselectedStyle().setBgTransparency(100);
		exitButton.getUnselectedStyle().setBgColor(ColorUtil.GREEN);
		                 
		
		//Binding play-mode specific key commands
		addKeyListener(-91, myIncrease); //up arrow
		addKeyListener(-92, myDecrease); //down arrow
		addKeyListener(-93, myLeft); //left arrow
		addKeyListener(-94, myRight); //right arrow
		addKeyListener(-90, myFireMissile); //space bar
		
		
		
		this.setLayout(new BorderLayout()); //instantiate BorderLayout
		
		Toolbar myToolbar = new Toolbar(); //instantiate toolbar
		this.setToolbar(myToolbar); //make sure to use lower-case "b", setToolBar() is depreciated
		
		Command fileMenu = new Command("File"); //just using these to label the 
		Command commandMenu = new Command("Commands");//side menu and overflow menu
		myToolbar.addCommandToLeftBar(fileMenu);
		myToolbar.addCommandToRightBar(commandMenu);
		
		//I decided to put file menu on side menu, and commands menu in overflow menu
		//because I couldn't figure out where else to put the checkbox than
		//the side menu.
		myToolbar.addCommandToOverflowMenu(myAddAsteroid);
		myToolbar.addCommandToOverflowMenu(myAddShip);
		myToolbar.addCommandToOverflowMenu(myAddStation);
		myToolbar.addCommandToOverflowMenu(myAddFlying);
		myToolbar.addCommandToOverflowMenu(myReload);
		myToolbar.addCommandToOverflowMenu(myKill);
		myToolbar.addCommandToOverflowMenu(myEliminate);
		myToolbar.addCommandToOverflowMenu(myHit);
		myToolbar.addCommandToOverflowMenu(myCrash);
		myToolbar.addCommandToOverflowMenu(myWhack);
		myToolbar.addCommandToOverflowMenu(myTick);
		myToolbar.addCommandToOverflowMenu(myExit);
		
		myToolbar.addCommandToSideMenu(myNew);
		myToolbar.addCommandToSideMenu(mySave);
		myToolbar.addCommandToSideMenu(myUndo);
		CheckBox soundCheck = new CheckBox();
		soundCheck.setSelected(true);
		soundCheck.setCommand(mySound);
		myToolbar.addComponentToSideMenu(soundCheck);
		myToolbar.addCommandToSideMenu(myAbout);
		myToolbar.addCommandToSideMenu(myExit);
		
		
		
		
		
		
		Container centerCont = new Container();	//instantiate Container
		centerCont.add(mv); //add views to the container
		
		centerCont.getStyle().setBgColor(ColorUtil.BLACK);
		centerCont.getStyle().setBgTransparency(255);
		centerCont.setWidth(1024);
		centerCont.setHeight(768);
		centerCont.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		
		Container westCont = new Container();
		westCont.setLayout(new BoxLayout(BoxLayout.Y_AXIS)); //boxlayout y axis for all the buttons
		
		
		Container northCont = new Container();
		northCont.add(pv);
		northCont.getAllStyles().setBgColor(ColorUtil.CYAN);
		northCont.getStyle().setBgTransparency(255);
		
		//adding buttons to west container
		westCont.add(addAsteroidButton);
		westCont.add(addSpaceShipButton);
		westCont.add(addStationButton);
		westCont.add(addFlyingSaucerButton);
		westCont.add(jumpButton);
		westCont.add(increaseButton);
		westCont.add(decreaseButton);
		westCont.add(leftButton);
		westCont.add(rightButton);
		westCont.add(fireButton);
		westCont.add(reloadButton);
		westCont.add(killButton);
		westCont.add(eliminateButton);
		westCont.add(hitButton);
		westCont.add(crashButton);
		westCont.add(exterminateButton);
		westCont.add(whackButton);
		whackButton.getStyle().setPadding(Component.RIGHT, 2);
		westCont.add(tickButton);
		
		westCont.add(exitButton);
		
		
		
		
		add(BorderLayout.CENTER , centerCont); //add component to center of BorderLayout
		add(BorderLayout.WEST , westCont);
		add(BorderLayout.NORTH , northCont);
		
		repaint();
		this.show();
		
		
	}
	/** originally wanted to use play method in constructor, but got errors with proxy design...
	private void play() {
		//Declare all the needed commands for buttons, keys, and side bar
				AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
				AddFlyingSaucerCommand myAddFlying = new AddFlyingSaucerCommand(gw);
				AddShipCommand myAddShip = new AddShipCommand(gw);
				AddSpaceStationCommand myAddStation = new AddSpaceStationCommand(gw);
				FireMissileCommand myFireMissile = new FireMissileCommand(gw);
				JumpCommand myJump = new JumpCommand(gw);
				KillCommand myKill = new KillCommand(gw);
				EliminateCommand myEliminate = new EliminateCommand(gw);
				HitCommand myHit = new HitCommand(gw);
				CrashCommand myCrash = new CrashCommand(gw);
				ExterminateCommand myExterminate = new ExterminateCommand(gw); 
				WhackCommand myWhack = new WhackCommand(gw);
				TickCommand myTick = new TickCommand(gw);
				PrintMapCommand myPrintMap = new PrintMapCommand(gw);
				PrintDisplayCommand myPrintDisplay = new PrintDisplayCommand(gw);
				ExitCommand myExit = new ExitCommand(gw);
				
				//Then the commands to the buttons
				addAsteroidButton.setCommand(myAddAsteroid);
				addFlyingSaucerButton.setCommand(myAddFlying);
				addSpaceShipButton.setCommand(myAddShip);
				addStationButton.setCommand(myAddStation);
				fireButton.setCommand(myFireMissile);
				jumpButton.setCommand(myJump);
				killButton.setCommand(myKill);
				eliminateButton.setCommand(myEliminate);
				hitButton.setCommand(myHit);
				crashButton.setCommand(myCrash);
				exterminateButton.setCommand(myExterminate);
				whackButton.setCommand(myWhack);
				tickButton.setCommand(myTick);
				mapButton.setCommand(myPrintMap);
				displayButton.setCommand(myPrintDisplay);
				exitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						exitGame();
					}
				});
				
				
				
				
				this.setLayout(new BorderLayout()); //instantiate BorderLayout
				
				Toolbar myToolbar = new Toolbar();
				setToolbar(myToolbar); //make sure to use lower-case "b", setToolBar() is depreciated
				 
				
				Container centerCont = new Container();	//instantiate Container
				centerCont.add(mv); //add views to the container
				
				
				
				Container westCont = new Container();
				westCont.setLayout(new BoxLayout(BoxLayout.Y_AXIS)); //boxlayout y axis for all the buttons
				westCont.getAllStyles().setBgColor(ColorUtil.LTGRAY);
				
				Container northCont = new Container();
				northCont.add(pv);
				northCont.getAllStyles().setBgColor(ColorUtil.CYAN);
				
				
				
				
				
				//adding buttons to west container
				westCont.add(addAsteroidButton);
				westCont.add(addSpaceShipButton);
				westCont.add(addStationButton);
				westCont.add(addFlyingSaucerButton);
				westCont.add(jumpButton);
				westCont.add(fireButton);
				westCont.add(reloadButton);
				westCont.add(killButton);
				westCont.add(eliminateButton);
				westCont.add(hitButton);
				westCont.add(crashButton);
				westCont.add(exterminateButton);
				westCont.add(whackButton);
				westCont.add(tickButton);
				westCont.add(mapButton);
				westCont.add(displayButton);
				westCont.add(exitButton);
				
				
				
				
				add(BorderLayout.CENTER , centerCont); //add component to center of BorderLayout
				add(BorderLayout.WEST , westCont);
				add(BorderLayout.NORTH , northCont);
				
				
				this.show(); 
				
	}
	*/
	/**
	public class ExitCommand extends Command{
		private Game myForm;
		public ExitCommand (Game fForm) {
			super("Exit");
			myForm=fForm;
		}
		@Override
    	public void actionPerformed(ActionEvent evt){
			Boolean bOk = Dialog.show("Confirm quit","Are you sure?","Ok","Cancel");
			if (bOk) {
				Display.getInstance().exitApplication();
			}
			
    	}//actionPerformed
	}
	
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

	*/
	private void play1()
	{
		Label myLabel=new Label("Enter a Command:"); this.addComponent(myLabel);
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
