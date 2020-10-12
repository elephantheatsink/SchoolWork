package com.mycompany.a1;

import java.util.ArrayList;

public class GameWorld {
	//field declaration
	public ArrayList<GameObject> gameObjs; //name of the arraylist that will hold all the game objects
	                                       //tutor told me to make this public for some reason.
	protected int tickCount=0; //initial tick count is zero, and will increase every time the game ticks
	protected int lives=3; // if there are no lives left, none of the ship commands will work. I'll let it tick and 
						   // make new asteroids, etc. because I might want to have the game keep making new 
						   // asteroids and such after the game is over to make space look alive
	protected int score=0; //score starts at 0
	public void init() {
		//field of gameObjects that are going to be initialized
		gameObjs = new ArrayList<GameObject>(); //initialize the arraylist
	}
	
	public void addAsteroid() { //make a new asteroid and add it to the arraylist
			
		Asteroid asteroid = new Asteroid();
		gameObjs.add(asteroid);
				
	}
	
	public void addFlyingSaucer() { //make a new flying saucer and add it to the arraylist
		FlyingSaucer fs = new FlyingSaucer();
		gameObjs.add(fs);
		
	}
	
	public void addSpaceStation() { //make a new space station and add it to the arraylist
		SpaceStation ss = new SpaceStation();
		gameObjs.add(ss);
		
	}
	
	public void addShip() { //method to add a new ship
		boolean noShips=true; //boolean to determine whether or not there is a ship in the arraylist. at first there are no ships
		if(lives==0) {      //if you have no lives, just print there are no ships
			System.out.println("You have no ships left");
		}else {             //otherwise, if there is more than 0 life (hopefully it doesn't go to negative lives by accident)
			for (int i=0;i<gameObjs.size();i++) { //go through each object in the arraylist to make sure a ship doesn't already exist
				if(gameObjs.get(i) instanceof Ship) {
					System.out.println("Ship already exists"); //if there is a ship, "noship" would become false
					noShips=false;
					break;                           //and break from the for loop, and it wouldn't add a new ship
				}else {                              
					noShips=true;					//of there is no ship, noShip stays true
				}
			}
			if(noShips==true) {       				//after the for loop, is there still is no ship
				Ship ship = new Ship();				//create a new ship and add it to the array
				gameObjs.add(ship);
			}
		}	
	}
	
	public void increase() { //method to increase the ship speed
		boolean isShip=false; //boolean to determine if there is ship in the array. did the boolean opposite from the above one
		if(lives>0) { //check if game has any lives left for ship
			for (int i=0;i<gameObjs.size();i++) { //run through the array to look for a ship
				if (gameObjs.get(i) instanceof Ship) { //if there is a ship
					isShip=true; 						//mark there is a ship
					Ship tempShip=(Ship)gameObjs.get(i); //create temporary ship to reference the ship
					tempShip.increaseSpeed(); 			//call ship method to increase speed
					break;								//break from the for loop
				}
			}
			if(!isShip)System.out.println("There is no Ship");		//if loop completes and there is no ship, print there is no ship
		}else System.out.println("There is no Ship");				//if there are no lives for the ship, print there is no ship
	}
	
	public void decrease() {	//method to decrease the ship speed, similar to above method
		boolean isShip=false;
		if(lives>0) {
			for (int i=0;i<gameObjs.size();i++) {
				if (gameObjs.get(i) instanceof Ship) {
					isShip=true;
					Ship tempShip=(Ship)gameObjs.get(i);
					tempShip.decreaseSpeed();
					break;
				}
			}
			if(!isShip)System.out.println("There is no Ship");
		}else System.out.println("There is no Ship");
	}
	
	public void left() { //method to decrease direction, similar to above method
		boolean isShip=false;
		if(lives>0) {
			for (int i=0;i<gameObjs.size();i++) {
				if (gameObjs.get(i) instanceof Ship) {
					isShip=true;
					Ship tempShip=(Ship)gameObjs.get(i);
					tempShip.turnLeft();
					break;
				}
			}
			if(!isShip)System.out.println("There is no Ship");
		}else System.out.println("There is no Ship");
	}
	
	public void right() { //method to increase direction, similar to above method
		boolean isShip=false;
		if(lives>0) {
			for (int i=0;i<gameObjs.size();i++) {
				if (gameObjs.get(i) instanceof Ship) {
					isShip=true;
					Ship tempShip=(Ship)gameObjs.get(i);
					tempShip.turnRight();
					break;
				}
			}
			if(!isShip)System.out.println("There is no Ship");
		}else System.out.println("There is no Ship");
	}
	
