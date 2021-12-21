package greenfield.customer;

import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import greenfield.jms.DomainEventProvider;
import io.helidon.microprofile.config.ConfigCdiExtension;
import io.helidon.microprofile.server.JaxRsCdiExtension;
import io.helidon.microprofile.server.ServerCdiExtension;
import io.helidon.microprofile.tests.junit5.AddBean;
import io.helidon.microprofile.tests.junit5.AddExtension;
import io.helidon.microprofile.tests.junit5.DisableDiscovery;
import io.helidon.microprofile.tests.junit5.HelidonTest;

@HelidonTest
@DisableDiscovery
@AddBean(CustomerRepository.class)
@AddBean(CustomerResource.class)
@AddBean(DomainEventProvider.class)
@AddExtension(ServerCdiExtension.class)
@AddExtension(JaxRsCdiExtension.class)
@AddExtension(ConfigCdiExtension.class)
@AddExtension(CdiComponentProvider.class)
public class CustomerResourceTest {

	@Inject
	WebTarget webTarget;

	@Test
	public void test_getExisting() {
		Customer c = webTarget.path("api/customer/1").request().get(Customer.class);
		Assertions.assertNotNull(c);
	}
}
