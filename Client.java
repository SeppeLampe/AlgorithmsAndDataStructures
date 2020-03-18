import java.util.Comparator;
/* This class represents clients and holds their attributes (name, email, id and VIP status).
 * The id is unique for each Client and if the client is a vip, the boolean vip will be true, for regular clients this is false.
 * Author: Seppe Lampe
 */
public class Client implements Comparable{
	private String name;
	private String emailAddress;
	private int id;
	private boolean vip;
	
	public Client(String name, String emailAddress, int id, boolean vip) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.id = id;
		this.vip = vip;
	}
		
	public String getName() {								//O(1)
		return name;
	}

	public String getEmailAddress() {						//O(1)
		return emailAddress;
	}

	public int getId() {									//O(1)
		return id;
	}

	public boolean getVip() {								//O(1)
		return vip;
	}

	// This method returns a String with the name, id and emailAddress of the client along with its VIP status
	public String toString() {								//O(1)
		String isVip = "";
		if(!vip) {
			isVip = " not";
		}
		return String.format("%s with id%d and his/her email address is %s. They are%s a VIP client", name, id, emailAddress, isVip);
	}
	
	@Override
	public int compareTo(Object person) {					//O(1)
		return id - ((Client)person).getId();				// Comparing is done based on id since this is unique.
	}
}
