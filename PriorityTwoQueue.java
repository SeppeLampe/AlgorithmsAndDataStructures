/*
 * This class is made as part of the PriorityQueue exercises (exercise 3).
 * It is useful in the project for keeping track of the requests for a single building.
 * This is because the clients only have two priority states (priority or no priority) and are further sorted based on first come first serve.
 * Pushing and popping happens in O(1) since our Queues are based on CircularVectors.
 * in CircularVectors addLast and removeFirst operate in O(1).
 * Author: Seppe Lampe
 */

public class PriorityTwoQueue implements Comparable{
	private Queue priority;
	private Queue normal;

	public PriorityTwoQueue() {
		priority = new Queue();
		normal = new Queue();
	}

	// Adds a new element to the PriorityTwoQueue, with a boolean indicating whether it has priority or not.
	public void push(Comparable o, boolean important) {						//O(1)
		if (important) {
			priority.push(o);
		}
		else {
			normal.push(o);
		}
	}
	
	// Compares two PriorityTwoQueues based on their size
	@Override
	public int compareTo(Object comp) {		//Compare size					//O(1)
		return this.size() - ((PriorityTwoQueue)comp).size();
	}
	
	// Returns the total size of the PriorityTwoQueues
	public int size() {														//O(1)
		return priority.size() + normal.size();
	}

	// Remove the element with the highest priority and return it
	public Comparable pop() {												//O(1)
		if (priority.size() > 0) return priority.pop();
		else return normal.pop();
	}

	// Return the element with the highest priority
	public Comparable top() {												//O(1)
		if (priority.size() > 0) return priority.top();
		else return normal.top();
	}
}
