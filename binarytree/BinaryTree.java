package binarytree;
import java.util.Iterator;


public class BinaryTree<E> implements Iterable<E>{
	protected E value;
	
	protected BinaryTree<E> left;
	protected BinaryTree<E> right;
	
	
	public BinaryTree(E val, BinaryTree<E> l, BinaryTree<E> r) {
		this.value = val;
		this.left = l;
		this.right = r;
		
	}

	public BinaryTree(E val) {
		this.value = val;
		this.left = null;
		this.right = null;
	}
	
	public BinaryTree() {
		this.value = null;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Returns whether the BinaryTree is a leaf.
	 * @return boolean is leaf?
	 */
	public boolean isLeaf() {
		if (left == null && right == null) return true;
		else return false;
	}
	
	/**
	 * Returns the number of BinaryTree nodes contained in the BinaryTree
	 * @return int number of BinaryTrees
	 */
	public int size() {
		if (isLeaf()) {
			return 1;
		}
		else if (left() == null) {
			return 1 + right().size();
		}
		else if (right() == null) {
			return 1 + left().size();
		}
		else return 1 + left().size() + right().size();
	}
	
	/**
	 * Returns the maximum path length to a descendent.
	 * @return int height
	 */
	public int height () {
		if (isLeaf()) {
			return 0;
		}
		else if (left == null) {
			return 1 + right().height();
		}
		else if (right == null) {
			return 1 + left.height();
		}
 		else {
			return 1 + (Math.max(left.height(), right.height()));
		}
	}
 	
	/**
	 * Returns the BinaryTree contained in the left pointer.
	 * @return BinaryTree<E> child tree
	 */
	public BinaryTree<E> left() {
		return this.left;
	}
	
	/**
	 * Returns the true if the differences of heights of subtrees at every node is no greater than one.
	 * @return boolean 
	 */
	public boolean isBalanced () {
		if (isLeaf()) {
			return true;
		}
		else if (left() == null && right.isLeaf()) {
			return true;
		}
		else if (right() == null && left.isLeaf()) {
			return true;
		}
		else if (Math.abs(left().height() - right().height()) <= 1) {
			return true;
		}
		else return false;
	}

	/**
	 * Returns true if adding a node to a tree would increase its height
	 * @return boolean is full
	 */
	public boolean isFull () {
		if (isLeaf()) return true;
		else if (left() == null || right() == null) {
			return false;
		}
		else if (left().height() == right().height()) {
			return left().isFull() && right().isFull();
		}
		else return false;
	}
	
	/**
	 *  Returns true if the difference in heights between the branches are <1 and if any and all holes appear in the right branches.
	 * @return boolean 
	 * */
	public boolean isComplete () {
		if (isLeaf()) {
			return true;
		}
		else if ((left() == null && right.isLeaf()) || (right() == null && left.isLeaf())) {
			return true;
		}
		else if (left().isComplete() && right().isFull()) {
			return true;
		}
		else if (left().isFull() && right.isComplete()) {
			return true;
		}
		else return false;
	}
	
	/**
	 * Returns the BinaryTree contained in the right pointer.
	 * @return BinaryTree<E> child tree
	 */
	public BinaryTree<E> right() {
		return this.right;
	}

	/**
	 * Sets the value of the left branch to the parameter
	 * @param left value of left node
	 */
	public void setLeft(BinaryTree<E> left) {
		this.left = left;
	}
	
	/**
	 * Sets the value of the right branch to the parameter.
	 * @param right value of right node
	 */
	public void setRight(BinaryTree<E> right) {
		this.right = right;
	}
	
	/**
	 * 
	 * @param object Sets the value of the Binary Tree
	 */
	public void setValue(E object) {
		value = object;
	}
	
	/**
	 * Gets the value stored in the BinaryTree
	 * @return value of the BinaryTree
	 */
	public E getValue() {
		return this.value;
	}
	
	public Iterator<E> iterator() {
		return inorderIterator();
	}
	public Iterator<E> preorderIterator() {
		return new PreOrderIterator<E>(this);
	}
	public Iterator<E> inorderIterator() {
		return new InOrderIterator<E>(this);
	}
	public Iterator<E> postorderIterator() {
		return new PostOrderIterator<E>(this);
	}

	/**
	 * Returns a string representation of the binary tree.
	 * @return String string represenation
	 */
	public String toString () {
		if (isLeaf()) {
			return value + "()";
		}
		else {
			String str = " " + value;
			if (left() != null && right() != null) {
				str += " (" + left().toString() + ", " + right().toString() +")";
			}
			else{
				if (left() != null) {
					str += "(" + left().toString() + ", ())";
				}
				else if (right() != null) {
					str += "((), " + right.toString() + ")";
				}
			}
			
			return str;
		}
	}
}
