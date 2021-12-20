package greenfield.ping;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("/api/ping")
public class PingResource {

	@GET
	public String ping() {
		return "pong (" + System.currentTimeMillis() + ")";
	}
}