	public void fireMissile() { //method to fire the missile, which I had problems earlier.
		if(lives>0) {			//check if ship has any lives left, only run if it has lives
			boolean isShip=false; //start boolean by saying there is no ship
			int s=0;			//this int will remember where in the array the ship is at so we don't look for it over and over
			for (int i=0;i<gameObjs.size();i++) { //look for the ship in the array
				if (gameObjs.get(i) instanceof Ship) {
					s=i;
					isShip=true; 					//if there is a ship, indicate it with boolean 
					break;							//and break from the for loop
				}
			}
			if(isShip) {							//boolean allows for two different scenarios
				Ship tempShip=(Ship)gameObjs.get(s); 	//if there is a ship, make a temporary ship to reference my ship in the array
				if(tempShip.getMissileCount()==0) {		//if there are no missiles, print message
					System.out.println("No more missiles");					
				}else {									//if there are missiles
					Missile missile=new Missile();		//make a new missile
					missile.setLocation(tempShip.getX(),tempShip.getY());		//set missile location to ship's location
					int temp=tempShip.getSpeed()+5;								//make temporary int to hold the value of ship's speed and add more speed
					missile.setSpeed(temp);										//set missile speed to temporary int (just didn't want same thing to happen as last time where the missile would travel the sum of all speeds of missile and ship)
					temp=tempShip.getDirection();								//temporary int holds ship's direction
					missile.setDirection(temp);									//set missile direction to temp int
					tempShip.setMissileCount(tempShip.getMissileCount()-1); 	//decrement ship's missile count
					
					/**
					this was the code before that kept making the ship's 
					location the same as moving missile's location
					
					
					Missile missile=new Missile();
					missile.myLocation=tempShip.myLocation;
					missile.mySpeed=tempShip.mySpeed+5; 
					missile.myDirection=tempShip.myDirection;
					tempShip.missileCount--;
					
					
					I learned that getters and setters are super important,
					and I could make every attribute private, instead of protected
					as I did in the beginning. I may have missed a couple attributes 
					and left them protected.
					
					or maybe if i made a new ship like "Ship tempShip = new Ship();tempShip=(Ship)gameObjs.get(i);" it could have worked?
					 */
					
					gameObjs.add(missile);										//add missile to the array
				}
			}else System.out.println("There is no Ship");						//if there are no ships in the array, or
		}else System.out.println("There is no Ship");							//if there are no lives in the ship, print message
	}
	
	public void jump() {									//jump method
		for (int i=0;i<gameObjs.size();i++) {			//search for ship and call the ship's hyperspace method
			if (gameObjs.get(i) instanceof Ship) {
				Ship tempShip=(Ship)gameObjs.get(i);
				tempShip.hyperSpace();
				break;
				
			}
		}
	}
	
	public void reload() {								//add new missiles to ship
		for (int i=0;i<gameObjs.size();i++) {			//search for ship and call the ship's reloadMissiles method
			if (gameObjs.get(i) instanceof Ship) {
				Ship tempShip=(Ship)gameObjs.get(i);
				tempShip.reloadMissiles();
				break;
				
			}
		}
	}
	
