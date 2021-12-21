package greenfield.jms;

public class DomainEvent {

	public Integer id;
	public String value;

	public DomainEvent() {

	}

	public DomainEvent(Integer id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	@Override
	public String toString() {
		return id + " " + value;
	}
}
