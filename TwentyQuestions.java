import hashtable.HashTable;

import java.util.Scanner;

import vector.Vector;
import binarytree.BinaryTree;

/*
 * Red-Black Trees for binary tree balancing
 */

public class TwentyQuestions {
	private BinaryTree<String> tree;
	private Scanner input;
	
	public static void main (String [] args) {
		new TwentyQuestions();
	}
	
	public TwentyQuestions () {
		init();
		cycle(null, tree);
	}
	
	/**
	 * Initializes the tree using stored data.
	 */
	public void init () {
		input = new Scanner(System.in);
		
		tree = parseSaveData(FileManager.readFile());
		
	}
	
	/**
	 * A single cycle of the game
	 */
	private void cycle(BinaryTree<String> parent, BinaryTree<String> child) {
		if (child.isLeaf()) {
			System.out.print("Is it a(n) " + child.getValue() + "\n:");
			
			if (answerInput()) {
				System.out.println("Haha! I win!");
				endgame();
			}
			else {
				System.out.println("Alas, you've bested me!");
				updateTree(parent, child);
			}
		}
		else {
			System.out.print(child.getValue() + "\n:");
				
			if (answerInput()) {
				cycle(child, child.right());
			}
			else {
				cycle(child, child.left());	
			}
		}
	}

	private void updateTree(BinaryTree<String> parent, BinaryTree<String> child) {
		System.out.print("What was your object?\n:");
		String object = input.nextLine();
		System.out.print("Of course! How could I have been so stupid? Will you please enter a new question about a(n) " +  object + " so I do not make that mistake again?\n:");
		String question = input.nextLine();
		
		if (tree.isLeaf()) {
			BinaryTree<String> newObject = new BinaryTree<String>(object);
			BinaryTree<String> newQuestion = new BinaryTree<String>(question, tree, newObject);
			
			tree = newQuestion;
		}
		else if (parent.right().toString().equals(child.toString())){
			BinaryTree<String> newObject = new BinaryTree<String>(object);
			BinaryTree<String> newQuestion = new BinaryTree<String>(question, child, newObject);
			
			parent.setRight(newQuestion);
		}
		else if (parent.left().toString().equals(child.toString())){
			BinaryTree<String> newObject = new BinaryTree<String>(object);
			BinaryTree<String> newQuestion = new BinaryTree<String>(question, child, newObject);
			
			parent.setLeft(newQuestion);
		}
		
		endgame();
	}
	
	private void endgame () {
		saveTree();
		
		System.out.print("Would you like to play again?\n:");
		if(answerInput()) {
			cycle(null, tree);
		}
		else {
			System.out.println("I didn't want you to play anyway!");
			System.exit(0);
		}
	}
	
	/**
	 * Returns true or false depending on user input.
	 * @return boolean true or false
	 */
	private boolean answerInput () {
		String userInput = input.nextLine();
		return (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("t") || userInput.equalsIgnoreCase("true"));
	}

	private BinaryTree<String> parseSaveData(String stringData) {
		if (stringData.equals("")) {
			return new BinaryTree<String>("Apple");
		}
		
		String[] data = stringData.split("/");
		HashTable<Integer, NodePair> nodes = new HashTable<Integer, NodePair>(data.length);
		
		for (String s : data) {
			String[] nodeData = s.split(",");
			nodes.put(Integer.parseInt(nodeData[0]), new NodePair(nodeData[1], (nodeData[2].equalsIgnoreCase("null")) ? -1 : Integer.parseInt(nodeData[2]), (nodeData[3].equalsIgnoreCase("null")) ? -1 : Integer.parseInt(nodeData[3])));
		}
		
		
		return reconstructSavedTree(nodes, nodes.get(0));
	}
	
	private BinaryTree<String> reconstructSavedTree (HashTable<Integer, NodePair> nodes, NodePair index) {
		NodePair rootNode = index;
		BinaryTree<String> tree = rootNode.value;
		if (rootNode.leftid != -1) tree.setLeft(reconstructSavedTree(nodes, nodes.get(rootNode.leftid)));
		if (rootNode.rightid != -1) tree.setRight(reconstructSavedTree(nodes, nodes.get(rootNode.rightid)));
		
		return tree;
	}
	
	private void saveTree () {
		SaveTree save = new SaveTree(tree);
		FileManager.writeFile(save.saveTree());
	}
	
	private class NodePair {
		BinaryTree<String> value;
		int leftid, rightid;
		
		public NodePair(String value, int leftid, int rightid) {
			this.value = new BinaryTree<String>(value);
			this.leftid = leftid;
			this.rightid = rightid;
		}
	}
	
	private class SaveTree {
		BinaryTree<String> tree;
		
		HashTable<BinaryTree<String>, Integer> nodes;
		
		int index;
		String gameData = "";
		
		public SaveTree(BinaryTree<String> tree) {
			this.tree = tree;
			this.nodes = new HashTable<BinaryTree<String>, Integer>(tree.size());
			this.index = 0;
		}
		
		private String saveTree() {
			traversal(tree);
			collectData(tree);
			
			return gameData;
		}
		
		private void collectData (BinaryTree<String> tree) {
			gameData += nodes.get(tree) + ",";
			gameData += tree.getValue() + ",";
			gameData += (tree.left() == null) ? "null," : nodes.get(tree.left()) + ",";
			gameData += (tree.right() == null) ? "null" : nodes.get(tree.right());
			gameData += "/";
			
			if (tree.left() != null) collectData(tree.left());
			if (tree.right() != null ) collectData(tree.right());
		}
		
		/**
		 * Traverses the Binary Tree, adds the root to the nodes Hashtable and assigns it an id and calls the traversal on the left and right nodes if they are not null.
		 * @param tree BinaryTree to traverse
		 */
		private void traversal(BinaryTree<String> tree) {
			nodes.put(tree, index);
			index++;
			if (!tree.isLeaf()) {
				if (tree.left() != null) traversal(tree.left());
				if (tree.right() != null) traversal(tree.right());
			}
		}
	}
}
