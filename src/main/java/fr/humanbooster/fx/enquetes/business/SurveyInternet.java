package fr.humanbooster.fx.enquetes.business;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class SurveyInternet extends Survey {
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<PartnerSite> lsPartnerSite;
	
	public SurveyInternet() {
		super();
	}
	
	public SurveyInternet(String name, float price) {
		super();
		super.setName(name);
		super.setPrice(price);
	}
	
	public SurveyInternet(int id, String name, float price) {
		super();
		super.setId(id);
		super.setName(name);
		super.setPrice(price);
	}	

	public Set<PartnerSite> getLsPartnerSite() {
		return lsPartnerSite;
	}

	public void setLsPartnerSite(Set<PartnerSite> lsPartnerSite) {
		this.lsPartnerSite = lsPartnerSite;
	}

	@Override
	public int compareTo(Survey o) {
		return this.getId() > o.getId() ? 1 : -1;
	}



}
