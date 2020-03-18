/* This class is used to implement a Real Estate Management System.
 * It uses a wide range of supporting classes to keep track of real estate, clients, requests for visits,
 * organising visits and various search options.
 * Author: Seppe Lampe
 */
public class ManagementSystem implements IManagementSystem {
	private RedBlackTree buildings;
	private RedBlackTree clients;
	private Graph map;
	private int normalCount;
	private int vipCount;
	private int houseCount;
	private int apartmentCount;
	private DictionaryTree visitRequests;
	/* I doubted a long time to include a PriorityTwoQueue to each building in the RealEstate class.
	 * This is somewhat more practical for requesting the visits but is more heavy on organising the visits.
	 * However I finally decided to implement it here as a DictionaryTree storing the buildingIds as keys 
	 * with the PriorityTwoQueues as values since I believe that keeping track of requested visits belongs
	 * to/ is a property of a Real Estate Management System and not to a class representing a building.
	 */
	
	public ManagementSystem()	
	{
		/* Every added type of real estate (house or apartment) will be added in a RedBlackTree.
		   In a real-world scenario this Tree will be updated and searched frequently, resulting in
		   the choice of a RedBalckTree above a normal BST or AVLTree. The same argumentation was
		   used for the client Tree. */
		buildings = new RedBlackTree();			
		clients = new RedBlackTree();				
		map = new Graph();
		/* Each requested visit will be stored in this Dictionary.
		   The keys of the DictionaryPairs will be buildingIds while the values will be PriorityTwoQueues 
		   storing the client names*/
		visitRequests = new DictionaryTree();
	}

	/* Adds a new House to the database. Requires a street name, the amount of bedrooms,
	   the amount of bathrooms and the price as in input. */
	public int addNewHouse(String street, int bedrooms, int bathrooms, int price) {											//O(log(n))									
		houseCount++;
		buildings.insert(new House(street, bedrooms, bathrooms, price, houseCount+apartmentCount));
		return houseCount+apartmentCount;
	}
	
	/* Adds a new Apartment to the database. Requires a street name, the amount of bedrooms,
	   the amount of bathrooms and the price as in input. As well as a boolean indicating
	   the presence of an elevator, the floor the apartment is on and the total amount of floors in the building */
	public int addNewApartment(String street, int bedrooms, int bathrooms, int price, boolean lift, int apartmentFloor, int buildingFloors) {		
		apartmentCount++;																						//O(log(n))
		buildings.insert(new Apartment(street, bedrooms, bathrooms, price, lift, apartmentFloor, buildingFloors, houseCount+apartmentCount));
		return houseCount+apartmentCount;
	}
	
	// Adds a regular client to the database. Needs a name and email-address as input
	public int addRegularClient(String name, String emailAddress) {												//O(log(n))
		normalCount++;
		clients.insert(new Client(name, emailAddress, normalCount+vipCount, false));
		return normalCount+vipCount;
	}
	
	// Adds a VIP client to the database. Needs a name and email-address as input
	public int addVIPClient(String name, String emailAddress) {													//O(log(n))
		vipCount++;
		clients.insert(new Client(name, emailAddress, normalCount+vipCount, true));
		return normalCount+vipCount;
	}
	
	// Prints all the houses in the database
	public void printHouses() {																					//O(n)
		System.out.println(String.format("The %d houses in the system are:", houseCount));
		buildings.traverseInOrder(new TreeAction()
		{
			public void run(Tree.TreeNode n)	{
				if(n.getValue().getClass() == House.class)
					System.out.println(n.getValue());
			}
		});
	}
	
	// Prints all the apartments in the database
	public void printApartments() {																				//O(n)
		System.out.println(String.format("The %d apartments in the system are:", apartmentCount));
		buildings.traverseInOrder(new TreeAction() {
			public void run(Tree.TreeNode n) {
				if(n.getValue().getClass() == Apartment.class)
					System.out.println(n.getValue());
			}
		});
	}
	
	// Prints all the regular clients in the database
	public void printRegularClients() {																			//O(n)
		System.out.println(String.format("The %d regular clients in the system are:", normalCount));
		clients.traverseInOrder(new TreeAction() {
			public void run(Tree.TreeNode n)	{
				if(((Client)n.getValue()).getVip() == false)
					System.out.println(n.getValue());
			}
		});
	}
	
