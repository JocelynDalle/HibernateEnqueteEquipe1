package fr.humanbooster.fx.enquetes.business;

import javax.persistence.Entity;

@Entity
public class SurveyPhone extends Survey {
	
	private String script;

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
	
	

}
