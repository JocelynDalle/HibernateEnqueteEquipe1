package fr.humanbooster.fx.enquetes.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Question implements Comparable<Question> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String wording;
	
	@ManyToOne
	private Survey survey;

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

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", wording=" + wording + "]";
	}

	@Override
	public int compareTo(Question o) {
		if(this.getId() == o.getId()) {
			return 0;
		}
		return this.getId() > o.getId() ? 1 : -1;
	}

}
