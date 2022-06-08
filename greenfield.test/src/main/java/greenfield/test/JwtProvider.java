package greenfield.test;

import java.util.Set;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.json.JsonString;

import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
@Alternative
@Priority(1000)
public class JwtProvider {

	@Produces
	@Alternative
	public JsonWebToken providerJwt() {
		return new TestToken();
	}
}

class TestToken implements JsonWebToken {

	@Override
	public String getName() {
		return "Tester";
	}

	@Override
	public Set<String> getClaimNames() {
		return Set.of("name", "mail", "domain", "baseRole", "flags");
	}

	@Override
	public JsonString getClaim(String claimName) {
		switch (claimName) {
			case "name":
				return new TestString("Tester");
			case "mail":
				return new TestString("test@rpgnextgen.com");
			case "domain":
				return new TestString("rpgnextgen.com");
			case "baseRole":
				return new TestString("user");
		}

		return null;
	}

}

class TestString implements JsonString {

	private String s;

	public TestString(String s) {
		this.s = s;
	}

	@Override
	public ValueType getValueType() {
		return ValueType.STRING;
	}

	@Override
	public CharSequence getChars() {
		return s;
	}

	@Override
	public String getString() {
		return s;
	}

}
