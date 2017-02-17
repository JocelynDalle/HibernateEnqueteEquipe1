package fr.humanbooster.fx.enquetes.business;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Survey implements Comparable<Survey> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private String name;
	private float price;
	private Date date;
	@OneToMany(mappedBy = "survey")
	private Set<Criteria> setCriteria;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Criteria> getSetCriteria() {
		return setCriteria;
	}

	public void setSetCriteria(Set<Criteria> setCriteria) {
		this.setCriteria = setCriteria;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", name=" + name + ", price=" + price + ", date=" + date + ", setCriteria="
				+ setCriteria + "]";
	}



}
