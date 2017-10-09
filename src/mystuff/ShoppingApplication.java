package mystuff;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;

@ApplicationPath("/services")
public class ShoppingApplication extends Application{
	
	private Set<Object> singletons = new HashSet<>();
	private	Set<Class<?>> empty = new HashSet<Class<?>>();

	public ShoppingApplication() {
		singletons.add(new CustomerResourceService());
		singletons.add(new Timezone());
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
