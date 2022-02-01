package greenfield.customer;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@ApplicationScoped
@Path("/api/customer")
public class CustomerResource {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

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

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getName(@PathParam("id") Integer id) {
		return Response.ok(customers.get(id)).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/app.vnd.bauformat.extended")
	public Response getExtended(@HeaderParam("accept") String acceptMediaType, @PathParam("id") Integer id) {
		logger.info("Accepted media type: " + acceptMediaType);

		return Response.ok(customers.get(id)).build();
	}
}
