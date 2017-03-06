package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.PartnerSite;

public interface PartnerSiteDao {

	// Create
	public PartnerSite createPartnerSite(PartnerSite partnerSite);

	// Update
	public PartnerSite updatePartnerSite(PartnerSite partnerSite);

	// Delete
	public boolean deletePartnerSite(int idPartnerSite);

	public PartnerSite findById(int idPartnerSite);

	public Set<PartnerSite> findAll();

	public Session openCurrentSession();

	public Session openCurrentSessionWithTransaction();

	public void closeCurrentSession();

	public void closeCurrentSessionwithTransaction();

}