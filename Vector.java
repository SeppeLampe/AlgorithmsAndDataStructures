/* This class is used widespread in many of the classes of this project.
 * The main attribute of this class is an array, which can be filled with Comparables.
 * It is worth looking at the time complexities of some methods.
 * The main thing to remember is that adding or removing to/from the front (addFirst or removeFirst) operates in O(n),
 * while adding or deleting to/from the back (addLast and removeLast) operates in O(1).
 * Furthermore does a vector take an int as argument during construction. This is the initial length of the vector.
 * If, during its life cycle this is not sufficient then its length is automatically doubled.
 * Lastly, the class keeps track of the length of the vector by keeping a counter 'count'.
 * Author: Seppe Lampe
 */
public class Vector implements Comparable {
	private Comparable data[];
	private int count;
	private int capacity;

	public Vector(int capacity) {
		data = new Comparable[capacity];
		count = 0;
		this.capacity = capacity;
	}

	// This method adds an element to the front
	public void addFirst(Comparable o) {								// O(n)
		count++;
		for (int i = count - 1; i > 0; i--) {
			data[i] = data[i - 1];
		}
		data[0] = o;
	}
	
	// This method adds an element to the back
	public void addLast(Comparable o) {									// O(n)
		if (count == data.length) {
			this.extendedCapacity();
		}
		data[count] = o;
		count++;
	}

	// This method compares two Vectors based on their size
	public int compareTo(Object comp) { 								// O(1)
		return this.size() - ((Vector) comp).size(); 	// Compare based on size
	}

	// This method checks whether an element is present in the Vector
	public boolean contains(Comparable obj) {							// O(n)
		for (int i = 0; i < count; i++) {
			if (data[i].equals(obj))
				return true;
		}
		return false;
	}
	
	// This method creates a copy of the vector
	public Vector copy() { 												// O(n)
		Vector result = new Vector(capacity); 			/* Create a new vector with the values of the data array copied to
														   somewhere else. */
		for (int i = 0; i < count; i++) {
			result.addLast(data[i]);
		}
		return result;
	}

	// This method doubles the capacity of the Vector
	private void extendedCapacity() {									// O(n)
		Comparable data2[] = new Comparable[count * 2];
		for (int i = 0; i < count; i++) {
			data2[i] = data[i];
		}
		data = data2;
		capacity = capacity * 2;
	}

	// This method checks whether the Vector is empty
	public boolean isEmpty() {											// O(1)
		return count == 0;
	}
	
	// This method looks for a certain object in the Vector and returns it or null if absent
	public Comparable find(Comparable obj) { 							// O(n)
		for (int i = 0; i < count; i++) {
			if (data[i].compareTo(obj) == 0) {
				return data[i];
			}
		}
		return null;
	}

	// This method returns the value of a certain position
	public Comparable get(int index) { 									// O(1)
		if (index >= count) {
			return String.format("Vector does not contain %d elements", index + 1);
		}
		return data[index];
	}

	// This method return the capacity
	public int getCapacity() { 											// O(1)
		return capacity;
	}

	// This method return the first element
	public Comparable getFirst() 										// O(1)
	{
		return data[0];
	}

	// This method return the last element
	public Comparable getLast() {										// O(1)
		return data[count - 1];
	}

	// This method return the index of a certain object (actually the lowest index).
	public int index(Comparable obj) { 									// O(n)
		for (int i = 0; i < count; i++) {
			if (data[i].equals(obj))
				return i;
		}
		return -1;
	}

	// This method interleaves two Vectors, it takes an element from the first Vector, then the second, then the first, ...
	public Vector interleave(Vector secondVector) {						// O(n)
		Vector result = new Vector(count + secondVector.size());
		int i = 0;
		while (result.size() != count + secondVector.size()) {
			if (i >= count) {
				result.addLast(secondVector.data[i]);
			} else if (i >= secondVector.size()) {
				result.addLast(data[i]);
			} else {
				result.addLast(data[i]);
				result.addLast(secondVector.data[i]);
			}
			i++;
		}
		return result;
	}

	// This method prints out a Vector
	public void print() { 												// O(n)
		System.out.println(toString());
	}

	// This method removes the first element of a Vector
	public void removeFirst() { 										// O(n)
		if (isEmpty()) {
			System.out.println("Vector is already empty");
		} else {
			for (int i = 0; i < count; i++) {
				data[i] = data[i + 1];
			}
			count--;
		}
	}

	// This method removes the last element of a Vector
	public void removeLast() { 											// O(1)
		if (isEmpty()) {
			System.out.println("Vector is already empty");
		}
		data[count - 1] = null;
		count--;
	}

	// This method reverses a Vector
	public void reverse() {// O(n)
		for (int i = 0; i < count / 2; i++) {
			Comparable temp = data[i];
			data[i] = data[count - 1 - i];
			data[count - 1 - i] = temp;
		}
	}

	// This method sets a certain index of the Vector equal to a certain object.
	public void set(int index, Comparable obj) {						// O(1)
		if (data[index] == null) {
			count += 1;
		}
		data[index] = obj;
	}

	// This method returns the current size of the Vector
	public int size() {// O(1)
		return count;
	}

	// This method returns a String representing the elements in the Vector
	public String toString() { 											// O(n)
		String result = new String();
		if (count > 0) {
			for (int i = 0; i < count - 1; i++) {
				result += data[i].toString() + " "; 			// Add all elements except for the last one
			}
			result += data[count - 1].toString(); 				// Add the last element but without the trailing empty space
		}
		return result;
	}

	// This method doubles a Vector i.e., returns a Vector with each element twice in it. 
	public Vector vectorDouble() {										// O(n)
		Vector result = new Vector(count * 2);
		for (int i = 0; i < count; i++) {
			result.addLast(data[i]);
			result.addLast(data[i]);
		}
		return result;
	}

}