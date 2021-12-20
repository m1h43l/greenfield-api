package greenfield.jms;

public class DomainEvent {

	public Long id;
	public String value;

	public DomainEvent() {

	}

	public DomainEvent(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	@Override
	public String toString() {
		return id + " " + value;
	}
}
