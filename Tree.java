/* This class is an implementation of the Binary Search Tree (BST). Each element of this data structure is
 * a TreeNode, a class specifically made for this class. A TreeNode has two pointers and a value.
 * One pointer (called leftNode) points to an object with a smaller value than itself while its other
 * pointer (called rightNode) points to an object with a higher value than itself. Only one pointer is aimed
 * at each element. The Tree class has one special TreeNode, called root. This TreeNode does not have a pointer
 * to itself and is used to initiate traversing the Tree.
 * Author: Seppe Lampe
 */
import java.util.Comparator;

public class Tree {
	
	public class TreeNode implements Comparable{
		protected Comparable value;
		protected TreeNode leftNode;
		protected TreeNode rightNode;				

		public TreeNode(Comparable v) {	
			value = v;
			leftNode = null;
			rightNode = null;					
		}

		public TreeNode(Comparable v, TreeNode left, TreeNode right) {	
			value = v;					
			leftNode = left;
			rightNode = right;					
		}

		public TreeNode getLeftNode() { // O(1)
			return leftNode;
		}

		public TreeNode getRightNode() { // O(1)
			return rightNode;
		}

		public void setLeftNode(TreeNode leftNode) {
			this.leftNode = leftNode;
		}

		public void setRightNode(TreeNode rightNode) {
			this.rightNode = rightNode;
		}

		public Comparable getValue() { // O(1)
			return value;
		}
		
		@Override
		public int compareTo(Object o) { // O(1)
			return value.compareTo(((TreeNode) o).value);
		}

		public String toString() {
			return value.toString();
		}
	}

	// start of the actual tree class
	// the root of our tree
	protected TreeNode root;
	protected int count;

	public Tree() {
		root = null;
	}

	// Searches for a Node based on its value
	protected TreeNode findNode(Comparable element, TreeNode current) { // O(log(n))
		if (current == null)
			return new TreeNode(null);
		else if (element.compareTo(current.value) == 0)
			return current;
		else if (element.compareTo(current.value) < 0)
			return findNode(element, current.getLeftNode());
		else
			return findNode(element, current.getRightNode());
	}

	public Comparable find(Comparable element) // O(log(n))
	{
		return findNode(element, root).getValue();
	}

	// Traverses the Tree in a breadth first manner
	public void traverse(TreeAction action) { // O(n)
		Queue t = new Queue();
		t.push(root);
		while (!t.isEmpty()) {
			TreeNode n = (TreeNode) t.pop();
			action.run(n);

			if (n.getLeftNode() != null)
				t.push(n.getLeftNode());
			if (n.getRightNode() != null)
				t.push(n.getRightNode());
		}
	}

	// Traverses the Tree and performs an action on/at each Node
	public void traverseNode(TreeNode n, TreeAction action) // O(n)
	{
		if (n != null) {
			if (n.getLeftNode() != null)
				traverseNode(n.getLeftNode(), action);
			action.run(n);
			if (n.getRightNode() != null)
				traverseNode(n.getRightNode(), action);
		}
	}

	public void traverseInOrder(TreeAction action) { // O(n)
		traverseNode(root, action);
	}

	// Returns a String representation of the Tree
	public String toString() { // O(n)
		final String[] result = new String[] { "" }; // Cannot use a normal String because adding to string creates a
														// new string (immutability)
		traverseInOrder(new TreeAction() { // A change in the string results in a change in reference
			public void run(TreeNode n) { // Therefore we can use a string array with one element, and we change
												// this element...
				result[0] += n.getValue().toString() + " ";
			}
		});
		return result[0];
	}

	// Prints a representation of the Tree to the console
	public void print() { // O(n)
		traverseInOrder(new TreeAction() {
			public void run(TreeNode n) {
				System.out.println(n.getValue());
			}
		});
	}

	// Adds an element to the Tree
	public void insert(Comparable element) { // O(log(n))
		insertAtNode(element, root, null);
	}

	// we traverse the tree.
	// Current holds the pointer to the TreeNode we are currently checking
	// Parent holds the pointer to the parent of the current TreeNode
	private void insertAtNode(Comparable element, TreeNode current, TreeNode parent) { // O(log(n))
		if (current == null) { // if the node we check is empty
			TreeNode newNode = new TreeNode(element);
			if (parent != null) { // the current node is empty, but we have a parent
				if (element.compareTo(parent.value) < 0) { // do we add it to the left?
					parent.setLeftNode(newNode);
				} else {
					parent.setRightNode(newNode); // or do we add it to the right?
				}
				balance(parent);
			} else { // the current node is empty and it has no parent, we actually have an empty
						// tree
				root = newNode;
			}
			count += 1;
		} 
		else if (element.compareTo(current.value) == 0) {
			System.out.println("Element is already in tree."); // if the element is already in the tree, what to do?
		} 
		else if (element.compareTo(current.value) < 0) { // if the element is smaller than current, go left
			insertAtNode(element, current.getLeftNode(), current);
		} 
		else { // if the element is bigger than current, go right
			insertAtNode(element, current.getRightNode(), current);
		}
	}
	
	// Finds the depth of the Tree
	// The leafs (null nodes) are not counted towards the depth
	protected int depth(TreeNode top) { // O(n)			
		int count = 0;
		Queue myQueue = new Queue();
		myQueue.push(top);
		while (!myQueue.isEmpty()) {
			for (int i = myQueue.size(); i > 0; i--) {
				if (((TreeNode) myQueue.top()).getLeftNode() != null) {
					myQueue.push(((TreeNode) myQueue.top()).getLeftNode());
				}
				if (((TreeNode) myQueue.top()).getRightNode() != null) {
					myQueue.push(((TreeNode) myQueue.top()).getRightNode());
				}
				myQueue.pop();
			}
			count++;
		}
		return count;
	}

	public int findDepth() { // O(n)
		return depth(root);
	}

	// Finds the maximum value in a Tree
	public int max(TreeNode top) { // O(log(n))
		while (top.getRightNode() != null) {
			top = top.getRightNode();
		}
		return (int) top.getValue();
	}

	public int findMax() { // O(log(n))
		return max(root);
	}

	public int size() {
		return count;
	}
	
	// Prints a Tree level by level
	// The leafs (null nodes) are not counted towards the depth
	public void printTree() { // O(n)	
		Queue myQueue = new Queue();
		myQueue.push(root);
		while (!myQueue.isEmpty()) {
			System.out.println(myQueue.toString());
			for (int i = myQueue.size(); i > 0; i--) {
				if (((TreeNode) myQueue.top()).getLeftNode() != null) {
					myQueue.push(((TreeNode) myQueue.top()).getLeftNode());
				}
				if (((TreeNode) myQueue.top()).getRightNode() != null) {
					myQueue.push(((TreeNode) myQueue.top()).getRightNode());
				}
				myQueue.pop();
			}
		}
	}
	
	// Balances the tree, in a normal BST no balancing is performed
	private void balance(TreeNode n) {	
	}
}
