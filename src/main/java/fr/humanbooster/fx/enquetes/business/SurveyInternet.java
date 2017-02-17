package fr.humanbooster.fx.enquetes.business;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

public class SurveyInternet extends Survey {
	
	@ManyToMany (fetch = FetchType.EAGER)
	private Set<PartnerSite> setPartnerSite;

	public Set<PartnerSite> getSetPartnerSite() {
		return setPartnerSite;
	}

	public void setSetPartnerSite(Set<PartnerSite> setPartnerSite) {
		this.setPartnerSite = setPartnerSite;
	}
	
	

}
