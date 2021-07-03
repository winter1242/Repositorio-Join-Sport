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
@Table(name = "type_pays")
public class TypePay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name_type_pay", length = 130, nullable = false)
	private String nameTypePay;
	
	@Column(name = "quantity_user", nullable = true)
	private Integer quantityUser;
	
	@Column(name = "cost", nullable = true)
	private Float cost;
	
	@OneToMany(mappedBy = "typepay", fetch = FetchType.LAZY)
	private List<Event> event;

	public TypePay() {
		event = new ArrayList<Event>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameTypePay() {
		return nameTypePay;
	}

	public void setNameTypePay(String nameTypePay) {
		this.nameTypePay = nameTypePay;
	}

	public Integer getQuantityUser() {
		return quantityUser;
	}

	public void setQuantityUser(Integer quantityUser) {
		this.quantityUser = quantityUser;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public List<Event> getEvent() {
		return event;
	}

	public void setEvent(List<Event> event) {
		this.event = event;
	}
	
}
