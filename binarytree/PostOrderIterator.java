package binarytree;
import java.util.Iterator;

import linkedList.LinkedList;
import linkedList.Queue;


public class PostOrderIterator<E> implements Iterator<E>{
	private Queue<E> values;
	
	PostOrderIterator (BinaryTree<E> tree) {
		values = new LinkedList<E>();
		traversal(tree);
	}
	
	/**
	 * Traverses the Binary Tree, calls the traversal on the left and right nodes if they are not null and then adds the value to the Queue.
	 * @param tree BinaryTree to traverse
	 */
	private void traversal(BinaryTree<E> tree) {
		if (tree.isLeaf()) {
			values.offer(tree.value);
			return;
		}
		else {
			if (tree.left() != null) traversal(tree.left());
			if (tree.right() != null) traversal(tree.right());
			values.offer(tree.value);
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
