package fr.humanbooster.fx.enquetes.business;

import java.util.Date;
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

	public SurveyInternet(int id, String name, float price, Date date) {
		super();
		super.setId(id);
		super.setName(name);
		super.setPrice(price);
		super.setDate(date);
	}	
	
	public Set<PartnerSite> getLsPartnerSite() {
		return lsPartnerSite;
	}

	public void setLsPartnerSite(Set<PartnerSite> lsPartnerSite) {
		this.lsPartnerSite = lsPartnerSite;
	}
	

	@Override
	public String toString() {
		return "SurveyInternet [lsPartnerSite=" + lsPartnerSite + ", id=" + id + ", name=" + name + ", price=" + price
				+ ", date=" + date + ", formatDate=" + formatDate + ", setCriteria=" + setCriteria + ", lsQuestion="
				+ lsQuestion + "]";
	}

	@Override
	public int compareTo(Survey o) {
		if(this.getId() == o.getId()) {
			return 0;
		}
		return this.getId() > o.getId() ? 1 : -1;
	}
}
