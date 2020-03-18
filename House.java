public class House extends RealEstate{
/* This class is made to represent houses, it receives most of its functionality from the RealEstate superclass.
 * Only the toString method is specific for this class.
 * Author: Seppe Lampe
 */
	
	public House(String street, int bedrooms, int bathrooms, int price, int id) {
		super(street, bedrooms, bathrooms, price, id);
	}
	
	// Returns a String containing the general info of the House
	public String toString() {						//O(1)
		return String.format("House id%d on %s which has %d bedroom(s), %d bathroom(s) and a price of €%d.", id, street, bedrooms, bathrooms, price);
	}
}