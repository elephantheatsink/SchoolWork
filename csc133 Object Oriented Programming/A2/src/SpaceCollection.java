package com.mycompany.a2;

import java.util.Vector;

public class SpaceCollection implements ICollection{
	private Vector theCollection;
	public SpaceCollection() {
		theCollection = new Vector();
	}
	public void add(Object newObject) {
		theCollection.addElement(newObject);
	}
	public IIterator getIterator() {
		return new SpaceVectorIterator();
	}
	public void remove(int i) {
		theCollection.remove(i);
	}

	public Vector getGameObjects() {
		return theCollection;
	}
	public int getSize() {
		return theCollection.size();
	}
	public Object get(int i) {
		return theCollection.get(i);
	}
	private class SpaceVectorIterator implements IIterator {
		private int currElementIndex;
		public SpaceVectorIterator() {
			currElementIndex = -1;
		}
		public boolean hasNext() {
			if (theCollection.size ( ) <= 0) return false;
			if (currElementIndex == theCollection.size()-1 )
				return false;
			return true;
		}
		public Object getNext ( ) {
			currElementIndex ++ ;
			return(theCollection.elementAt(currElementIndex));
		}
		
		
		
		
	} //end private iterator class
} //end SpaceCollection class