package mystuff;
 
import javax.ws.rs.*;
 
@Path("/timezone")
public class Timezone {
 
    @GET
    @Produces("text/plain")
    public String timezone() {
        return "time to party";
    }
 
    @Path("{myverb}")
    @GET
    @Produces("application/json")
    public String myfunction(@PathParam("myverb") String myverb) {
        return "{\"time\" : \"" + myverb + "\"}";
    }
}