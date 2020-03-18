//  ___   ___  _  _ _ _____    ___ _  _   _   _  _  ___ ___ _ _ _ 
// |   \ / _ \| \| ( )_   _|  / __| || | /_\ | \| |/ __| __| | | |
// | |) | (_) | .` |/  | |   | (__| __ |/ _ \| .` | (_ | _||_|_|_|
// |___/ \___/|_|\_|   |_|    \___|_||_/_/ \_\_|\_|\___|___(_|_|_)
                                                               
public interface IManagementSystem {

	/*
	 * Add a new house with given parameters - street where the house is located,
	 * number of bedrooms, number of bathrooms, price per month - to the management system
	 *
	 * @param street where the house is located
	 *
	 * @param bedrooms number of bedrooms
	 * 
	 * @param bathrooms number of bathroom
	 * 
	 * @param price price per month
	 *
	 * @return ID of the house
	 */
	public int addNewHouse(String street, int bedrooms, int bathrooms, int price);

	/*
	 * Add a new apartment with given parameters - street where the apartment is located, 
	 * number of bedrooms, number of bathrooms, price per month, indication whether 
	 * there's a lift in the building, floor on which the apartment is, 
	 * number of floors in the building - to the management system
	 *
	 * @param street where the house is located
	 *
	 * @param bedrooms number of bedrooms
	 * 
	 * @param bathrooms number of bathrooms
	 * 
	 * @param price price per month
	 * 
	 * @param lift is there a lift in the building
	 * 
	 * @param apartmentFloor floor on which is the apartment
	 * 
	 * @param buildingFloors number of floors in the building
	 *
	 * @return ID of the apartment
	 */
	public int addNewApartment(String street, int bedrooms, int bathrooms, int price, boolean lift, int apartmentFloor,	int buildingFloors);

	/*
	 * Add a new client with given parameters - name, email address to the
	 * management system
	 *
	 * @param name name of the client
	 * 
	 * @param emailAddress email address of the client
	 *
	 * @return ID of the client
	 */
	public int addRegularClient(String name, String emailAddress);

	/*
	 * Add a new VIP client with given parameters - name, email address to the
	 * management system
	 *
	 * @param name name of the client
	 * 
	 * @param emailAddress email address of the client
	 *
	 * @return ID of the VIP client
	 */
	public int addVIPClient(String name, String emailAddress);

	/*
	 * Search for a place based on the price range. Print all the found properties.
	 *
	 * @param minPrice minimal price of a place (house or apartment)
	 * 
	 * @param maxPrice maximal price of a place (house or apartment)
	 */
	public void searchOnPrice(int minPrice, int maxPrice);

	/*
	 * Search for a place based on the minimum number of bedrooms and max price.
	 * Print all the found properties.
	 *
	 * @param minBedrooms minimum of bedrooms in the house or apartment
	 * 
	 * @param maxPrice maximal price of a place (house or apartment)
	 */
	public void searchOnBedroomsAndPrice(int minBedrooms, int maxPrice);
	
	/*
	 * Search for a place within a circle, given by center point and radius in kilometers.
	 * Print all the found properties.
	 *
	 * @param street is a center point for the search
	 * 
	 * @param radius is radius in km for which the seach is applied
	 */
	public void searchOnDistance(String street, double radius);

	/*
	 * Request a visit of selected property (house or apartment)
	 *
	 * @param client ID of a client who's requesting a visit
	 * 
	 * @param property ID of a property to visit (house or apartment)
	 */
	public void requestVisit(int client, int property);

	/*
	 * Organize visit for each property where 5 or more people requested a visit.
	 * Print the property details and all client names.
	 *
	 */
	public void organizeVisits();

	/*
	 * Print all houses in the system. Print number of houses and a summary details
	 * about each one.
	 */
	public void printHouses();

	/*
	 * Print all apartments in the system. Print number of apartments and a summary
	 * details about each one.
	 */
	public void printApartments();

	/*
	 * Print all regular clients. Print number of clients and a summary details
	 * about each one.
	 */
	public void printRegularClients();

	/*
	 * Print all VIP clients. Print number of VIP clients and a summary details
	 * about each one.
	 */
	public void printVIPClient();

	/*
	 * Adds a street into the system
	 *
	 * @param streetName name of the street that is added to the system
	 * 
	 */
	public void addStreet(String streetName);
	
	/*
	 * Adds connection between streets into the system
	 *
	 * @param street1 name of the first street that is being connected
	 * 
	 * @param street2 name of the second street that is being connected
	 * 
	 * @param distance is the distance between the streets
	 * 
	 */
	public void connectStreets(String street1, String street2, double distance);
}