	// Prints all the VIP clients in the database
	public void printVIPClient() {																				//O(n)
		System.out.println(String.format("The %d VIP clients in the system are:", vipCount));
		clients.traverseInOrder(new TreeAction() {
			public void run(Tree.TreeNode n)	{
				if(((Client)n.getValue()).getVip() == true)
					System.out.println(n.getValue());
			}
		});
	}

	// Searches for real estate in a given price range.
	public void searchOnPrice(int minPrice, int maxPrice) {														//O(n)
		System.out.println(String.format("The properties within the price range of €%d - €%d are:", minPrice, maxPrice));
		buildings.traverseInOrder(new TreeAction() {
			public void run(Tree.TreeNode n) {
				int price = ((RealEstate)n.getValue()).getPrice();
				if(price >= minPrice && price <= maxPrice) {
					System.out.println(n.getValue());
				}
			}
		});
	}
	
	// Searches the database for real estate with a minimum amount of bedrooms but below a certain price.
	public void searchOnBedroomsAndPrice(int minBedrooms, int maxPrice) {										//O(n)
		System.out.println(String.format("The properties with at least %d bedrooms and a max price of €%d are:", minBedrooms, maxPrice));
		buildings.traverseInOrder(new TreeAction() {
			public void run(Tree.TreeNode n) {
				if(((RealEstate)n.getValue()).getPrice() <= maxPrice && ((RealEstate)n.getValue()).getBathrooms() >= minBedrooms)
					System.out.println(n.getValue());
			}
		});
	}
	
	// Searches for real estate in a given distance from a point.
	public void searchOnDistance(String street, double radius) {
		buildings.traverseInOrder(new TreeAction() {
			public void run(Tree.TreeNode n) {
				double distance = map.findDistance(street, ((RealEstate) n.getValue()).getStreet());
				if (distance <= radius && distance >= 0) {
					System.out.println(n.getValue() + String.format("At a distance of %f", distance));
				}
			}
		});
	}
	
	// Stores a request for a visit to a certain building under the name of certain client
	public void requestVisit(int buildingId, int clientId) {	//O(log(n))
		Client client = (Client)(clients.find(new Client("", "", clientId, false)));  	//Find the client
		PriorityTwoQueue propertyRequests = (PriorityTwoQueue) visitRequests.findValue(buildingId);
		if (propertyRequests == null) {
			propertyRequests = new PriorityTwoQueue();
			visitRequests.add(buildingId, propertyRequests);
		}
		propertyRequests.push(client.getName(), client.getVip());
	}
	
	/* Organises visits for buildings which have at least 5 requests.
	   Visits are organised in groups of five and VIP clients get priority above regular clients. */
	public void organizeVisits() {			//O(n*log(n))
		System.out.println("The organized visits are: ");
		visitRequests.traverseInOrder(new TreeAction() {  	//Traverse through each building in the tree
			public void run(Tree.TreeNode n)	{
				PriorityTwoQueue requests = (PriorityTwoQueue) ((DictionaryTree.DictionaryPair)n.getValue()).getValue();
				if(requests.size() > 4)	{
					System.out.println(buildings.find(new RealEstate("", 0, 0, 0, (int) ((DictionaryTree.DictionaryPair)n.getValue()).getKey())));											//Print the building																	
					int groupnumber = 1;
					while(requests.size() > 4) {	// We will keep organising visits for 5 people as long as there are more than 4 people remaining who have requested a visit
						System.out.println(String.format("Group %d: %s, %s, %s, %s and %s.", groupnumber, requests.pop(), requests.pop(), requests.pop(), requests.pop(), requests.pop()));
						groupnumber += 1;
					}
					System.out.println();
				}
			}
		});
	}
	
	// Adds a street to the database
	public void addStreet (String streetName) {
		map.addNode(streetName);
	}
	
	// Adds a connection between two streets to the database, takes a third parameter for distance between the two points.
	public void connectStreets(String street1, String street2, double distance) {
		map.addUndirectedEdge(street1, street2, distance);
	}

}