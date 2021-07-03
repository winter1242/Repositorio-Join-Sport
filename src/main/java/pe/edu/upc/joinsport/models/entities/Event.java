package pe.edu.upc.joinsport.models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name_event", length = 130, nullable = false)
	private String nameEvent;

	@Column(name = "direction", length = 250, nullable = false)
	private String direction;
	
	@Column(name = "photo", length = 250)
	private String photo;

	@ManyToOne
	@JoinColumn(name = "type_event_id")
	private TypeEvent typeEvent;

	@ManyToOne
	@JoinColumn(name = "sport_id")
	private Sport sport;

	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;

	@ManyToOne
	@JoinColumn(name = "typepay_id")
	private TypePay typepay;

	@ManyToOne
	@JoinColumn(name = "organizer_id")
	private Organizer organizer;

	@Column(name = "day_event_start", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date dayEventStart;

	@Column(name = "day_event_end", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date dayEventEnd;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	private List<EventUser> eventUsers;

	@Transient
	private Integer districtId;
	
	@Transient
	private Integer sportId;
	
	@Transient
	private Integer typeEventId;
	
	@Transient
	private Integer typePayId;
	
	@Transient
	private Integer organizerId;

	public Event() {
		eventUsers = new ArrayList<EventUser>();
		this.districtId = 0;
		this.sportId = 0;
		this.typeEventId = 0;
		this.typePayId = 0;
		this.organizerId = 0;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public TypeEvent getTypeEvent() {
		return typeEvent;
	}

	public Sport getSport() {
		return sport;
	}

	public District getDistrict() {
		return district;
	}

	public TypePay getTypepay() {
		return typepay;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public Date getDayEventStart() {
		return dayEventStart;
	}

	public void setDayEventStart(Date dayEventStart) {
		this.dayEventStart = dayEventStart;
	}

	public Date getDayEventEnd() {
		return dayEventEnd;
	}

	public void setDayEventEnd(Date dayEventEnd) {
		this.dayEventEnd = dayEventEnd;
	}

	public List<EventUser> getEventUser() {
		return eventUsers;
	}

	public void setEventUser(List<EventUser> eventUser) {
		this.eventUsers = eventUser;
	}
	
	//modificacion ORGANIZERID
	
	//hasta aqui
	
	//modificacion TYPEPAYID

	public void setTypepay(TypePay typepay) {
		this.typepay = typepay;
		if(this.typepay != null) {
			this.typePayId = this.typepay.getId();
		}
	}
	
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public Integer getTypePayId() {
		if (this.typePayId <= 0 && this.typepay != null) {
			this.typePayId = this.typepay.getId();
		}
		return typePayId;
	}

	public void setTypePayId(Integer typePayId) {
		this.typePayId = typePayId;
	}
	//hasta aqui
	
	//modificacion TYPEEVENTID
	public void setTypeEvent(TypeEvent typeEvent) {
		this.typeEvent = typeEvent;
		if(this.typeEvent != null) {
			this.typeEventId = this.typeEvent.getId();
		}
	}
	
	public Integer getTypeEventId() {
		if (this.typeEventId <= 0 && this.typeEvent != null) {
			this.typeEventId = this.typeEvent.getId();
		}
		return typeEventId;
	}

	public void setTypeEventId(Integer typeEventId) {
		this.typeEventId = typeEventId;
	}
	//hasta aqui
	
	//modificacion SPORTID
	public void setSport(Sport sport) {
		this.sport = sport;
		if(this.sport != null) {
			this.sportId = this.sport.getId();
		}
	}
	
	public Integer getSportId() {
		if (this.sportId <= 0 && this.sport != null) {
			this.sportId = this.sport.getId();
		}
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}
	
	//hasta aqui
	
	// modificacion DISTRICTID
	public void setDistrict(District district) {
		this.district = district;
		if (this.district != null) {
			this.districtId = this.district.getId();
		}
	}

	public Integer getDistrictId() {
		if (this.districtId <= 0 && this.district != null) {
			this.districtId = this.district.getId();
		}
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	// hasta aqui es la modificacion
}
