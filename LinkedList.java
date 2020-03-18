/* This class represents a list where each element has a pointer to the next.
 * Each element of the list is an instance of ListElement which contains two things.
 * el1, the data of itself and el2, a pointer to the next element of the list. 
 * There is one root 'head' and a counter 'count' for keeping track of the length.
 * Various methods have different time complexities, it is important to remember this.
 * Adding to or removing from the front (addFirst and removeFirst) operate in O(1)
 * while adding to and removing from the back (addLast and removeLast) operate in O(n).
 * Author: Seppe Lampe
 */
public class LinkedList {
	private int count;

	private class ListElement {
		private Comparable el1;
		private ListElement el2;

		public ListElement(Comparable el, ListElement nextElement) {
			el1 = el;
			el2 = nextElement;
		}

		public ListElement(Comparable el) {
			this(el, null);
		}

		public Comparable first() {
			return el1;
		}

		public ListElement rest() {
			return el2;
		}

		public void setFirst(Comparable value) {
			el1 = value;
		}

		public void setRest(ListElement value) {
			el2 = value;
		}

	}

	private ListElement head;

	public LinkedList() {
		head = null;
		count = 0;
	}
	
	// This method returns the current size of the LinkedList
	public int size() {									//O(1)
		return count;
	}
	
	// This method returns whether the LinkedList is empty
	public boolean isEmpty() { 							//O(1)
		return size() == 0;
	}

	// This method adds an element to the front of the LinkedList
	public void addFirst(Comparable o) { 				//O(1)
		head = new ListElement(o, head);
		count++;
	}

	// This method adds an element to the back of the LinkedList
	public void addLast(Comparable obj) { 				//O(n)
		ListElement last = new ListElement(obj, null);
		ListElement temp = head;
		if (temp != null) {
			while (temp.rest() != null) {
				temp = temp.rest();
			}
			temp.el2 = last;
			count++;
		} 
		else { 											// If the LinkedList is empty temp.el2 will be non-existent
			this.addFirst(obj);
		}
	}

	// This method returns the first element of the LinkedList
	public Comparable getFirst() {						//O(1)
		return head.first();
	}
	
	// This method returns the last element of the LinkedList
	public Comparable get(int n) {						//O(n)
		try {
			ListElement temp = head;
			while (0 < n) {
				temp = temp.rest();
				n--;
			}
			return temp.first();
		}
		catch(NullPointerException e) {
			return "List is not that long.";
		}
	}

	// This method returns the last element in the LinkedList
	public Comparable getLast() {						//O(n)
		ListElement temp = head;
		if (temp != null) {
			while (temp.rest() != null) {
				temp = temp.rest();
			}
			return temp.first();
		} 
		else {
			return "The list is empty";
		}
	}

	// This method sets the nth element to a certain object
	public void set(int n, Comparable obj) {			//O(n)
		ListElement temp = head;
		try {
			while (0 <= n) {
				if (n == 0) {
					temp.el1 = obj;
					return;
				}
				temp = temp.rest();
				n--;
			}
		} catch (NullPointerException e) {
			System.out.println("The list has less than n elements");
		}
	}

	// This method checks whether an object is in the LinkedList
	public boolean contains(Comparable obj) {			//O(n)
		ListElement temp = head;
		while (temp != null) {
			if (temp.first() == obj) {
				return true;
			}
			temp = temp.rest();
		}
		return false;
	}
	
	// This method checks whether an object is in the LinkedList and returns it or null if it is not present
	public Comparable find(Comparable obj) {			//O(n)
		ListElement temp = head;
		while (temp != null) {
			if (temp.first().compareTo(obj) == 0) {
				return temp.first();
			}
			temp = temp.rest();
		}
		return null;
	}

	// This method prints the LinkedList
	public void print() {								//O(n)
		try {
			System.out.println(this.toString());
		} catch (NullPointerException e) {
			System.out.println("The list is empty.");
		}
	}

	// This method returns a String representing the elements in the LinkedList
	public String toString() {							//O(n)
		String result = "";
		ListElement temp = head;
		while (temp.rest() != null) {
			result += temp.first().toString() + " ";
			temp = temp.rest();
		}
		result += temp.first().toString();
		return result;
	}

