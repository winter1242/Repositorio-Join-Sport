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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "organizers")
public class Organizer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name_organizer", length = 130, nullable = false)
	private String nameOrganizer;

	@Column(name = "surname_organizer", length = 150, nullable = false)
	private String surnameOrganizer;
	
	@Column(name = "birthday", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date birthday;
	
	@Column(name = "ruc", length = 11, nullable = true)
	private Integer ruc;
	
	@Column(name = "dni_organizer", length = 8, nullable = false)
	private String dniOrganizer;
	
	@Column(name = "organization", length = 130, nullable = true)
	private String organization;
	
	@Column(name = "user_organizer", length = 20, nullable = false)
	private String userOrganizer;
	
	@Column(name = "password_organizer", length = 10, nullable = false)
	private String passwordOrganizer;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Primaryuser primaryuser;
	
	@OneToMany(mappedBy = "organizer", fetch = FetchType.LAZY)
	private List<Event> event;
	
	//@OneToOne(mappedBy = "organizer")
	//private Primaryuser primaryuser;
	
	public Organizer() {
		event = new ArrayList<Event>();
		primaryuser = new Primaryuser();
	}

	public Primaryuser getPrimaryuser() {
		return primaryuser;
	}

	public void setPrimaryuser(Primaryuser primaryuser) {
		this.primaryuser = primaryuser;
	}

	public String getDniOrganizer() {
		return dniOrganizer;
	}

	public void setDniOrganizer(String dniOrganizer) {
		this.dniOrganizer = dniOrganizer;
	}
/*
	public String getDni() {
		return dniOrganizer;
	}

	public void setDni(String dniOrganizer) {
		this.dniOrganizer = dniOrganizer;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameOrganizer() {
		return nameOrganizer;
	}

	public void setNameOrganizer(String nameOrganizer) {
		this.nameOrganizer = nameOrganizer;
	}

	public String getSurnameOrganizer() {
		return surnameOrganizer;
	}

	public void setSurnameOrganizer(String surnameOrganizer) {
		this.surnameOrganizer = surnameOrganizer;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getRuc() {
		return ruc;
	}

	public void setRuc(Integer ruc) {
		this.ruc = ruc;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getUserOrganizer() {
		return userOrganizer;
	}

	public void setUserOrganizer(String userOrganizer) {
		this.userOrganizer = userOrganizer;
	}

	public String getPasswordOrganizer() {
		return passwordOrganizer;
	}

	public void setPasswordOrganizer(String passwordOrganizer) {
		this.passwordOrganizer = passwordOrganizer;
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}
/*
	public Primaryuser getPrimaryuser() {
		return primaryuser;
	}

	public void setPrimaryuser(Primaryuser primaryuser) {
		this.primaryuser = primaryuser;
	}
	*/
	
}
