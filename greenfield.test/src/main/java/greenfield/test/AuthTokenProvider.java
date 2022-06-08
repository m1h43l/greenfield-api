package greenfield.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AuthTokenProvider {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@Inject
	@ConfigProperty(name = "client.id")
	private String clientId;

	@Inject
	@ConfigProperty(name = "client.secret")
	private String clientSecret;

	@Inject
	@ConfigProperty(name = "oauth.url")
	private String url;

	@Produces
	@Named("authToken")
	public String provideToken() {
		Client client = ClientBuilder.newClient();
		Form form = new Form();
		form.param("client_id", clientId)
				.param("client_secret", clientSecret)
				.param("grant_type", "client_credentials");
		Entity<Form> entity = Entity.form(form);
		Response response = client.target(url).request(MediaType.APPLICATION_JSON).post(entity);
		if (response.getStatus() != 200) {
			logger.log(Level.FINER, "Response " + url + ": " + response.getStatus());
		}
		String token = response.readEntity(JsonObject.class).getString("access_token");
		return token;
	}

}