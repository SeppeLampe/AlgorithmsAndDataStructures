/* This is an implementation of a RedBlackTree based on the Tree class.
 * The balancing of this class has been partially built on the strategy used on the following webpage: https://www.geeksforgeeks.org/red-black-tree-set-2-insert/
 * The RedBlackTree balances a Tree which makes searching the structure much more efficient.
 * However the structuring is not entirely perfect (see AVLTree) but this fairly well structured Tree is
 * easy to obtain and especially maintain. It is ideal for dynamic Trees which are updated frequently.
 * Author: Seppe Lampe
 */
public class RedBlackTree extends Tree{
	
	public class RedBlackTreeNode extends Tree.TreeNode{
		private RedBlackTreeNode parentNode;
		private boolean colour;							// This is the colour of the node. red = false, black = true.
		
		public RedBlackTreeNode(Comparable v) {
			super(v);
			colour = false;
			parentNode = null;
		}

		public RedBlackTreeNode(Comparable v, RedBlackTreeNode left, RedBlackTreeNode right, RedBlackTreeNode parent) {
			super(v, left, right);
			parentNode = parent;
			colour = false;
		}

		public RedBlackTreeNode getLeftNode() {
			return (RedBlackTreeNode) leftNode;
		}
		
		public void setLeftNode(RedBlackTreeNode n) {
			leftNode = n;
			if (n!= null) {
				n.setParentNode(this);
			}
		}
		
		public RedBlackTreeNode getRightNode() {
			return (RedBlackTreeNode) rightNode;
		}
		
		public void setRightNode(RedBlackTreeNode n) {
			rightNode = n;
			if (n!= null) {
				n.setParentNode(this);
			}
		}

		public RedBlackTreeNode getParentNode() {
			return parentNode;
		}

		public void setParentNode(RedBlackTreeNode parentNode) {
			this.parentNode = parentNode;
		}
		
		public boolean isBlack() {
			return colour;
		}

		public void setColourBlack() {
			this.colour = true;
		}
		
		public void setColourRed() {
			this.colour = false;
		}

		@Override
		public int compareTo(Object o) { // O(1)
			return value.compareTo(((RedBlackTreeNode) o).value);
		}

	}
	
	public void insert(Comparable element) { // O(log(n))
		insertAtNode(element, (RedBlackTreeNode) root, null);
	}
	
	private void insertAtNode(Comparable element, RedBlackTreeNode current, RedBlackTreeNode parent) { 	// O(log(n))
		if (current == null) { 															// if the node we check is empty
			RedBlackTreeNode newNode = new RedBlackTreeNode(element);
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
		} else if (element.compareTo(current.value) == 0) {
			System.out.println("Element is already in tree."); 							// if the element is already in the tree, what to do?
		} else if (element.compareTo(current.value) < 0) { 								// if the element is smaller than current, go left
			insertAtNode(element, current.getLeftNode(), current);
		} else 																			// if the element is bigger than current, go right
			insertAtNode(element, current.getRightNode(), current);
	}
	
	private void splayLeft(RedBlackTreeNode n) { // O(1)
		/*		  p							   p
				 /							  /
			 	n							 r
			   / \           -->            / \
		     l	  r					       n   rr
			  	 / \					  /	\
				rl  rr					l	 rl
		*/
		RedBlackTreeNode rightTree = n.getRightNode();	
		RedBlackTreeNode parent = n.getParentNode();
		// The original leftTree of the rightTree of n becomes the new rightTree of n	
		n.setRightNode(rightTree.getLeftNode());
		// n becomes the leftTree of r (its rightTree)	
		rightTree.setLeftNode(n);		
		if(n == super.root) {
			super.root = rightTree;
		}
		// The rightTree now comes at the place where n used to be
		else {
			if(parent.getLeftNode() == n) {	
				parent.setLeftNode(rightTree);
			}
			else {									
				parent.setRightNode(rightTree);
			}
		}
	}

	private void splayRight(RedBlackTreeNode n) { // Similar to splayLeft but mirrored
	/*		  p							   p
			 /							  /
		 	n							 l
		   / \           -->            / \
	      l	  r					       ll  n
	   	 / \							  /	\
		ll  lr							 lr	 r
	*/
		RedBlackTreeNode leftTree = n.getLeftNode();		
		RedBlackTreeNode parent = n.getParentNode();		
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
	}
	
	
	/* This part was based on the webpage mentioned in the beginning of the document.
	   However it has been written to function with pointers to the parentNode as I found this resulted
	   in more understandable/readable code. */
	private void balance(RedBlackTreeNode n) {
		if(n==root) {
			n.setColourBlack();
		}
		else if(!n.getParentNode().isBlack()) {			// Father is red
			if(n.getParentNode().getParentNode().getRightNode() == null || n.getParentNode().getParentNode().getLeftNode() == null) {		// Uncle is a leaf
				blackUncleBalance(n);
			}
			else if(!n.getParentNode().getParentNode().getRightNode().isBlack() && !n.getParentNode().getParentNode().getLeftNode().isBlack()) {	// Uncle is red as well
				redUncleBalance(n);
			}
			else {										// Uncle is black
				blackUncleBalance(n);	
			}
		}
	}
	
	private void redUncleBalance(RedBlackTreeNode n) {
		RedBlackTreeNode parent = n.getParentNode();
		RedBlackTreeNode grandparent = parent.getParentNode();
		if(grandparent.getLeftNode() == parent) {					// if parent is leftNode then uncle must be the RightNode of n's grandparent
			grandparent.getRightNode().setColourBlack();			// Colour the uncle of n black
		}
		else {
			grandparent.getLeftNode().setColourBlack();			// Colour the uncle of n black
		}
		parent.setColourBlack();						// Colour the father of n black
		grandparent.setColourRed();		// n must have a grandparent since his parent was red, we color the grandparent red
		balance(grandparent);					// Balance the grandparent of n
	}	
	
	private void blackUncleBalance(RedBlackTreeNode n) {
		RedBlackTreeNode parent = n.getParentNode();
		RedBlackTreeNode grandparent = parent.getParentNode();
		if(grandparent.getLeftNode() == parent && parent.getLeftNode() == n) {
			splayRight(grandparent);
			parent.setColourBlack();
		}
		if(grandparent.getLeftNode() == parent && parent.getRightNode() == n) {
			splayLeft(parent);
			splayRight(grandparent);
			n.setColourBlack();
		}
		if(grandparent.getRightNode() == parent && parent.getLeftNode() == n) {
			splayRight(parent);
			splayLeft(grandparent);
			n.setColourBlack();
		}
		if(grandparent.getRightNode() == parent && parent.getRightNode() == n) {
			splayLeft(grandparent);
			parent.setColourBlack();
		}
		grandparent.setColourRed();	
	}
	
}
