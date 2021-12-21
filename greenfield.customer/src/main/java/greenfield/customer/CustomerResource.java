package greenfield.customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@ApplicationScoped
@Path("/api/customer")
public class CustomerResource {

	@Inject
	CustomerRepository customers;

	@GET
	@Path("{id}")
	public Customer get(@PathParam("id") Integer id) {
		return customers.get(id);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		customers.remove(id);
	}

}
