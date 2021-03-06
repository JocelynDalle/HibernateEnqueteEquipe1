package fr.humanbooster.fx.enquetes.business;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

@Entity
public class Department implements Comparable<Department> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Length(max=3)
	private String number;
	
	@Length(max=50)
	private String name;
	
	@OneToMany(mappedBy="department", fetch=FetchType.EAGER)
	private Set<Criteria> lsCriteria;
	
	public Department(String number, String name) {
		this.number = number;
		this.name = name;
	}
	
	public Department() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", number=" + number + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Department o) {
		if(this.getId() == o.getId()) {
			return 0;
		}
		return this.getId() > o.getId() ? 1 : -1;
	}
	
}
