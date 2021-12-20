package greenfield.jms;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

@ApplicationScoped
public class NamedConnectionFactory {

	@Produces
	@Named("greenfield-jms-factory")
	public ConnectionFactory provide() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
}
