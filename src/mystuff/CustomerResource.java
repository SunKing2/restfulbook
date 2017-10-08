package mystuff;
import java.net.URI;
import java.util.*;
import java.util.regex.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("customers")
public class CustomerResource {

	private Map<Integer, Customer> customerDB = new HashMap<>();
	private Integer currentId = 0;
	
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response createCustomer(String inputString) {

		System.out.println("POST /customers input:\n" + inputString);
		Customer customer = new Customer(++currentId);
		customerDB.put(currentId, customer);
		
		System.out.println("created:" + customer);
		
		String uriString = "/customer/" + currentId;
		Response response = Response.created(URI.create(uriString)).build();
		
		return response;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public String getCustomer(@PathParam("id") int id) {
		System.out.println("Get /customers/" + id + " input:\n");
		
		String sId = "" + id;
		String sLastName = "";
		
		Customer customer = retrieveCustomer(id);
		sLastName = customer.lastName;
		return "<customer><id>" + sId + "</id><lastName>" + sLastName + "</lastName></customer>";
	}

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateCustomer(@PathParam("id") int id, String inputString) {
		System.out.println("PUT /customers/\" + id + \" input:\n" + inputString);
		
		Customer customer = retrieveCustomer(id);
		
	    String newLastName = "!!!!error!!!!";
	    
	    try {
	        Pattern pattern1 = Pattern.compile("<lastName>(.*?)</lastName>");
	        Matcher matcher = pattern1.matcher(inputString);
	        matcher.find();
	        newLastName = matcher.group(1);
	    }
		catch (Exception exc) {
			System.out.println("PUT /customers/{id}:" + exc);
			//throw new WebApplicationException(Response.Status.NOT_FOUND);
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
	    customer.lastName = newLastName;
	    
        System.out.println("customer:" + customer);
	}

	private Customer retrieveCustomer(int id) throws WebApplicationException {
		
		// TODO putting customer into database in GET and PUT is baaaaddd!!!
		customerDB.put(id, new Customer(1));
		Customer customer = null;
		try {
			customer = customerDB.get(id);
		}
		catch (Exception exc) {
			System.out.println("GET /customers/{id}:" + exc);
			throw new WebApplicationException(Response.Status.NOT_FOUND); 
		}
		return customer;
	}
	
}
