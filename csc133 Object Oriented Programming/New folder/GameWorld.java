package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.Random;



public class GameWorld extends Observable implements IGameWorld{
	//field declaration
	
	private int tickCount=0; //initial tick count is zero, and will increase every time the game ticks
	private int lives=3; // if there are no lives left, none of the ship commands will work. I'll let it tick and 
						   // make new asteroids, etc. because I might want to have the game keep making new 
						   // asteroids and such after the game is over to make space look alive
	private int score=0; //score starts at 0
	private int playerMissiles = 0;
	private boolean soundOn=true;
	
	
	private Vector<Observer> myObserverList = new Vector<Observer>();
	
	private SpaceCollection mySpaceCollection;
	
	public GameWorld() {
		
		mySpaceCollection = new SpaceCollection(); //initialize the SpaceCollection
		init();
	}
	public void init() {
		//field of gameObjects that are going to be initialized
		
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	
	
	
	
	public void addAsteroid() { //make a new asteroid and add it to the arraylist
			
		Asteroid asteroid = new Asteroid();
		mySpaceCollection.add(asteroid);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
				
	}
	
	public void addFlyingSaucer() { //make a new flying saucer and add it to the arraylist
		FlyingSaucer fs = new FlyingSaucer();
		mySpaceCollection.add(fs);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		
	}
	
	public void addSpaceStation() { //make a new space station and add it to the arraylist
		SpaceStation ss = new SpaceStation();
		mySpaceCollection.add(ss);
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		
	}
	
	public void addShip() { //method to add a new ship
		boolean noShips=true; //boolean to determine whether or not there is a ship in the arraylist. at first there are no ships
		IIterator it = mySpaceCollection.getIterator();//initialize iterator
		if(lives==0) {      //if you have no lives, just print there are no ships
			System.out.println("You have no ships left");
		}else {             //otherwise, if there is more than 0 life (hopefully it doesn't go to negative lives by accident)
			//for (int i=0;i<mySpaceCollection.getSize();i++) { //go through each object in the arraylist to make sure a ship doesn't already exist
			while(it.hasNext()) {
				//if(mySpaceCollection.get(i) instanceof Ship) {
				Object o = it.getNext();
				if(o instanceof Ship) {
					System.out.println("Ship already exists"); //if there is a ship, "noship" would become false
					noShips=false;
					break;                           //and break from the for loop, and it wouldn't add a new ship
				}else {                              
					noShips=true;					//of there is no ship, noShip stays true
				}
			}
			if(noShips==true) {       				//after the for loop, is there still is no ship
				Ship ship = new Ship();				//create a new ship and add it to the array
				mySpaceCollection.add(ship);
				playerMissiles=12;
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				
			}
		}	
	}
	
	public void increase() { //method to increase the ship speed
		boolean isShip=false; //boolean to determine if there is ship in the array. did the boolean opposite from the above one
		IIterator it = mySpaceCollection.getIterator();//initialize iterator
		if(lives>0) { //check if game has any lives left for ship
			//for (int i=0;i<mySpaceCollection.getSize();i++) { //run through the array to look for a ship
			while(it.hasNext()) {
				Object o = it.getNext();
				if (o instanceof Ship) { //if there is a ship
					isShip=true; 						//mark there is a ship
					Ship tempShip=(Ship)o; //create temporary ship to reference the ship
					tempShip.increaseSpeed(); 			//call ship method to increase speed
					break;								//break from the for loop
				}
			}
			if(!isShip)System.out.println("There is no Ship");		//if loop completes and there is no ship, print there is no ship
		}				//if there are no lives for the ship, print there is no ship
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void decrease() {	//method to decrease the ship speed, similar to above method
		boolean isShip=false;
		IIterator it = mySpaceCollection.getIterator();
		if(lives>0) {
			
			while(it.hasNext()) {
				Object o=it.getNext();
				if (o instanceof Ship) {
					isShip=true;
					Ship tempShip=(Ship)o;
					tempShip.decreaseSpeed();
					break;
				}
			}
			if(!isShip)System.out.println("There is no Ship");
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void left() { //method to decrease direction, similar to above method
		boolean isShip=false;
		IIterator it = mySpaceCollection.getIterator();
		if(lives>0) {
			while(it.hasNext()) {
				Object o=it.getNext();
				if (o instanceof Ship) {
					isShip=true;
					Ship tempShip=(Ship)o;
					tempShip.turnLeft();
					break;
				}
			}
			if(!isShip)System.out.println("There is no Ship");
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void right() { //method to increase direction, similar to above method
		boolean isShip=false;
		IIterator it = mySpaceCollection.getIterator();
		if(lives>0) {
			while(it.hasNext()) {
				Object o=it.getNext();
				if (o instanceof Ship) {
					isShip=true;
					Ship tempShip=(Ship)o;
					tempShip.turnRight();
					break;
				}
			}
			if(!isShip)System.out.println("There is no Ship");
		}else System.out.println("There is no Ship");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void fireMissile() { //method to fire the missile, which I had problems earlier.
		if(lives>0) {			//check if ship has any lives left, only run if it has lives
			boolean isShip=false; //start boolean by saying there is no ship
			IIterator it = mySpaceCollection.getIterator();
			Object o;
			
			while(it.hasNext()){ //look for the ship in the array
				o=it.getNext();
				if (o instanceof Ship) {
					isShip=true;
					Ship tempShip=(Ship)o; 	//if there is a ship, make a temporary ship to reference my ship in the array
					if(tempShip.getMissileCount()==0) {		//if there are no missiles, print message
						System.out.println("No more missiles");					
					}else {									//if there are missiles
						Missile missile=new Missile();		//make a new missile
						missile.setLocation(tempShip.getX(),tempShip.getY());		//set missile location to ship's location
						int temp=tempShip.getSpeed()+5;								//make temporary int to hold the value of ship's speed and add more speed
						missile.setSpeed(temp);										//set missile speed to temporary int (just didn't want same thing to happen as last time where the missile would travel the sum of all speeds of missile and ship)
						temp=tempShip.getDirection();								//temporary int holds ship's direction
						missile.setDirection(temp);									//set missile direction to temp int
						tempShip.setMissileCount(tempShip.getMissileCount()-1);
						playerMissiles --;//decrement ship's missile count
						mySpaceCollection.add(missile);								//add missile to the array
					}
				}           
			}if(!isShip)System.out.println("There is no Ship");                 //if there are no ships in the array, or
		}else System.out.println("There is no Ship");							//if there are no lives in the ship, print message
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void jump() {									//jump method
		IIterator it = mySpaceCollection.getIterator();
		while(it.hasNext()) {
			Object o = it.getNext();          //search for ship and call the ship's hyperspace method
			if (o instanceof Ship) {
				Ship tempShip=(Ship)o;
				tempShip.hyperSpace();
				break;
				
			}
		}
	}
	
	public void reload() {								//add new missiles to ship
		IIterator it = mySpaceCollection.getIterator();
		while(it.hasNext()) {
			Object o = it.getNext();          //search for ship and call the ship's hyperspace method
			if (o instanceof Ship) {
				Ship tempShip=(Ship)o;
				tempShip.reloadMissiles();
				playerMissiles=12;
				break;
				
			}
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void kill() {								//kill method that deletes asteroid and missile. since we didn't have to specify which asteroid 
														//and which missile has to be deleted, i just deleted the first ones in the array.
														//eventually, there will be more code to make temporary references to show
														//which missile is close to which asteroid
														//**kill is very similar to eliminate, crash, hit, exterminate and whack, just different game objects are used**
		boolean hasMissile=false;						//using booleans to look for missile and asteroid in the arraylist
		boolean hasAsteroid=false;
		IIterator itMis = mySpaceCollection.getIterator();
		IIterator itAst = mySpaceCollection.getIterator();
		while(itMis.hasNext()) {
			if(itMis.getNext() instanceof Missile) {
				hasMissile=true;
				break;
			}
		}
		while(itAst.hasNext()) {
			if(itAst.getNext() instanceof Asteroid) {
				hasAsteroid=true;
				break;
			}
		}
		
		
		if(hasMissile&&!hasAsteroid) {						//combinations of missile and asteroid existing to print messages
			System.out.println("There is no Asteroid");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			
		}
		if(!hasMissile&&hasAsteroid) {
			System.out.println("There is no Missile");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		if(!hasMissile&&!hasAsteroid) {
			System.out.println("There is no Missile or Asteroid");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		if(hasMissile&&hasAsteroid) {						//if array has both missile and asteroid, go through the list again and 
															//delete the first asteroid and missile. this will be completely rewritten in the future
			for (int i=0;i<mySpaceCollection.getSize();i++) {
				if(mySpaceCollection.get(i) instanceof Missile) {
					mySpaceCollection.remove(i);
					break;
					
				}
			}
			for (int i=0;i<mySpaceCollection.getSize();i++) {
				if(mySpaceCollection.get(i) instanceof Asteroid) {
					Asteroid tempAsteroid = (Asteroid) mySpaceCollection.get(i);
					
					mySpaceCollection.remove(i);
					int origASize=tempAsteroid.getSize();
					if(tempAsteroid.getSize()<=18) {
						
						score=score+5;
						this.setChanged();
						this.notifyObservers(new GameWorldProxy(this));
					}else if(origASize>=19&&origASize<=24) {
						Random rand = new Random();
						Asteroid newA1 = new Asteroid();
						Asteroid newA2 = new Asteroid();
						int newA1Size = 6+rand.nextInt(origASize-12);
						int newA2Size = origASize-newA1Size;
						newA1.setSize(newA1Size);
						mySpaceCollection.add(newA1);
						newA2.setSize(newA2Size);
						mySpaceCollection.add(newA2);
						score = score+4;
						this.setChanged();
						this.notifyObservers(new GameWorldProxy(this));
					}else if(origASize>=25&&origASize<=30) {
						Random rand = new Random();
						Asteroid newA1 = new Asteroid();
						Asteroid newA2 = new Asteroid();
						Asteroid newA3 = new Asteroid();
						int newA1Size = 6+rand.nextInt(origASize-18);
						int newA2Size = 6+rand.nextInt(origASize-newA1Size-12);
						int newA3Size = origASize-newA1Size-newA2Size;
						newA1.setSize(newA1Size);
						mySpaceCollection.add(newA1);
						newA2.setSize(newA2Size);
						mySpaceCollection.add(newA2);
						newA3.setSize(newA3Size);
						mySpaceCollection.add(newA3);
						score = score+3;
						this.setChanged();
						this.notifyObservers(new GameWorldProxy(this));
					}
					break;
				}
					
			}
		}
		
	}
	
	public void eliminate() {							//similar to kill()
		
		boolean hasMissile=false;
		boolean hasSaucer=false;
		IIterator itMis = mySpaceCollection.getIterator();
		IIterator itSau = mySpaceCollection.getIterator();
		while(itMis.hasNext()) {
			if(itMis.getNext() instanceof Missile) {
				hasMissile=true;
				break;
			}
		}
		while(itSau.hasNext()) {
			if(itSau.getNext() instanceof FlyingSaucer) {
				hasSaucer=true;
				break;
			}
		}
	
		
		if(hasMissile&&!hasSaucer) {
			System.out.println("There is no Flying Saucer");
		}
		if(!hasMissile&&hasSaucer) {
			System.out.println("There is no Missile");
		}
		if(!hasMissile&&!hasSaucer) {
			System.out.println("There is no Missile or Flying Saucer");
		}
		if(hasMissile&&hasSaucer) {
						
			for (int i=0;i<mySpaceCollection.getSize();i++) {
			
				if(mySpaceCollection.get(i) instanceof Missile) {
					mySpaceCollection.remove(i);
					break;
					
				}
			}
			for (int i=0;i<mySpaceCollection.getSize();i++) {
				if(mySpaceCollection.get(i) instanceof FlyingSaucer) {
					mySpaceCollection.remove(i);
					break;
				}		
			}
			 
		}
		
		
		score=score+10;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	
	
	public void crash() {							//similar to kill()
		
		boolean hasShip=false;
		boolean hasAsteroid=false;
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof Ship) {
				hasShip=true;
				break;
			}
		}
	
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof Asteroid) {
				hasAsteroid=true;
				break;
			}
		}
		if(hasShip&&!hasAsteroid) {
			System.out.println("There is no Asteroid");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		if(!hasShip&&hasAsteroid) {
			System.out.println("There is no Ship");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		if(!hasShip&&!hasAsteroid) {
			System.out.println("There is no Ship or Asteroid");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		if(hasShip&&hasAsteroid) {
			for (int i=0;i<mySpaceCollection.getSize();i++) {
				if(mySpaceCollection.get(i) instanceof Ship) {
					mySpaceCollection.remove(i);
					break;
					
				}
			}
			for (int i=0;i<mySpaceCollection.getSize();i++) {
				if(mySpaceCollection.get(i) instanceof Asteroid) {
					mySpaceCollection.remove(i);
					break;
				}
					
			}
			lives--;									//if ship gets deleted, decrement lives 
			if(lives==0) {								//if there are no lives left print message
				System.out.println("You have no ships left");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}else {										//if there are lives left add a ship
				addShip();
				
			}
		}
		
	}
	
	public void hit() {							//similar to crash()
		
		boolean hasShip=false;
		boolean hasSaucer=false;
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof Ship) {
				hasShip=true;
				break;
			}
		}
	
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof FlyingSaucer) {
				hasSaucer=true;
				break;
			}
		}
		if(hasShip&&!hasSaucer) {
			System.out.println("There is no Saucer");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		if(!hasShip&&hasSaucer) {
			System.out.println("There is no Ship");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		if(!hasShip&&!hasSaucer) {
			System.out.println("There is no Ship or Flying Saucer");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		if(hasShip&&hasSaucer) {
			for (int i=0;i<mySpaceCollection.getSize();i++) {
				if(mySpaceCollection.get(i) instanceof Ship) {
					mySpaceCollection.remove(i);
					break;
					
				}
			}
			for (int i=0;i<mySpaceCollection.getSize();i++) {
				if(mySpaceCollection.get(i) instanceof FlyingSaucer) {
					mySpaceCollection.remove(i);
					break;
				}
					
			}
			lives--;
			if(lives==0) {
				System.out.println("You have no ships left");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}else {
				addShip();
			}
		}
		
	}
	
	public void exterminate() {							//similar to kill()
		boolean has1stAsteroid=false;
		boolean has2ndAsteroid=false;
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof Asteroid) {
				has1stAsteroid=true;
				break;
			}
		}
		if(has1stAsteroid) {
			for (int i=0;i<mySpaceCollection.getSize();i++) {
				if(mySpaceCollection.get(i) instanceof Asteroid) {
					has2ndAsteroid=true;
					break;
				}
			}
		}
		if(!has1stAsteroid) {      //if it doesn't have 1st asteroid it can't have 2nd asteroid
			System.out.println("There are no Asteroids");
		}
		if(has1stAsteroid&&!has2ndAsteroid) {
			System.out.println("There is only one Asteroid");
		}
		if(has2ndAsteroid) {          //if it has 2nd asteroid it has 1st asteroid. trying to reduce redundancy
			int exCount=0;
			while(exCount<2) {	
				for (int i=0;i<mySpaceCollection.getSize();i++) {
					if(mySpaceCollection.get(i) instanceof Asteroid) {
						mySpaceCollection.remove(i);
						exCount++;
						
					}
				}
			}
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void whack() {								//similar to kill()
		boolean hasSaucer=false;
		boolean hasAsteroid=false;
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof FlyingSaucer) {
				hasSaucer=true;
				break;
			}
		}
	
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof Asteroid) {
				hasAsteroid=true;
				break;
			}
		}
		if(hasSaucer&&!hasAsteroid) {
			System.out.println("There is no Asteroid");
		}
		if(!hasSaucer&&hasAsteroid) {
			System.out.println("There is no Flying Saucer");
		}
		if(!hasSaucer&&!hasAsteroid) {
			System.out.println("There is no Flying Saucer or Asteroid");
		}
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof Asteroid) {
				mySpaceCollection.remove(i);
				break;
				
			}
		}
		for (int i=0;i<mySpaceCollection.getSize();i++) {
			if(mySpaceCollection.get(i) instanceof FlyingSaucer) {
				mySpaceCollection.remove(i);
				break;
			}
				
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void tick() {							//tick the game clock
		
		IIterator it1 = mySpaceCollection.getIterator();
		IIterator it2 = mySpaceCollection.getIterator();
		
		while(it1.hasNext()) {
			Object o = it1.getNext();
			if(o instanceof IMovable) {
				//IMovable mObj = (IMovable) o;
				((IMovable) o).move();
				
			}
			if (o instanceof SpaceStation) {
				SpaceStation tempStation = (SpaceStation) o;
				if(tempStation.getBlinkRate()%tickCount==0) {
					tempStation.blink();
				}
			}
			
		}
		/**
		for (int i=0; i<mySpaceCollection.getSize(); i++) {		//this loop is taken out of the prompt, going thru the arraylist and if object is movable, move() it
			 if (mySpaceCollection.get(i) instanceof IMovable) {
				 IMovable mObj = (IMovable)mySpaceCollection.get(i);
				 mObj.move();
			 }
		}
		for (int i=0; i<mySpaceCollection.getSize(); i++) {		//go through arraylist again
			if (mySpaceCollection.get(i) instanceof SpaceStation) { //if object is space station
				SpaceStation tempStation = (SpaceStation)mySpaceCollection.get(i); //make reference to space station
				if(tempStation.getBlinkRate()%tickCount==0) { 				//if game tick is an exact multiple of ship's blink rate
					tempStation.blink();									//toggle blink
				}
			}
		}
		*/
		tickCount++;								//every time game ticks, increase tick count
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		
	}
	
	
	
	public void printDisplay() {					//method to print the display
		
		int missileDisplay=0;
		for (int i=0;i<mySpaceCollection.getSize();i++) {		//go thru the arraylist to find the ship and get its missile count
			if (mySpaceCollection.get(i) instanceof Ship) {
				Ship tempShip=(Ship)mySpaceCollection.get(i);
				missileDisplay=tempShip.getMissileCount();
			}
		}			
		System.out.println("score: "+score+" missile count: "+missileDisplay+" elapsed time: "+tickCount);	//print the score, missile count, and tick count
	}
	
	public void printMap() {						//method to print the map
		for(int i=0;i<mySpaceCollection.getSize();i++) {		//go through each object in the arraylist and call its toString method and print the string it returns
			System.out.println(mySpaceCollection.get(i).toString());
		}
	}
	
	public int getPlayerScore() {
		return score;
	}
	
	public int getLives() {
		return lives;
	}
	public int getPlayerMissiles() {
		return playerMissiles;
	}
	
	public boolean getSound() {
		return soundOn;
	}
	public void turnSoundOff() {
		soundOn=false;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	public void turnSoundOn() {
		soundOn=true;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	public int getTickCount() {
		return tickCount;
	}
	public void addGameObject(GameObject o) {
		mySpaceCollection.add(o);
		
	}
	public boolean removeGameObject(GameObject o) {
		return true;
	}
	
	public void notifyObservers() {
		GameWorldProxy proxy = new GameWorldProxy(this);
		for (Observer o : myObserverList) {
			o.update((Observable)proxy,null);
		}
	}
	
	public SpaceCollection getGameObjects() {
		return mySpaceCollection;
	}
	public IIterator getIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
