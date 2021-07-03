package pe.edu.upc.joinsport.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "type_events")
public class TypeEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name_type_event", length = 130, nullable = false)
	private String nameTypeEvent;
	
	@OneToMany(mappedBy = "typeEvent", fetch = FetchType.LAZY)
	private List<Event> events;
	
	public TypeEvent() {
		events = new ArrayList<Event>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameTypeEvent() {
		return nameTypeEvent;
	}

	public void setNameTypeEvent(String nameTypeEvent) {
		this.nameTypeEvent = nameTypeEvent;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}
