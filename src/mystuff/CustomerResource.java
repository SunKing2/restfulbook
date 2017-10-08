package mystuff;
import java.net.URI;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("customers")
public class CustomerResource {

	private Map<Integer, Customer> customerDB = new HashMap<>();
	private Integer id = 0;
	
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response createCustomer(String inputString) {

		System.out.println("POST /customers input:\n" + inputString);
		Customer customer = new Customer(++id);
		customerDB.put(id, customer);
		
		System.out.println("created:" + customer);
		
		String uriString = "/customer/" + id;
		Response response = Response.created(URI.create(uriString)).build();
		
		return response;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public String getCustomer(@PathParam("id") int id) {
		String sId = "";
		String sLastName = "";
		
		// TODO putting customer into database in GET is baaaaddd!!!
		customerDB.put(1, new Customer(1));
		try {
			Customer customer = customerDB.get(id);
			sId = "" + customer.id;
			sLastName = customer.lastName;
		}
		catch (Exception exc) {
			System.out.println("GET /customers/{id}:" + exc);
			throw new WebApplicationException(Response.Status.NOT_FOUND); 
		}
		return "<customer><id>" + sId + "</id><lastName>" + sLastName + "</lastName></customer>";
	}
}
