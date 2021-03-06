package fr.humanbooster.fx.enquetes.service;

import java.util.Set;

import fr.humanbooster.fx.enquetes.business.PartnerSite;

public interface PartenerSiteService {

	public Set<PartnerSite> findAll();
	
	public PartnerSite create(String name, String url);
	
	public PartnerSite findById(int id);
}
