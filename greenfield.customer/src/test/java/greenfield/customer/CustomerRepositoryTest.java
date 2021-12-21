package greenfield.customer;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import greenfield.jms.DomainEventProvider;
import io.helidon.microprofile.tests.junit5.AddBean;
import io.helidon.microprofile.tests.junit5.DisableDiscovery;
import io.helidon.microprofile.tests.junit5.HelidonTest;

@HelidonTest
@DisableDiscovery
@AddBean(CustomerRepository.class)
@AddBean(DomainEventProvider.class)
public class CustomerRepositoryTest {

	@Inject
	CustomerRepository customers;

	@Test
	public void test_getExisting() {
		Customer c = customers.get(1);
		Assertions.assertEquals(1, c.id);
		Assertions.assertEquals("Bugs Bunny", c.name);
	}

	@Test
	public void test_getNonExisting() {
		Customer c = customers.get(100);
		Assertions.assertNull(c);
	}
}
