package pe.edu.upc.joinsport.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pe.edu.upc.joinsport.utils.Segmento;

@Entity
@Table(name = "primeryusers"/*, indexes = { @Index(columnList = "username", name = "primaryuser_index_username") }*/)
public class Primaryuser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", length = 30, nullable = false)
	private String username;

	@Column(name = "password", length = 60, nullable = false)
	private String password;
	
	@OneToOne(mappedBy = "primaryuser")
	private Organizer organizer;
	
	@OneToOne(mappedBy = "primaryuser")
	private User user;

	@Column(name = "enable")
	private boolean enable;
	
	@Column(name = "segmento", nullable = false)
	private Segmento segmento; 
	
	@Column(name = "id_segmento", nullable = false)
	private Integer idSegmento;
/*
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "id")
	private Organizer organizer;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "id")
	private User user;
*/
	@OneToMany(mappedBy = "primaryuser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Authority> authorities;

	public Primaryuser() {
		this.enable = true;
		this.authorities = new ArrayList<>();
	}

	public Primaryuser(String username, String password) {
		this.username = username;
		this.password = password;
		this.enable = true;
		this.authorities = new ArrayList<>();
	}
/*
	// Construct that receive the organizer
	public Primaryuser(String username, String password, Organizer organizer) {
		this.id = organizer.getId();
		this.username = username;
		this.password = password;
		this.enable = true;
		this.organizer = organizer;
		this.authorities = new ArrayList<>();
		organizer.setPrimaryuser(this);
	}

	// Construct that receive the user (participante)
	public Primaryuser(String username, String password, User user) {
		this.id = user.getId();
		this.username = username;
		this.password = password;
		this.enable = true;
		this.user = user;
		this.authorities = new ArrayList<>();
		user.setPrimaryuser(this);
	}
*/
	// Add ROLE or ACCESS to user
	public void addAuthority(String auth) {
		Authority authority = new Authority();
		authority.setAuthority(auth);
		authority.setPrimaryuser(this);

		this.authorities.add(authority);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
/*
	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
	
	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public Integer getIdSegmento() {
		return idSegmento;
	}

	public void setIdSegmento(Integer idSegmento) {
		this.idSegmento = idSegmento;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}
