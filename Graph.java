
/* This class is a representation of a graph structure. It stores Nodes in a Tree structure.
 * Each Node contains a RedBlackTree with its edges. An Edge is defined as a new class.
 * An Edge has two parameters: toNode, the Node to which it connects the original Node and weight,
 * an optional parameter for defining the weight of the edge.
 * The fact that edges are stored in an RedBlackTree is because they will be searched a lot, so an ordinary binary Tree is not 
 * efficient enough. Yet distances between two points can change (new roads, roadworks, new train schedule, etc.).
 * That's why a RedBlackTree was chosen above an AVLTree.
 * Author: Seppe Lampe 
 */
public class Graph {
	public class Node implements Comparable {
		private Comparable info;
		private RedBlackTree edges;
		private double visited;

		public Node(Comparable label) {
			info = label;
			visited = 0;
			edges = new RedBlackTree();
		}

		// Adds an edge to the Node
		public void addEdge(Edge e) {
			edges.insert(e);
		}

		// Compare two Nodes base don their label
		public int compareTo(Object o) {
			// two nodes are equal if they have the same label.
			Node n = (Node) o;
			return n.info.compareTo(info);
		}

		// Return the label of the Node
		public Comparable getLabel() {
			return info;
		}
		
		// Return a String representation of the label of the Node
		public String toString() {
			return getLabel().toString();
		}

	}

	private class Edge implements Comparable {
		private Node toNode;
		private double weight;
		
		public Edge(Node to) {
			toNode = to;
			weight = 0;
		}
		
		public Edge(Node to, double w) {
			toNode = to;
			weight = w;
		}

		// Compare two Edges, they are equal if they point to the same Node
		public int compareTo(Object o) {
			//Two edges are equal if they point to the same node. 
			//This assumes that the edges are starting from the same node !!!
			Edge n = (Edge) o;
			return n.toNode.compareTo(toNode);
		}
		
		// Return a String representation of the Edge
		public String toString() {
			if (weight != 0) {
				return String.format("%s weight %i", toNode.getLabel().toString(), weight);
			}
			return toNode.getLabel().toString();
		}
	}
	
	public class Pair implements Comparable{
		public Object element1;
		public Object element2;
	
		public Pair(Object el1 , Object el2) {
			this.element1 = el1;
			this.element2 = el2;
		}
		
		public int compareTo(Object o) {
		Pair p2 = (Pair)o;
		return ((Comparable)element1).compareTo(p2.element1);
		}
	}

	private RedBlackTree nodes;					/* The choice has been made to store the Nodes in a RedBlackTree since
												* new Nodes get added when an area is expanded. Searching in this graph still
												* is much better than in an ordinary Tree while adding is not as heavy as in
												AVLTrees. */

	public Graph() {
		nodes = new RedBlackTree();
	}

	// Add a Node to the Graph (Tree)
	public void addNode(Comparable label) {
		nodes.insert(new Node(label));
	}

	// Search for a Node in the Graph
	private Node findNode(Comparable nodeLabel) {
		return (Node) nodes.find(new Node(nodeLabel));
	}

	// Add a unidirectional Edge between two Nodes in the Graph, with the weight
	public void addEdge(Comparable nodeLabel1, Comparable nodeLabel2, double weight) {
		Node n1 = findNode(nodeLabel1);
		Node n2 = findNode(nodeLabel2);
		n1.addEdge(new Edge(n2, weight));
	}
	
	// Add a unidirectional Edge between two Nodes in the Graph, without weight
	public void addEdge(Comparable nodeLabel1, Comparable nodeLabel2) {
		Node n1 = findNode(nodeLabel1);
		Node n2 = findNode(nodeLabel2);
		n1.addEdge(new Edge(n2));
	}
	
	// Add a bidirectional Edge between two Nodes in the Graph, with the weight
	public void addUndirectedEdge(Comparable nodeLabel1, Comparable nodeLabel2, double weight) {
		Node n1 = findNode(nodeLabel1);
		Node n2 = findNode(nodeLabel2);
		n1.addEdge(new Edge(n2, weight));
		n2.addEdge(new Edge(n1, weight));
	}
	
	// Add a bidirectional Edge between two Nodes in the Graph, without weight
	public void addUndirectedEdge(Comparable nodeLabel1, Comparable nodeLabel2) {
		Node n1 = findNode(nodeLabel1);
		Node n2 = findNode(nodeLabel2);
		n1.addEdge(new Edge(n2));
		n2.addEdge(new Edge(n1));
	}
	
	// Print a representation of the Graph
	public void print() {
		nodes.traverseInOrder(new TreeAction() {						
			public void run(Tree.TreeNode n) {
				System.out.println(String.format("%s: %s", n.getValue().toString(), ((Node)n.getValue()).edges.toString()));
			}
		});
	}
	
	// Find the shortest distance between two Nodes
	public double findDistance(Comparable nodeLabel1, Comparable nodeLabel2) {
		Vector path = findPath(nodeLabel1, nodeLabel2, true);
		if(path.isEmpty()) {
			System.out.println(String.format("No path was found from %s to %s", nodeLabel1, nodeLabel2));
			return -1;
		}
		return ((Node) path.getLast()).visited;
	}
	
	// Return the distance of a given path (Vector)
	//If you want to print out a path and it's distance then it is more efficient to save the path in a variable and then use this method
	public double findDistance(Vector path) {
		return ((Node) path.getLast()).visited;		//This method works in O(1)
	}
	
