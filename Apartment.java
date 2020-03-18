/* This class is an instance of the RealEstate class used for apartments
 * It has several unique parameters i.e., lift, a boolean indicating if there is an elevator or not.
 * apartmentFloor, an integer displaying on which floor of the building the apartment can be found 
 * and buildingFloor the total amount of floors present in the apartment building.
 * Author: Seppe Lampe 
 */

public class Apartment extends RealEstate{ 
	private boolean lift;
	private int apartmentFloor;
	private int buildingFloors;
	

	public Apartment(String street, int bedrooms, int bathrooms, int price, boolean lift, int apartmentFloor, int buildingFloors, int id) {
		super(street, bedrooms, bathrooms, price, id);
		this.lift = lift;
		this.apartmentFloor = apartmentFloor;
		this.buildingFloors = buildingFloors;
	}
	
	public boolean isLift() {									//O(1)
		return lift;
	}
	
	public int getApartmentFloor() {							//O(1)
		return apartmentFloor;
	}
	
	public int getBuildingFloors() {							//O(1)
		return buildingFloors;
	}
	
	// This method returns a String with the general information of the Apartment
	public String toString() {									//O(1)
		String elevator = "no";
		if (isLift()) {
			elevator = "an";
		}
		return String.format("Apartment id%d on %s which has %d bedroom(s), %d bathroom(s) and a price of €%d. The apartment is on floor %d out of %d and there is %s elevator present in the building.", id, street, bedrooms, bathrooms, price, apartmentFloor, buildingFloors, elevator);
	}
}