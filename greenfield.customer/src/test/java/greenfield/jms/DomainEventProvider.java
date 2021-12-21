package greenfield.jms;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;

import org.apache.johnzon.jsonb.JohnzonBuilder;

@ApplicationScoped
public class DomainEventProvider {

	private final Jsonb jsonBuilder = new JohnzonBuilder().build();

	public void send(final DomainEvent event) {
		System.out.println("Sending domain event: " + jsonBuilder.toJson(event));
	}

}
