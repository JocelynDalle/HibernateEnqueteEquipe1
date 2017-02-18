package fr.humanbooster.fx.enquetes.business;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class SurveyInternet extends Survey {
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<PartnerSite> lsPartnerSite;

	public Set<PartnerSite> getLsPartnerSite() {
		return lsPartnerSite;
	}

	public void setLsPartnerSite(Set<PartnerSite> lsPartnerSite) {
		this.lsPartnerSite = lsPartnerSite;
	}

	@Override
	public int compareTo(Survey o) {
		if(this.getId() == o.getId()) {
			return 0;
		}
		return this.getId() > o.getId() ? 1 : -1;
	}
}
