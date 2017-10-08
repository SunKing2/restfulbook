package mystuff;
import java.net.URI;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("customers")
public class CustomerResource {

	private Map<Integer, Customer> customerDB = new HashMap<>();
	private Integer id = 0;
	
	@GET
	@Produces("application/xml")
	public Response createCustomer() {
		
		Customer customer = new Customer(++id);
		customerDB.put(id, customer);
		
		System.out.println("created:" + customer);
		
		String uriString = "/customer/" + id;
		Response response = Response.created(URI.create(uriString)).build();
		
		return response;
	}
}
