package mystuff;

public class Customer {

	public int id;
	public String lastName;
	
	public Customer(int id) {
		this.id = id;
		this.lastName = "Smith" + id;
	}
	
	public String toString() {
		return "" + id + "    " + lastName;
	}
}
