
public class Main {

	public static void main(String[] args) 
	{

		/*	
		Graph myGraph = new Graph();
		myGraph.addNode("A");
		myGraph.addNode("B");
		myGraph.addNode("C");
		myGraph.addNode("D");
		myGraph.addNode("E");
		myGraph.addNode("F");
		myGraph.addNode("G");
		myGraph.addNode("H");
		myGraph.addEdge("A", "C", 1);
		myGraph.addEdge("A", "H", 24);
		myGraph.addEdge("B", "D", 3);
		myGraph.addEdge("B", "H", 5);
		myGraph.addEdge("C", "G", 7);
		myGraph.addEdge("C", "E", 2);
		myGraph.addEdge("E", "A", 3);
		myGraph.addEdge("G", "B", 10);
		myGraph.addEdge("G", "D", 7);
		Vector path = myGraph.findPath("A", "H", false);
		path.print();
		System.out.println(myGraph.findDistance(path));
		System.out.println(myGraph.findDistance("A", "H"));
		System.out.println(myGraph.findPath("A", "H", true));

		Tree myTree = new Tree();
		for(int i= 1; i<10000; i++) {
			if(i%2 == 0) {
				if(i%6==0) {
					myTree.insert(i-1);
				}
				else {
					myTree.insert(i);
				}
			}
			else {
				if(i%3==0) {
					myTree.insert(i);
				}
				else {
					myTree.insert(-i);
				}
			}
		}
		System.out.println(myTree.findMax());
		System.out.println(myTree.findDepth());
		
			
		ManagementSystem mySystem = new ManagementSystem();
		mySystem.addStreet("1st Street");
		mySystem.addStreet("2nd Street");
		mySystem.addStreet("3rd Street");
		mySystem.addStreet("4th Street");
		mySystem.addStreet("5th Street");
		mySystem.addStreet("6th Street");
		mySystem.addStreet("7th Street");
		mySystem.addStreet("8th Street");
		
		mySystem.connectStreets("1st Street", "3rd Street", 1);
		mySystem.connectStreets("1st Street", "8th Street", 24);
		mySystem.connectStreets("2nd Street", "4th Street", 3);
		mySystem.connectStreets("2nd Street", "8th Street", 5);
		mySystem.connectStreets("3rd Street", "7th Street", 7);
		mySystem.connectStreets("3rd Street", "5th Street", 2);
		mySystem.connectStreets("1st Street", "5th Street", 3);
		mySystem.connectStreets("7th Street", "2nd Street", 6);
		mySystem.connectStreets("7th Street", "4th Street", 5);
		
		mySystem.addNewHouse("1st Street", 2, 2, 350000);
		mySystem.addNewHouse("2nd Street", 6, 4, 10000000);
		mySystem.addNewHouse("3rd Street", 1, 1, 250000);
		mySystem.addNewHouse("4th Street", 6, 4, 1050000);
		mySystem.addNewHouse("5th Street", 2, 1, 400000);
		
		mySystem.addNewApartment("1st Street", 2, 2, 300000, false, 3, 4);
		mySystem.addNewApartment("6th Street", 1, 1, 200000, true, 1, 7);
		mySystem.addNewApartment("7th Street", 3, 2, 500000, true, 2, 5);
		mySystem.addNewApartment("8th Street", 4, 3, 700000, true, 8, 9);
		mySystem.addNewApartment("8th Street", 5, 5, 1200000, true, 12, 12);
		
		mySystem.addRegularClient("John Snow", "BlackCastle@GOT.hbo");
		mySystem.addRegularClient("Connor MacManus", "Boondock1@Saints.com");
		mySystem.addRegularClient("Murphy MacManus", "Boondock2@Saints.com");
		mySystem.addRegularClient("Marlon Brando", "Sleeping@TheFishes.com");
		mySystem.addRegularClient("R2-D2", "beep@boop.com");
		mySystem.addRegularClient("King Filip", "Filip@King.be");
		
		mySystem.addVIPClient("Bill Gates", "HasALotOf@Money.com");
		mySystem.addVIPClient("Elon Musk", "GoingTo@Space.com");
		mySystem.addVIPClient("C-3PO", "WhyIsMyRightLeg@Silver.com");
		mySystem.addVIPClient("Elijah Wood", "CaresFor@TheRingAndWilfred.com");
		mySystem.addVIPClient("Gandalf", "Grey@White.com");
		mySystem.addVIPClient("Mel Gibson", "HasALotOf@Kids.com");
		
		mySystem.printApartments();
		System.out.println();
		
		mySystem.printHouses();
		System.out.println();
		
		mySystem.printRegularClients();
		System.out.println();
		
		mySystem.printVIPClient();
		System.out.println();
		
		mySystem.searchOnPrice(250000, 500000);
		System.out.println();
		
		mySystem.searchOnBedroomsAndPrice(2, 500000);
		System.out.println();
		
		mySystem.searchOnDistance("1st Street", 7);
		System.out.println();
		
		mySystem.requestVisit(3, 7);
		mySystem.requestVisit(2, 1);
		mySystem.requestVisit(6, 10);
		mySystem.requestVisit(2, 4);
		mySystem.requestVisit(6, 1);
		mySystem.requestVisit(2, 8);
		mySystem.requestVisit(2, 9);
		mySystem.requestVisit(6, 8);
		mySystem.requestVisit(2, 2);
		mySystem.requestVisit(6, 5);
		mySystem.requestVisit(2, 10);
		mySystem.requestVisit(6, 7);
		mySystem.requestVisit(6, 9);
		mySystem.requestVisit(6, 2);
		mySystem.requestVisit(6, 3);
		mySystem.requestVisit(6, 4);
		mySystem.requestVisit(6, 6);

		mySystem.organizeVisits();
		mySystem.organizeVisits();
*/	
		LinkedList test = new LinkedList();
		int i = 0;
		while(i<10) {
			test.addFirst(i++);
		}
		test.fropple2();
		test.print();
	}
}
