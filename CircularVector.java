/* This class is a variation on the Vector class. Adding and removing happens in O(1) no matter at which side.
 * Author: Seppe Lampe
 */
import java.util.Comparator;
public class CircularVector
{
	private Vector data;
	private int first;
	private int count;

	public CircularVector()
	{
		count = 0;
		first = 0;
		data = new Vector(5);
	}
	// This method returns the current size of the CircularVector
	public int size()							//O(1)
	{
		return count;
	}
	
	// This method adds an element to the front of the CircularVector
	public void addFirst(Comparable element)	//O(1) except when capacity is at max!
	{
		if (count == data.getCapacity()) {		//O(n) if this case is true!!!! (Very seldom)
			this.extendedCapacity();
		}
		if (first == 0 && count == 0) { 
			data.set(0, element); 				//if empty
		}
		else {
			data.set((first-1+data.getCapacity())%data.getCapacity(), element);
		}
		count++;
		first--;
		if (first < 0) {
			first = data.getCapacity() - 1;
		}
	}

	// This method adds an element to the back of the CircularVector
	public void addLast(Comparable element)		//O(1) except when capacity is at max!
	{
		if (count == data.getCapacity()) {
			this.extendedCapacity();			//O(n) if this case is true!!!! (Very seldom)
		}
		data.set((first + count)%data.getCapacity(), element);
		count++;
	}

	// This method adds an element to the back of the CircularVector
	public Comparable getFirst() {				//O(1)
		return data.get(first);
	}
	
	// This method returns the element on certain position of the CircularVector
	public Comparable get(int index) {			//O(1)
		return data.get(index);
	}

	// This method returns the last element of the CircularVector
	public Comparable getLast()	{				//O(1)
		return data.get((first + count - 1)%data.getCapacity());
	}

	// This method returns the last element of the CircularVector
	public void removeFirst() {					//O(1)
		if(count > 0)
		{
			first = (first+1)%data.getCapacity();
			count--;
		}
	}

	// This method removes the last element of the CircularVector
	public void removeLast() {					//O(1)
		if(count > 0) {
			data.set((first + count)%data.getCapacity(), null);
			count--;
		}
	}
	
	// This method doubles the capacity of the CircularVector
	public void extendedCapacity() {			//O(n)
		Vector newVector = new Vector(count*2);
		for(int i=0; i<count; i++)
		{
			newVector.addLast(data.get(first));
			first = (first+1)%data.getCapacity();
		}
		first = 0;
		data = newVector;
	}

	// This method returns a String representing the elements in the CircularVector
	public String toString() {					//O(n)
		String result = new String();
		for(int i=0;i<count-1;i++) result += (data.get((first + i) % data.getCapacity())).toString() + " ";
		result += this.getLast().toString();
		return result;
	}
}