	public boolean areConnected(Comparable nodeLabel1, Comparable nodeLabel2) {
		if (findPath(nodeLabel1, nodeLabel2, false) == null) {
			return false;
		}
		return true;
	}
	
	// Convert a Tree to a Graph
	public Graph treeToGraph(Tree tree) {													//O(n*log(n))			
		Graph result = new Graph();
		tree.traverseInOrder(new TreeAction() {												//O(n)
			public void run(Tree.TreeNode n)	{
				if (result.findNode(n.getValue()) == null) {								//O(log(n))
					result.addNode(n.getValue());
				}
				if (n.getRightNode() != null) {
					if (result.findNode(n.getRightNode().getValue()) == null) {				//O(log(n))
						result.addNode(n.getRightNode().getValue());
					}					
					result.addEdge(n.getValue(), n.getRightNode().getValue());
				}
				if (n.getLeftNode() != null) {
					if (result.findNode(n.getLeftNode().getValue()) == null) {				//O(log(n))
						result.addNode(n.getLeftNode().getValue());
					}
					result.addEdge(n.getValue(), n.getLeftNode().getValue());
				}
			}
		});			//The Graph cannot be cyclic since it has been formed from a binary Tree, which cannot contain cycles.
		return result;
	}
	
	// Set all visited flags to -1
	private void resetVisitedFlags() {
		nodes.traverseInOrder(new TreeAction() {					//O(n)
			public void run(Tree.TreeNode n) {
				((Node)n.getValue()).visited = -1;					//Set all visited flags to -1 (unvisited)
			}
		});
	}
	
	/* This method is a lengthy, time consuming method finding the SHORTEST path between two Nodes 
	 * if the boolean parameter shortestPath is true. 
	   It returns first path it can find if the boolean is set to false. */
	private Vector findPath(Comparable nodeLabel1, Comparable nodeLabel2, boolean shortestPath) {	// O(n*n)
		Node startState = findNode(nodeLabel1);
		Vector path = new Vector(5);
		path.addLast(startState);
		if (nodeLabel1 == nodeLabel2) {
			startState.visited = 0.0;
			return path;
		}
		Node endState = findNode(nodeLabel2);
		Stack toDoList = new Stack();
		Vector result = new Vector(5);
		double currentDistance = 0;
		double shortestDistance = 0;
		resetVisitedFlags();	//Set all visited flags to -1 (unvisited)
		startState.visited = 0.0;

		
		/* Objects on the Stack will be pairs, the first element of the Pair is the origin of the Edge 
		   while the second element is the Edge. */
		startState.edges.traverseInOrder(new TreeAction() {				
			public void run(Tree.TreeNode n) {
				// Adds the Edges of the Startnode to the Stack
				toDoList.push(new Pair(startState, n.getValue()));
			}
		});

		while (!toDoList.isEmpty()) {
			Pair currentPair = (Pair) toDoList.pop();
			Node sourceNode = (Node) currentPair.element1;
			Edge currentEdge = (Edge) currentPair.element2;
			
			/* This loop checks whether last element of the current path is the source Node.
			*  If it is not then the current path must be a dead-end, we need to go back.
			*  So the last Node should be removed from the path and we check the Node before it. */					
			while (path.getLast() != sourceNode) { 
				path.removeLast();	
			}	
			//We confirmed that the last element in our path is the source Node, we can continue on our path
			
			//Set the tracked distance back to the one of which the current element is an edge
			currentDistance = ((Node) path.getLast()).visited;
			
			// The current Node is only interesting if it has not been visited yet or if we found a shorter path to it
			if(currentEdge.toNode.visited == -1.0 || currentDistance < currentEdge.toNode.visited) {
				if(currentEdge.toNode != startState) {
					currentDistance += currentEdge.weight;
				}
				currentEdge.toNode.visited = currentDistance;
				if (currentEdge.toNode == endState) {	//We have reached the destination
					if (shortestDistance == 0 || currentDistance < shortestDistance) {
						path.addLast(currentEdge.toNode);
						
						/* If we just want a path then we can return the first one we find.
						*  Otherwise we will continue looking for better paths */
						if(!shortestPath) {	
							return path;
						}
						shortestDistance = currentDistance;
						result = path.copy();
						path.removeLast();
					}
				}
				
				/* If the destination is not reached then we keep following the edges of the current Node.
				   This traverse adds all the viable edges to the Stack */
				else if (shortestDistance == 0.0 || currentDistance < shortestDistance){
					final double distanceToThisPoint = currentDistance;
					currentEdge.toNode.edges.traverseInOrder(new TreeAction() {						
						public void run(Tree.TreeNode n) {	//O(n)
							double connectedNodeVisited = ((Edge)n.getValue()).toNode.visited;
							Edge connection = ((Edge)n.getValue());
							if (connectedNodeVisited == -1.0 || connectedNodeVisited > connection.weight + distanceToThisPoint) {			//If this node has not been visited yet, we can add it to the stack
								toDoList.push(new Pair(currentEdge.toNode, connection));
								connectedNodeVisited = connection.weight + distanceToThisPoint;
							}
						}
					});
					path.addLast(currentEdge.toNode); 
					/* Since we are doing a depth first search we will save the current element 
					   in our path before following one of its edges. */				
				}
			}
		}
		return result;
	}
	
}