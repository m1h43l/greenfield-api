package greenfield.jms;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/event")
@RequestScoped
public class DomainEventResource {

	@Inject
	private DomainEventProvider eventProvider;

	@GET
	public String triggerEvent() {
		eventProvider.send(buildDomainEvent());
		return String.valueOf(System.currentTimeMillis());
	}

	private DomainEvent buildDomainEvent() {
		return new DomainEvent((int) System.currentTimeMillis(), "My Name");
	}
}
