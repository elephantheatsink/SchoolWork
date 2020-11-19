package com.mycompany.a2;

public interface ISteerable { //steerable interface, only used by ship
	void turnRight();			//steerable methods that are implemented by ship
	void turnLeft();
	void increaseSpeed();
	void decreaseSpeed();
}
