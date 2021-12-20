package greenfield.jms;

import java.util.UUID;
import java.util.concurrent.SubmissionPublisher;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;

import org.apache.johnzon.jsonb.JohnzonBuilder;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.ProcessorBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.FlowAdapters;
import org.reactivestreams.Publisher;

import io.helidon.common.reactive.Multi;
import io.helidon.messaging.connectors.jms.JmsMessage;

@ApplicationScoped
public class DomainEventProvider {

	private final Jsonb jsonBuilder = new JohnzonBuilder().build();
	private final SubmissionPublisher<DomainEvent> emitter = new SubmissionPublisher<>();

	public void send(final DomainEvent event) {
		emitter.submit(event);
	}

	@Outgoing("raw-domain-events")
	public Publisher<DomainEvent> preparePublisher() {
		return ReactiveStreams.fromPublisher(FlowAdapters.toPublisher(Multi.create(emitter))).buildRs();
	}

	@Incoming("raw-domain-events")
	@Outgoing("domain-events")
	public ProcessorBuilder<DomainEvent, Object> process() {
		return ReactiveStreams.<DomainEvent>builder()
				.map(event -> JmsMessage.builder(jsonBuilder.toJson(event))
						.correlationId(UUID.randomUUID().toString())
						.build());
	}
}
