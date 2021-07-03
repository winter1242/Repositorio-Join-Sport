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
@Table(name = "sports")
public class Sport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name_sport", length = 130, nullable = false)
	private String nameSport;
	
	@OneToMany(mappedBy = "sport", fetch = FetchType.LAZY)
	private List<Event> events;
	
	public Sport() {
		events = new ArrayList<Event>();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameSport() {
		return nameSport;
	}

	public void setNameSport(String nameSport) {
		this.nameSport = nameSport;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
