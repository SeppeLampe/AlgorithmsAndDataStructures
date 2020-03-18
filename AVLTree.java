/* This is an implementation of an AVLTree based on the Tree class.
 * The balancing of this class has been partially built on the strategy used on the following webpage: https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * The AVLTree balances a Tree which makes searching the structure much more efficient.
 * The balancing is heavier than the balancing of a RedBlackTree but the balancing results in a Tree
 * which is as balanced as possible. This is ideal for static Trees which are not updated frequently
 * but searched a lot.
 * Author: Seppe Lampe
 */
public class AVLTree extends Tree{
	public class AVLTreeNode extends Tree.TreeNode{
		private AVLTreeNode parentNode;
		private int height;
		
		public AVLTreeNode(Comparable v) {
			super(v);
			height = 1;
			parentNode = null;
		}

		public AVLTreeNode(Comparable v, AVLTreeNode left, AVLTreeNode right, AVLTreeNode parent) {
			super(v, left, right);
			parentNode = parent;
			this.height = 1;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public AVLTreeNode getLeftNode() {
			return (AVLTreeNode) leftNode;
		}
		
		public void setLeftNode(AVLTreeNode n) {
			leftNode = n;
			if (n!= null) {
				n.setParentNode(this);
			}
		}
		
		public AVLTreeNode getRightNode() {
			return (AVLTreeNode) rightNode;
		}
		
		public void setRightNode(AVLTreeNode n) {
			rightNode = n;
			if (n!= null) {
				n.setParentNode(this);
			}
		}

		public AVLTreeNode getParentNode() {
			return parentNode;
		}

		public void setParentNode(AVLTreeNode parentNode) {
			this.parentNode = parentNode;
		}
		
		@Override
		public int compareTo(Object o) { // O(1)
			return value.compareTo(((AVLTreeNode) o).value);
		}

	}
	
	public AVLTree() {
	}
	
	public void insert(Comparable element) { // O(log(n))
		insertAtNode(element, (AVLTreeNode) root, null);
	}
	
	private void insertAtNode(Comparable element, AVLTreeNode current, AVLTreeNode parent) { 	// O(log(n))
		if (current == null) { 															// if the node we check is empty
			AVLTreeNode newNode = new AVLTreeNode(element);
			if (parent != null) { 														// the current node is empty, but we have a parent
				if (element.compareTo(parent.value) < 0) { 								// do we add it to the left?
					parent.setLeftNode(newNode);
				} 
				else {
					parent.setRightNode(newNode); 										// or do we add it to the right?
				}
				balance(parent);
			} 
			else { 																		// the current node is empty and it has no parent, we actually have an empty tree
				root = newNode;
			}
			count += 1;
		} 
		else if (element.compareTo(current.value) == 0) {
			System.out.println("Element is already in tree."); 							// if the element is already in the tree, what to do?
		} 
		else if (element.compareTo(current.value) < 0) { 								// if the element is smaller than current, go left
			insertAtNode(element, current.getLeftNode(), current);
		} 
		else 																			// if the element is bigger than current, go right
			insertAtNode(element, current.getRightNode(), current);
	}

	private void splayLeft(AVLTreeNode n) { // O(1)
		/*		  p							   p
				 /							  /
			 	n							 r
			   / \           -->            / \
		     l	  r					       n   rr
			  	 / \					  /	\
				rl  rr					l	 rl
		*/
		AVLTreeNode rightTree = n.getRightNode();	
		AVLTreeNode parent = n.getParentNode();
		n.setRightNode(rightTree.getLeftNode());				// The original LeftTree of the RightTree of n becomes the new RightTree of n	
		rightTree.setLeftNode(n);				// n becomes the LeftTree of r (its RightTree)
		if(n == super.root) {
			super.root = rightTree;
		}
		else {
			if(parent.getLeftNode() == n) {				// The RightTree now comes at the place where n used to be
				parent.setLeftNode(rightTree);
			}
			else {												// The RightTree now comes at the place where n used to be
				parent.setRightNode(rightTree);
			}
		}
		correctHeight(n);
		correctHeight(rightTree);
	}

	private void splayRight(AVLTreeNode n) { // Similar to splayLeft but mirrored
	/*		  p							   p
			 /							  /
		 	n							 l
		   / \           -->            / \
	      l	  r					       ll  n
	   	 / \							  /	\
		ll  lr							 lr	 r
	*/
		AVLTreeNode leftTree = n.getLeftNode();		
		AVLTreeNode parent = n.getParentNode();		
		n.setLeftNode(leftTree.getRightNode());	
		leftTree.setRightNode(n);							  							   				
		if(n == root) {								
			root = leftTree;						
		}											
		else {										
			if(parent.getRightNode() == n) {		
				parent.setRightNode(leftTree);		
			}										
			else {	
				parent.setLeftNode(leftTree);
			}
		}
		correctHeight(n);
		correctHeight(leftTree);
	}

	/* This method was based on the webpage mentioned in the beginning of the document.
	   However it has been written to function with pointers to the parentNode as I found this resulted
	   in more understandable/readable code. */
	private void balance(AVLTreeNode n) {
		AVLTreeNode parent = n.getParentNode();
		correctHeight(n);
		correctHeight(parent);
		int balance = getBalance(parent);
		if (balance > 1) {
			if (n == parent.getLeftNode()) {
				splayRight(parent);
			} 
			else {
				splayLeft(parent.getLeftNode());
				splayRight(parent);
			}
		} 
		else if (balance < -1) {
			if (n == parent.getRightNode()) {
				splayLeft(parent);
			} 
			else {
				splayRight(parent.getRightNode());
				splayLeft(parent);
			}
		}
		if(n != root && parent != root) {
			balance(parent);
		}
	}
	

// These are some helper functions for the balancing

	private int max(int one, int two) {
		if (one > two) {
			return one;
		}
		return two;
	}

	private int height(AVLTreeNode n) {
		if (n == null) {
			return 0;
		}
		return n.getHeight();
	}

	private void correctHeight(AVLTreeNode n) {
		if (n != null) {
			n.setHeight(max(height(n.getLeftNode()), height(n.getRightNode())) + 1);
		}
	}

	private int getBalance(AVLTreeNode n) {
		if (n == null) {
			return 0;
		}
		return height(n.getLeftNode()) - height(n.getRightNode());
	}
}
