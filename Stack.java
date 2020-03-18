/* A stack operates as a filo structure (first in last out).
 * It is based on the LinkedList class with pushing and popping happening in O(1).
 * This is done via the addFirst and removeFirst methods of LinkedList which operate in O(1).
 * Author: Seppe Lampe
 */
public class Stack {
	
	private LinkedList data;

	public Stack() {
		data = new LinkedList();
	}

	// This method adds an object to the top of the Stack
	public void push(Comparable o) {		//O(1)
		data.addFirst(o);
	}

	// This method removes the object at the top of the Stack
	public Comparable pop() {				//O(1)
		if(isEmpty()) {
			return "No elements in stack, cannot pop.";
		}
		else {
			Comparable storage = data.getFirst();
			data.removeFirst();
			return storage;
		}
	}

	// This method returns the object at the top of the Stack
	public Comparable top() {				//O(1)
		if(isEmpty()) {
			return "No elements in stack, cannot return top.";
		}
		else {
			return data.getFirst();
		}
	}
	
	// This method returns the size of the Stack
	public int size() {						//O(1)
		return data.size();
	}
	
	// This method checks whether the Stack is empty
	public boolean isEmpty() {				//O(1)
		return data.size() == 0;
	}
}
