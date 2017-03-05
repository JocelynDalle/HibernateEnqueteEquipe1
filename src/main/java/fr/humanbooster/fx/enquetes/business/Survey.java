package fr.humanbooster.fx.enquetes.business;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Survey implements Comparable<Survey> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	protected String name;
	protected float price;
	protected Date date;
	
	@Transient
	protected String formatDate;
	
	@OneToMany(mappedBy = "survey", fetch=FetchType.EAGER)
	protected Set<Criteria> setCriteria;

	@OneToMany(mappedBy="survey", fetch=FetchType.EAGER)
	protected List<Question> lsQuestion;
	
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

	public List<Question> getLsQuestion() {
		return lsQuestion;
	}

	public void setLsQuestion(List<Question> lsQuestion) {
		this.lsQuestion = lsQuestion;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", name=" + name + ", price=" + price + ", date=" + date + ", setCriteria="
				+ setCriteria + "]";
	}

	public String getFormatDate() {
		if(date == null) {
			Calendar cal = Calendar.getInstance();
			Date d = cal.getTime();
			return DateFormat.getDateInstance( DateFormat.MEDIUM ).format(d);
		}
		return DateFormat.getDateInstance( DateFormat.MEDIUM ).format(date);
	}

}
