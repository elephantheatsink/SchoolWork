package com.mycompany.a3;
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
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
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
	
	private MapView mv = new MapView();
	
	
	
	
	public Game() {
		gw= new GameWorld(); //game creates a game world. game is composed of a game world
		gw.init();
		PointsView pv = new PointsView(gw);
		
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
		
		
		add(BorderLayout.WEST , westCont);
		add(BorderLayout.NORTH , northCont);
		
		
		add(BorderLayout.CENTER , mv); //add component to center of BorderLayout
		UITimer timer = new UITimer(this);
		timer.schedule(1000 , true, this);
		
		this.show();
		
		
	}
	
	public void run() {
		gw.tick();
		//mv.repaint();
	}
	
	

}
