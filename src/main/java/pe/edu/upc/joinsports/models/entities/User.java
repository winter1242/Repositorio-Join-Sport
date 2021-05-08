package pe.edu.upc.joinsports.models.entities;

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

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name_user", length = 30, nullable = false)
	private String nameUser;

	@Column(name = "surname_user", length = 50, nullable = false)
	private String surnameUser;
	
	@Column(name = "birthday", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(name = "sex_user", nullable = false)
	private Boolean sexUser;
	
	@Column(name = "dni_user", length = 8, nullable = false)
	private String dniUser;
	
	@Column(name = "user_competitor", length = 20, nullable = false)
	private String userCompetitor;
	
	@Column(name = "password_competitor", length = 10, nullable = false)
	private String passwordCompetitor;
	
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<EventUser> eventUsers;
	
	@Transient
	private Integer districtId;
	
	public User() {
		eventUsers = new ArrayList<EventUser>();
		this.districtId = 0;
	}

	public String getDniUser() {
		return dniUser;
	}

	public void setDniUser(String dniUser) {
		this.dniUser = dniUser;
	}

	public List<EventUser> getEventUsers() {
		return eventUsers;
	}

	public void setEventUsers(List<EventUser> eventUsers) {
		this.eventUsers = eventUsers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getSurnameUser() {
		return surnameUser;
	}

	public void setSurnameUser(String surnameUser) {
		this.surnameUser = surnameUser;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getSexUser() {
		return sexUser;
	}

	public void setSexUser(Boolean sexUser) {
		this.sexUser = sexUser;
	}

	public String getUserCompetitor() {
		return userCompetitor;
	}

	public void setUserCompetitor(String userCompetitor) {
		this.userCompetitor = userCompetitor;
	}

	public String getPasswordCompetitor() {
		return passwordCompetitor;
	}

	public void setPasswordCompetitor(String passwordCompetitor) {
		this.passwordCompetitor = passwordCompetitor;
	}

	public District getDistrict() {
		return district;
	}
	//modificacion DISTRICTID
	public void setDistrict(District district) {
		this.district = district;
		if(this.district != null) {
			this.districtId = this.district.getId();
		}
	}
	
	public Integer getDistrictId() {
		if(this.districtId <= 0 && this.district != null) {
			this.districtId = this.district.getId();
		}
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	//hasta aqui es la modificacion
	public List<EventUser> getEventUser() {
		return eventUsers;
	}

	public void setEventUser(List<EventUser> eventUser) {
		this.eventUsers = eventUser;
	}
	
}
