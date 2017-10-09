package mystuff;
 
import javax.ws.rs.*;
 
@Path("/timezone")
public class Timezone {
	
	private int time = 0;
 
    @GET
    @Produces("text/plain")
    public String timezone() {
        return "time to party: " + ++time + ":00";
    }
 
    @Path("{myverb}")
    @GET
    @Produces("application/json")
    public String myfunction(@PathParam("myverb") String myverb) {
        return "{\"time\" : \"" + myverb + "\"}";
    }
}