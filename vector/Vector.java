package vector;
/*
 * Jack Proudfoot
 * October 24, 2015
 * 
 * A vector class used for storing data. Uses the generic type to define what data types
 * are stored in the Vector. Supports adding, removing, getting, setting, and clearing
 * of the vector along with getting the index of data or data types.
 */

import java.util.Iterator;


public class Vector<E> implements Iterable<E>{
	
	private Object [] data;
	
	private int size;
	
	public Vector () {
		data = new Object [10];
		
		this.size = 0;
	}
	
	public Vector (int size) {
		data = new Object [size];
		
		this.size = 0;
	}
	
	public Vector (Vector<E> v) {
		data = new Object[v.getSize()];
		
		for (int x = 0; x < data.length; x++) {
			data[x] = v.get(x);
		}
		
		size = data.length;
	}
	
	/**
	 * Adds newData to the end of the vector. If the vector is full, doubles the size.
	 * @param newData The object to be added to the vector
	 */
	public void add (E newData) {
		add(size, newData);		
	}
	
	/**
	 * Adds newData to the position index. Shifts all data contained after index one space. If the vector is full, doubles the size.
	 * @param newData The object to be added to the vector
	 * @param index The position where object is to be added
	 */
	public void add (int index, E newData) {
		//IndexOutOfBounds
		if (index > size) {
			throw new IndexOutOfBoundsException("You are trying to add an element at position " + index + ", however " + (size-1) +" is the last index in the vector.");
		}
		
		if (size+1 >= data.length) {
			increaseCapacity();
			add(index, newData);
		}
		else {
			size++;
			
			for (int x = size-1; x > index; x--) {
				if (data[x-1] != null) {
					data[x] = data[x-1];
				}
				else {
					data[x] = null;
				}
			}
			
			data[index] = (Object) newData;
			
		}
		
	}
	
	/**
	 * Removes the object contained at index
	 * @param int The index to remove an object
	 * @return E The object contained in space index
	 */
	@SuppressWarnings("unchecked")
	public E remove (int index) {
		//IndexOutOfBounds, Null Pointer
		
		if (index < size) {
			E removedObject = (E) data[index];
			
			for (int i = index; i < size; i++) {
				data[i] = data[i+1];
			}
			
			size--;
			
			return removedObject;
		}
		
		return null;
		
	}
	
	/**
	 * Creates a new array double the size of the old one and transfers all of the old objects into the new array.
	 */
	private void increaseCapacity() {
		//NullPointer
		
		Object[] doubleData = new Object[data.length * 2];
		
		for (int x = 0; x < size; x++) {
			if (data[x] != null) doubleData[x] = data[x];
		}
		
		data = doubleData;
	}
	
	
	/**
	 * Returns the how many objects that are stored in the array.
	 * @return int
	 */
	public int getSize () {
		return this.size;
	}

	/**
	 * Return item at specified index
	 * @param index
	 * @return E
	 */
	 @SuppressWarnings("unchecked")
	public E get(int index) {
	 //IndexOutOfBounds, NullPointer
		if (index < size) {
			if (data[index] != null) return (E) data[index];
		}
		else {
			throw new IndexOutOfBoundsException("There isn't anything there! The size of the Vector is " + this.size + " and you want to get the object at " + index + "!");
		}
		
		return null;
	 }

	/**
	 * Remove first instance of specified object
	 * @return boolean
	 * @param E obj The instance of the object to be removed.
	 */
	 public boolean remove(E obj) {
		 for (int x = 0; x < size; x++) {
			if (obj == null && data[x] == null) {
				remove(x);
				return true;
			}
			else if (obj != null && data[x].equals(obj)) {
				if (remove(x) != null) return true;
			}
		}
		
		return false;
	 }	
	
	/**
	 * Place object at specified location
	 * @param index The index at which to add the data
	 * @param obj The object which to add to the vector
	 * @return E object previously contained at index
	 */
	 @SuppressWarnings("unchecked")
	public E set(int index, E obj) {
		E previousValue = null;
		
		if (index < size) {
			if (data[index] != null) previousValue = (E) data[index];
			data[index] = obj;
		}
		else {
			throw new IndexOutOfBoundsException("You want me to set the value at index " + index + " to " + obj + " but there are only " + size + " items in the Vector.");
		}
		
		
		 
		 return previousValue;
	 }
	
	/**
	 * Return the number of items in the vector
	 * @return int Number of items in the array.
	 */
	 public int size() {
		 
		 return this.size;
	 }
	
	
	/**
	 * Remove all items from the vector
	 *
	 */
	 public void clear() {
		 for (int x = 0; x < size; x++) {
			 data[x] = null;
		 }
		 
		 size = 0;
	 }


	/**
	 * Return whether or not the vector is empty
	 * @return boolean Is the vector empty
	 */
	 
	 public boolean isEmpty() {
		 if (size == 0) return true;
		 return false;
	 }

	/**
	 * Return whether or not the vector contains specified object
	 * @param obj The object to check for
	 * @return boolean Does the vector contain the object
	 */
	 public boolean contains(E obj) {
		 for (int x = 0; x < size; x++) {
			 if (data[x] == obj) {
				 return true;
			 }
		 }
		 
		 return false;
	 }
	
	/**
	* Return index of the first instance of specified object
	* @return E The object to test for
	* @param int The index that contains the first instance of the object.
	*/
	public int indexOf(E obj) {
		for (int x = 0; x < size; x++) {
			 if (data[x].equals(obj)) {
				 return x;
			 }
		 }
		 
		 return -1;
	}
	
	/** 
	 * Creates and return an iterator of the vector
	 * @return Iterator<E> A new iterator of the vector
	 */
	 public Iterator<E> iterator() {		 
		 Iterator<E> iterator = new VectorIterator<E>(this);
		 
		 return iterator;
	 }
	 
	/**
	 * Returns the String representation of this Vector.
	 * @return String string representation of this vector
	 */
	public String toString () {
		String vectorString = "[";
		
		for (int x = 0; x < size; x++) {
			vectorString += data[x] + ", ";
		}
		
		vectorString = vectorString.substring(0, vectorString.length()-2) + "] ";		
		return vectorString;
	}
	
	/**
	 * Compares the two vectors based on the values they contain
	 * @param vector The vector to compare to
	 * @return Are the vectors equal
	 */
	public boolean equals (Vector<E> vector) {
		for (int x = 0; x < this.size; x++) {
			if (this.get(x) == null && vector.get(x) == null) {
				continue;
			}
			else if (this.get(x).equals(vector.get(x))) {
				continue;
			}
			else return false;
		}
		return true;
	}
}
