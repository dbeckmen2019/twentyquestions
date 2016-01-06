package binarytree;
import java.util.Iterator;

import linkedList.LinkedList;
import linkedList.Queue;


public class PreOrderIterator<E> implements Iterator<E>{
	private Queue<E> values;
	
	PreOrderIterator (BinaryTree<E> tree) {
		values = new LinkedList<E>();
		traversal(tree);
	}
	
	/**
	 * Traverses the Binary Tree, adds the root to the values Queue and calls the traversal on the left and right nodes if they are not null.
	 * @param tree BinaryTree to traverse
	 */
	private void traversal(BinaryTree<E> tree) {
		if (tree.isLeaf()) {
			values.offer(tree.value);
			return;
		}
		else {
			values.offer(tree.value);
			if (tree.left() != null) traversal(tree.left());
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