	public void kill() {								//kill method that deletes asteroid and missile. since we didn't have to specify which asteroid 
														//and which missile has to be deleted, i just deleted the first ones in the array.
														//eventually, there will be more code to make temporary references to show
														//which missile is close to which asteroid
														//**kill is very similar to eliminate, crash, hit, exterminate and whack, just different game objects are used**
		boolean hasMissile=false;						//using booleans to look for missile and asteroid in the arraylist
		boolean hasAsteroid=false;
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Missile) {
				hasMissile=true;
				break;
			}
		}
	
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Asteroid) {
				hasAsteroid=true;
				break;
			}
		}
		if(hasMissile&&!hasAsteroid) {						//combinations of missile and asteroid existing to print messages
			System.out.println("There is no Asteroid");
		}
		if(!hasMissile&&hasAsteroid) {
			System.out.println("There is no Missile");
		}
		if(!hasMissile&&!hasAsteroid) {
			System.out.println("There is no Missile or Asteroid");
		}
		if(hasMissile&&hasAsteroid) {						//if array has both missile and asteroid, go through the list again and 
															//delete the first asteroid and missile. this will be completely rewritten in the future
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof Missile) {
					gameObjs.remove(i);
					break;
					
				}
			}
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof Asteroid) {
					gameObjs.remove(i);
					break;
				}
					
			}
		}
		
		
		score=score+5;										//increase score
	}
	
	public void eliminate() {							//similar to kill()
		
		boolean hasMissile=false;
		boolean hasSaucer=false;
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Missile) {
				hasMissile=true;
				break;
			}
		}
	
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof FlyingSaucer) {
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
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof Missile) {
					gameObjs.remove(i);
					break;
					
				}
			}
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof FlyingSaucer) {
					gameObjs.remove(i);
					break;
				}
					
			}
		}
		
		
		score=score+10;
	}
	
	
	
	public void crash() {							//similar to kill()
		
		boolean hasShip=false;
		boolean hasAsteroid=false;
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Ship) {
				hasShip=true;
				break;
			}
		}
	
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Asteroid) {
				hasAsteroid=true;
				break;
			}
		}
		if(hasShip&&!hasAsteroid) {
			System.out.println("There is no Asteroid");
		}
		if(!hasShip&&hasAsteroid) {
			System.out.println("There is no Ship");
		}
		if(!hasShip&&!hasAsteroid) {
			System.out.println("There is no Ship or Asteroid");
		}
		if(hasShip&&hasAsteroid) {
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof Ship) {
					gameObjs.remove(i);
					break;
					
				}
			}
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof Asteroid) {
					gameObjs.remove(i);
					break;
				}
					
			}
			lives--;									//if ship gets deleted, decrement lives 
			if(lives==0) {								//if there are no lives left print message
				System.out.println("You have no ships left");
			}else {										//if there are lives left add a ship
				addShip();
				
			}
		}
		
	}
	
	public void hit() {							//similar to crash()
		
		boolean hasShip=false;
		boolean hasSaucer=false;
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Ship) {
				hasShip=true;
				break;
			}
		}
	
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof FlyingSaucer) {
				hasSaucer=true;
				break;
			}
		}
		if(hasShip&&!hasSaucer) {
			System.out.println("There is no Saucer");
		}
		if(!hasShip&&hasSaucer) {
			System.out.println("There is no Ship");
		}
		if(!hasShip&&!hasSaucer) {
			System.out.println("There is no Ship or Flying Saucer");
		}
		if(hasShip&&hasSaucer) {
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof Ship) {
					gameObjs.remove(i);
					break;
					
				}
			}
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof FlyingSaucer) {
					gameObjs.remove(i);
					break;
				}
					
			}
			lives--;
			if(lives==0) {
				System.out.println("You have no ships left");
			}else {
				addShip();
			}
		}
			
	}
	
	public void exterminate() {							//similar to kill()
		boolean has1stAsteroid=false;
		boolean has2ndAsteroid=false;
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Asteroid) {
				has1stAsteroid=true;
				break;
			}
		}
		if(has1stAsteroid) {
			for (int i=0;i<gameObjs.size();i++) {
				if(gameObjs.get(i) instanceof Asteroid) {
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
				for (int i=0;i<gameObjs.size();i++) {
					if(gameObjs.get(i) instanceof Asteroid) {
						gameObjs.remove(i);
						exCount++;
						
					}
				}
			}
		}	
	}
	
	public void whack() {								//similar to kill()
		boolean hasSaucer=false;
		boolean hasAsteroid=false;
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof FlyingSaucer) {
				hasSaucer=true;
				break;
			}
		}
	
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Asteroid) {
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
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof Asteroid) {
				gameObjs.remove(i);
				break;
				
			}
		}
		for (int i=0;i<gameObjs.size();i++) {
			if(gameObjs.get(i) instanceof FlyingSaucer) {
				gameObjs.remove(i);
				break;
			}
				
		}
		
	}
	
	public void tick() {							//tick the game clock
		for (int i=0; i<gameObjs.size(); i++) {		//this loop is taken out of the prompt, going thru the arraylist and if object is movable, move() it
			 if (gameObjs.get(i) instanceof IMovable) {
				 IMovable mObj = (IMovable)gameObjs.get(i);
				 mObj.move();
			 }
		}
		for (int i=0; i<gameObjs.size(); i++) {		//go through arraylist again
			if (gameObjs.get(i) instanceof SpaceStation) { //if object is space station
				SpaceStation tempStation = (SpaceStation)gameObjs.get(i); //make reference to space station
				if(tempStation.getBlinkRate()%tickCount==0) { 				//if game tick is an exact multiple of ship's blink rate
					tempStation.blink();									//toggle blink
				}
			}
		}
		tickCount++;								//every time game ticks, increase tick count
	}
	
	
	
	public void printDisplay() {					//method to print the display
		
		int missileDisplay=0;
		for (int i=0;i<gameObjs.size();i++) {		//go thru the arraylist to find the ship and get its missile count
			if (gameObjs.get(i) instanceof Ship) {
				Ship tempShip=(Ship)gameObjs.get(i);
				missileDisplay=tempShip.getMissileCount();
			}
		}			
		System.out.println("score: "+score+" missile count: "+missileDisplay+" elapsed time: "+tickCount);	//print the score, missile count, and tick count
	}
	
	public void printMap() {						//method to print the map
		for(int i=0;i<gameObjs.size();i++) {		//go through each object in the arraylist and call its toString method and print the string it returns
			System.out.println(gameObjs.get(i).toString());
		}
	}
	
	

}
