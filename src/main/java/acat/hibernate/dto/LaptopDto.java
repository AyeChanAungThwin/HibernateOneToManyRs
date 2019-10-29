package acat.hibernate.dto;

import java.io.Serializable;

import acat.hibernate.entity.BaseEntity;
import acat.hibernate.entity.Laptop;
import acat.hibernate.entity.Person;

public class LaptopDto extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2142712284820067232L;
	
	private String brand;
	
	private String description;
	
	private Person person;
	
	public LaptopDto(Laptop laptop) {
		if (laptop!=null) {
			super.setId(laptop.getId());
			this.brand = laptop.getBrand();
			this.description = laptop.getDescription();
		}
	}

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
