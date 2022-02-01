package greenfield.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import greenfield.jms.DomainEvent;
import greenfield.jms.DomainEventProvider;

@ApplicationScoped
public class CustomerRepository {

	@Inject
	DomainEventProvider domainEventProvider;

	private static Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

	private static void fillCustomers() {
		customers.put(1, new Customer(1, "Bugs Bunny"));
		customers.put(2, new Customer(2, "Duffy Duck"));
		customers.put(3, new Customer(3, "Yosemite Sam"));
	}

	static {
		fillCustomers();
	}

	public CustomerRepository() {
		customers.clear();
		fillCustomers();
	}

	public Customer get(Integer id) {
		return customers.get(id);
	}

	public Collection<Customer> values() {
		return customers.values();
	}

	public void remove(Integer id) {
		Customer c = customers.get(id);
		customers.remove(id);

		if (c != null) {
			domainEventProvider.send(new DomainEvent(id, c.name));
		}
	}
}
