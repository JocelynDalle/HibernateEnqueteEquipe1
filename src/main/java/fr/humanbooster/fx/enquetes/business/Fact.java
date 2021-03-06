package fr.humanbooster.fx.enquetes.business;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fact implements Comparable<Fact> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String wording;
	
	@OneToMany(mappedBy="fact", fetch=FetchType.EAGER)
	private Set<Criteria> lsCriteria;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getWording() {
		return wording;
	}
	
	public void setWording(String wording) {
		this.wording = wording;
	}
	
	@Override
	public String toString() {
		return "Fact [id=" + id + ", wording=" + wording + "]";
	}
	
	@Override
	public int compareTo(Fact o) {
		if(this.getId() == o.getId()) {
			return 0;
		}
		return this.getId() > o.getId() ? 1 : -1;
	}
}
