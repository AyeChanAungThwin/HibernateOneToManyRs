package acat.hibernate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "laptop")
public class Laptop extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2142712284820067232L;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Person person;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
