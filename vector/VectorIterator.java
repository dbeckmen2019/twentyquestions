package vector;
/*
 * Jack Proudfoot
 * October 24, 2015
 * 
 * A VectorIterator class used for iterating over the vector class. Implements the Iterator
 * class and supports checking that the vector contains data in the next index and returning
 * that data.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class VectorIterator<E> implements Iterator<E>{
	Vector<E> vector;
	
	private int index = 0;
	
	public VectorIterator (Vector<E> v) {
		vector = v;
	}
	
	/**
	 * Returns true if there is an element contained in the next position, returns false otherwise.
	 * @return boolean Is there a next element.
	 */
	public boolean hasNext() {
		if (index < vector.getSize()) {
			return true;
		}
		return false;
	}

	
	/**
	 * Returns the next element contained in the vector.
	 * @return E The element contained in the next index.
	 */
	public E next() {
		if (hasNext()) {
			E element = vector.get(index);
			index++;
			return element;
		}
		else {
			throw new NoSuchElementException("You are trying to iterate over the next item in the vector at index " + index + ", however there is not an element there.");
		}
	}
}