	// This method removes the first element of the LinkedList
	public void removeFirst() {							//O(1)
		head = head.el2;
		count--;
	}

	// This method removes the last element of the LinkedList
	public void removeLast() {							//O(n)
		ListElement temp = head;
		ListElement previous = null;
		if (temp != null) {
			if (temp.rest() == null) {
				head.el1 = null;
			} 
			else {
				while (temp.rest() != null) {
					previous = temp;
					temp = temp.rest();
				}
				previous.el2 = null;
			}
			count--;
		} 
		else {
			System.out.println("The list was already empty");
		}
	}
	
	// This method reverses the LinkedList
	public void reverse() {									//O(n)
		ListElement prev = head;
		ListElement current = head.rest();
		ListElement next = head.rest();
		while (next != null) {
			current = current.rest();
			next.setRest(prev);
			prev = next;
			next = current;
		}
		head.setRest(null);
		head = prev;
	}

	// This method interleaves two LinkedLists, it takes an element from the first LinkedLists, then the second, then the first, ...
	public LinkedList interleave(LinkedList secondList) {	//O(n)
		LinkedList result = new LinkedList();
		ListElement first = head;
		ListElement second = secondList.head;
		while (first != null & second != null) {
			if (first != null) {
				result.addLast(first.first());
				first = first.rest();
			}
			if (second != null) {
				result.addLast(second.first());
				second = second.rest();
			}
		}
		return result;
	}

	// This method fropples a LinkedList. This means swapping the first and second element, the third and fourth, etc.
	public void fropple() {									//O(n)
		ListElement odd = head;
		ListElement even = head.rest();
		ListElement storage = head.rest();
		even = even.rest();
		head = head.rest(); 			// Start with the second element
		try {
			while (even != null) {
				storage.el2 = odd; 		// Link even index to the previous index
				storage = even;
				even = even.rest();
				odd.el2 = even; 		// Link odd index to index + 3
				odd = storage;
				storage = even;
				even = even.rest();
				storage.el2 = odd; 		// Link even index to the previous index (necessary to include the very last
										// element)
			}
			odd.el2 = null; 			// Unlink the now last element from the original last element
		} catch (NullPointerException e) {
			return;
		}
	}

	// This method appends the elements of a LinkedList to another LinekdList
	public void append(LinkedList list2) { 					//O(n)
		ListElement temp = head;
		while (temp.rest() != null) {
			temp = temp.rest();
		}
		temp.el2 = list2.head;
		count += list2.size();
	}

	// This method creates a sorted LinkedList
	public void addSorted(Comparable o) {					//O(n)
		if (head == null) 							// an empty list , add element in front
			head = new ListElement(o, null);
		else if (head.first().compareTo(o) > 0) {	// we have to add the element in front
			head = new ListElement(o, head);
		} else {									// we have to find the first element which is bigger
			ListElement temp = head;
			while ((temp.rest() != null) && (temp.rest().first().compareTo(o) < 0)) {
				temp = temp.rest();
			}
			ListElement next = temp.rest();
			temp.setRest(new ListElement(o, next));
		}
		count++;
	}
	
	// This method can be used to insert a certain element at a certain position in the LinkedList
	public void insert(int index, Comparable o) {			//O(n)
		if (head == null) head = new ListElement(o, null);
		else {
			ListElement temp = head;
			while (0 < index) {
				temp = temp.rest();
				index--;
			}
			ListElement newElement = new ListElement(o, temp.rest());
			temp.setRest(newElement);
		}
	}
	
	public void fropple2() {
		if(count < 2) {
			return;
		}
		ListElement small = head;
		ListElement big = small.rest();
		ListElement next = small.rest();
		head = head.rest();
		while(next != null) {
			next = next.rest();
			big.setRest(small);
			if(next== null ||  next.rest() == null) {
				small.setRest(next);
				return;
			}
			big = next;
			next = next.rest();
			small.setRest(next);
			small = big;
			big = next;
		}
	}

}
