import java.util.Comparator;
/* This class is a sorted LinkedList. It stores the data in a specialised structure i.e., Pair.
 * This Pair contains two things: the priority of the object (an integer) and the object itself.
 * When inserting a new element in the PriorityQueue the LinkedList is traversed.
 * When an element is found with a lower priority then the new object is stored in front of it.
 * Author: Seppe Lampe
 */
public class PriorityQueue 
{       
	public class Pair implements Comparable
	{
		public Object element;
		public int priority;
	
		public Pair(Object element , int priority) {
			this.element = element;
			this.priority = priority;
		}
		
		public int compareTo(Object o) {					//O(1)
		Pair p2 = (Pair)o;
		return ((Comparable)priority).compareTo(p2.priority);
		}
	}

	private LinkedList data;

	public PriorityQueue()
	{
		data = new LinkedList();
	}

	// Add a new element with a certain priority to the PriorityQueue
	public void push(Object o, int priority)				//O(n)
	{
		Pair newPair = new Pair(o, priority);
		data.addSorted(newPair);		
	}

	// Remove the element with the highest priority and return it
	public Object pop()										//O(1)
	{
		Object storage = data.getFirst();
		data.removeFirst();
		return storage;
	}

	// Show the element with the highest priority
	public Object top()										//O(1)
	{
		return data.getFirst();
	}
}
