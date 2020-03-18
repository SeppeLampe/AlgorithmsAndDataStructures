/* This class represents a dictionary: a data structure used for storing values associated with a key.
 * It stores objects of the class DictionaryPair (which contains two elements, key and value) in a vector.
 * One can search based on the key.
 * Author: Seppe Lampe
 */

public class Dictionary 
{
	private Vector data;
	
	private class DictionaryPair implements Comparable
	{
	    private Comparable key;
		private Comparable value;
		public DictionaryPair (Comparable someKey, Comparable someValue)
		{
			this.key = someKey;
			this.value = someValue;
		}
		
		@Override
		public int compareTo(Object comp) {											//O(1)
			return this.getKey().compareTo(((DictionaryPair)comp).getKey());
		}
		
		public Comparable getKey() {												//O(1)
			return key;
		}
		public Comparable getValue() {												//O(1)
			return value;
		}
		public void setKey(Comparable newKey) {										//O(1)
			key = newKey;
		}
		public void setValue(Comparable newValue) {									//O(1)
			value = newValue;
		}
	}
	
	public Dictionary() {
		data = new Vector(5);
	}

	// Adds a key and value to the Dictionary
	public void add(Comparable key,Comparable value) {								//O(1)
		int index = (findPosition(new DictionaryPair(key, 0)));
		if (index == -1) data.addLast(new DictionaryPair(key, value));
		else data.set(index, new DictionaryPair(key, value));
	}
	
	// Returns the position of the key in the Vector
	public int findPosition(Comparable key)	{										//O(n)
		DictionaryPair test = new DictionaryPair(key, 0);
		for(int i=0;i<data.size();i++) {
			if(((DictionaryPair)data.get(i)).compareTo(test) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	// Search for a certain key and if it is present in the Dictionary then return the value, otherwise return null
	public Comparable find(Comparable key) {										//O(n)
		int index = (findPosition(new DictionaryPair(key, 0)));
		if (index > -1) return ((DictionaryPair)data.get(index)).getValue();
		else return null;
	}	
}