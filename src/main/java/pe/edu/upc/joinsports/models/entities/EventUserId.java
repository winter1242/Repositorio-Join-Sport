package pe.edu.upc.joinsports.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class EventUserId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer user;
	private Integer event;

	public EventUserId() {
		super();
	}

	// Constructor
	public EventUserId(Integer user, Integer event) {
		super();
		this.user = user;
		this.event = event;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, event);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		EventUserId eventUserId = (EventUserId) obj;

		if (this.user != eventUserId.user)
			return false;
		if (this.event != eventUserId.event)
			return false;
		return true;
	}
}
