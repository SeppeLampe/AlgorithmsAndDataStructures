/* This class is a variation on the LinkedList class. Adding and removing happens in O(1) no matter at which side.
 * It is actually a LinkedList and a reversed LinkedList combined. 
 * Each element of the list has a pointer to the next and the previous element.
 * This means that the structure can be traversed in two directions.
 * Author: Seppe Lampe
 */
public class DoubleLinkedList {
	private class DoubleLinkedListElement {

		private Object data;
		private DoubleLinkedListElement nextElement;
		private DoubleLinkedListElement previousElement;

		public DoubleLinkedListElement(Object v, DoubleLinkedListElement next, DoubleLinkedListElement previous) {
			data = v;
			nextElement = next;
			previousElement = previous;
			if (nextElement != null) nextElement.previousElement = this;
			if (previousElement != null) previousElement.nextElement = this;
		}
		
		public DoubleLinkedListElement(Object v) {
			this(v,null , null);
		}
		
		public DoubleLinkedListElement previous() {						//O(1)
			return previousElement;
		}
		
		public Object value() {											//O(1)
			return data;
		}
		
		public DoubleLinkedListElement next() {							//O(1)
			return nextElement;
		}
		
		public void setNext(DoubleLinkedListElement value) {			//O(1)
			nextElement = value;
		}
		
		public void setPrevious(DoubleLinkedListElement value) {		//O(1)
			previousElement = value;
		}
	}
	
	private int count ;
	private DoubleLinkedListElement head;
	private DoubleLinkedListElement tail;
	
	public DoubleLinkedList() {
		head = null;
		tail = null;
		count = 0;
	}
	
	// This method returns the first element of the DoubleLinkedList
	public Object getFirst() {											//O(1)
		return head.value();
	}
	
	// This method returns the last element of the DoubleLinkedList
	public Object getLast()	{											//O(1)
		return tail.value();
	}
	
	// This method returns the size of the DoubleLinkedList
	public int size() {													//O(1)
		return count;
	}
	
	// This method adds an element to the front of the DoubleLinkedList
	public void addFirst(Object value) {								//O(1)
		head = new DoubleLinkedListElement (value, head, null);
		if (tail == null) tail = head;
		count++;
	}
	
	// This method adds an element to the back of the DoubleLinkedList
	public void addLast(Object value) {									//O(1)
		tail = new DoubleLinkedListElement (value , null , tail);
		if (head == null) head = tail;
		count++;
	}
	
	// This method removes the first of the DoubleLinkedList
	public void removeFirst() {											//O(1)
		if(head != null) {
			head = head.next();
			if(head == null) tail = null;
			else head.setPrevious(null);
			count--;
		}
	}
	
	// This method removes the last of the DoubleLinkedList
	public void removeLast() {											//O(1)
		if(tail != null) {
			tail = tail.previous();
			if(tail == null) head = null;
			else tail.setNext(null);
			count--;
		}
	}
	
	// This method prints out a representation of the DoubleLinkedList
	public void print()	{												//O(n)
		DoubleLinkedListElement d = head;
		System.out.print("(");
		while (d != null)
		{
			System.out.print(d.value() + " ");
			d = d.next();
		}
		System.out.println(")");
	}
	
	// This method prints out a representation of the reverse of the DoubleLinkedList
	public void printReverse() {										//O(n)
		DoubleLinkedListElement d = tail;
		System.out.print("(");
		while (d != null)
		{
			System.out.print(d.value() + " ");
			d = d.previous();
		}
		System.out.println(")");
	}
	
	// This method returns a String representation of the elements in the DoubleLinkedList
	public String toString() {											//O(n)
		String result = new String();
		DoubleLinkedListElement plus = head;
		while (plus != null) {
			result += plus.value().toString() + " ";
			plus = plus.next();
		}
		result = result.substring(0, result.length() - 1); // Remove the space before the closing bracket
		return result;
	}
	
	// This method reverses a DoubleLinkedList
	public void reverse() {												//O(n)
		head = tail;
		DoubleLinkedListElement temp = head;
		DoubleLinkedListElement plus = head.previous();
		head.nextElement = head.previousElement;
		DoubleLinkedListElement prev = head;
		while(plus!=null) {
			plus = plus.previous();
			temp = temp.next();
			temp.nextElement = plus;
			temp.previousElement = prev;
			prev = temp;
		}		
	}
}
