package greenfield.test;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

// @LoginConfig(authMethod = "MP-JWT", realmName = "extranet")
@ApplicationPath("/api")
@OpenAPIDefinition(info = @Info(title = "Greenfield API", version = "1.0.0", description = "Helidon Evaluation Project"))
@ApplicationScoped
public class TestApplication extends Application {

}
