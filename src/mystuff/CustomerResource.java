package mystuff;
import java.io.*;
import java.net.URI;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.commons.io.IOUtils;

@Path("customers")
public class CustomerResource {

	private Map<Integer, Customer> customerDB = new HashMap<>();
	private Integer id = 0;
	
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response createCustomer(InputStream is) {

		String inputString = "";
		try {
			inputString = IOUtils.toString(is, "UTF-8");
		}
		catch (Exception exc) {
			System.out.println("POST /customers:" + exc);
		}
		
		System.out.println("POST /customers XML input:\n" + inputString);
		Customer customer = new Customer(++id);
		customerDB.put(id, customer);
		
		System.out.println("created:" + customer);
		
		String uriString = "/customer/" + id;
		Response response = Response.created(URI.create(uriString)).build();
		
		return response;
	}
}
