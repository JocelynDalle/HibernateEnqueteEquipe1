package fr.humanbooster.fx.enquetes.dao.impl;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.PartnerSite;

public interface PartnerSiteDao {

	// Create
	PartnerSite createPartnerSite(PartnerSite partnerSite);

	// Update
	PartnerSite updatePartnerSite(PartnerSite partnerSite);

	// Delete
	boolean deletePartnerSite(int idPartnerSite);

	PartnerSite findById(int idPartnerSite);

	Set<PartnerSite> findAll();

	Session openCurrentSession();

	Session openCurrentSessionWithTransaction();

	void closeCurrentSession();

	void closeCurrentSessionwithTransaction();

}