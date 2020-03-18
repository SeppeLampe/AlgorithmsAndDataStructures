import java.util.Comparator;
/* This is a superclass for all varieties of real estate types.
 * Its most noticeable parameter is the 'requests' PriorityTwoQueue.
 * This is used for storing the requests based on priority (VIP or Regular client).
 * Author: Seppe Lampe
 */
public class RealEstate implements Comparable{
	protected String street;
	protected int bedrooms;
	protected int bathrooms;
	protected int price;
	protected int id;
	protected PriorityTwoQueue requests;
	
	public RealEstate(String street, int bedrooms, int bathrooms, int price, int id) {
		this.street = street;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.price = price;
		this.id = id;
		this.requests = new PriorityTwoQueue();
	}
	
	
	
	public String getStreet() {								//O(1)
		return street;
	}

	public int getBedrooms() {								//O(1)
		return bedrooms;
	}
	
	public int getBathrooms() {								//O(1)
		return bathrooms;
	}
	
	public int getPrice() {									//O(1)
		return price;
	}
	
	public int getId() {									//O(1)
		return id;
	}
	
	public PriorityTwoQueue getRequests() {					//O(1)
		return requests;
	}
	
	public int compareTo(Object structure) {				//O(1)
		return id - ((RealEstate)structure).getId();		// Comparing is done based on id since this parameter is unique.
	}
	
}
