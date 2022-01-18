package greenfield.customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@ApplicationScoped
@Path("/api/customer")
public class CustomerResource {

	@Inject
	CustomerRepository customers;

	@GET
	@Path("{id}")
	@Operation(description = "Get customer")
	@APIResponse(responseCode = "200", description = "Customer object")
	@APIResponse(responseCode = "404", description = "Customer not found")
	public Customer get(@Parameter(description = "Kundennumnmer", required = true) @PathParam("id") Integer id) {
		return customers.get(id);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		customers.remove(id);
	}

}
