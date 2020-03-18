/* Queues operate in a first in first out manner (fifo).
 * Pushing and popping happens in O(1) since the Queues are based on CircularVectors in which addLast and removeFirst operate in O(1).
 * Author: Seppe Lampe
 */

public class Queue {
	
	private CircularVector data;
	
	public Queue() {
		data = new CircularVector();
	}

	public void push(Comparable o) {			//O(1) (except in the rare case when capacity is at max, then it is O(n)!!)
		data.addLast(o);
	}

	// Remove the element which has been in the queue the longest and return it
	public Comparable pop() {
		Comparable storage = data.getFirst();	//O(1)
		data.removeFirst();
		return storage;
	}

	// Return the element which has been in the queue the longest
	public Comparable top() {					//O(1)
		return data.getFirst();
	}

	// Return the amount of elements in the Queue
	public int size() {							//O(1)
		return data.size();
	}

	// Return a boolean indicating whether the Queue is empty or not
	public boolean isEmpty() {					//O(1)
		return data.size() == 0;
	}
	
	// Return a String representation of the Queue
	public String toString() {					//O(n)
		return data.toString();
	}
}
