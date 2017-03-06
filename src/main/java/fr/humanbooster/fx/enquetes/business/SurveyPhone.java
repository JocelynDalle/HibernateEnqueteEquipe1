package fr.humanbooster.fx.enquetes.business;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class SurveyPhone extends Survey {
	
	private String script;
	
	public SurveyPhone() {
		super();
	}
	
	public SurveyPhone(String name, float price, String script) {
		super();
		super.setName(name);
		super.setPrice(price);
		this.script = script;
	}
	
	public SurveyPhone(int id, String name, float price, String script) {
		super();
		super.setId(id);
		super.setName(name);
		super.setPrice(price);
		this.script = script;
	}	
	
	public SurveyPhone(String name, float price, String script, Date date) {
		super();
		super.setName(name);
		super.setPrice(price);
		super.setDate(date);
		this.script = script;
	}	
	
	public SurveyPhone(int id, String name, float price, String script, Date date) {
		super();
		super.setId(id);
		super.setName(name);
		super.setPrice(price);
		super.setDate(date);
		this.script = script;
	}	

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	
	@Override
	public String toString() {
		return "SurveyPhone [script=" + script + "]";
	}

	@Override
	public int compareTo(Survey o) {
		if(this.getId() == o.getId()) {
			return 0;
		}
		return this.getId() > o.getId() ? 1 : -1;
	}
	
}
