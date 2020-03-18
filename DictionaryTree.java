/* This class represents a dictionary: a data structure used for storing values associated with a key.
 * It stores objects of the class DictionaryPair (which contains two elements, key and value) in a Tree.
 * One can search based on the key.
 * Personally I would call this class 'Dictionary' but since we already made another implementation of it
 * called 'Dictionary', this one is called DictionaryTree. Personally I would get rid of the normal 'Dictionary'
 * class and just rename this one. But I believe we are to hand in all the classes used for this class,
 * whether or not they are implemented in the final version of our project.
 * Author: Seppe Lampe
 */
public class DictionaryTree{
	private RedBlackTree data;
	
	public class DictionaryPair implements Comparable
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
		
		public Comparable getKey()													//O(1)
		{
			return key;
		}
		public Comparable getValue()												//O(1)
		{
			return value;
		}
		public void setKey(Comparable newKey)										//O(1)
		{
			key = newKey;
		}
		public void setValue(Comparable newValue)									//O(1)
		{
			value = newValue;
		}
	}
	
	public DictionaryTree()
	{
		data = new RedBlackTree();
	}
	
	// Adds a key and value to the Dictionary
	public void add(Comparable key,Comparable value)									//O(log(n))
	{
		data.insert(new DictionaryPair(key, value));
	}
	
	// Searches for a DictionaryPair based on a certain key, returns the DictionaryPair.
	public DictionaryPair find(Comparable key) {										//O(log(n))
		return (DictionaryPair)data.find(new DictionaryPair(key, 0));
	}
	
	// Searches for a DictionaryPair based on a certain key, returns the key of the DictionaryPair.
	public Comparable findKey(Comparable key) {											//O(log(n))
		DictionaryPair pair = this.find(key);
		if(pair == null) {
			return null;
		}
		else {
			return pair.getKey(); 
		}
	}
	
	// Searches for a DictionaryPair based on a certain key, returns the value of the DictionaryPair.
	public Comparable findValue(Comparable key) {										//O(log(n))
		DictionaryPair pair = this.find(key);
		if(pair == null) {
			return null;
		}
		else {
			return pair.getValue(); 
		}
	}
	
	public void traverseInOrder(TreeAction action) { // O(n)
		data.traverseNode(data.root, action);
	}
}
