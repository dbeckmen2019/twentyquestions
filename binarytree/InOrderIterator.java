package binarytree;
import java.util.Iterator;

import linkedList.LinkedList;
import linkedList.Queue;


public class InOrderIterator<E> implements Iterator<E>{
	private Queue<E> values;
	
	InOrderIterator (BinaryTree<E> tree) {
		values = new LinkedList<E>();
		traversal(tree);
	}
	
	/**
	 * Traverses the Binary Tree, calls traversal on left if it is not null, adds the value to the Queue, and calls traversal on the right if it is not null.
	 * @param tree BinaryTree to traverse
	 */
	private void traversal(BinaryTree<E> tree) {
		if (tree.isLeaf()) {
			values.offer(tree.value);
			return;
		}
		else {
			if (tree.left() != null) traversal(tree.left());
			values.offer(tree.value);
			if (tree.right() != null) traversal(tree.right());
		}
	}

	@Override
	public boolean hasNext() {
		if (values.isEmpty()) return false;
		return true;
	}

	@Override
	public E next() {
		if (hasNext()) return values.poll();
		return null;
	}

}
