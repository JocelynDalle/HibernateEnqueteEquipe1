package fr.humanbooster.fx.enquetes.service.impl;

import java.util.Set;

import fr.humanbooster.fx.enquetes.business.PartnerSite;
import fr.humanbooster.fx.enquetes.dao.PartnerSiteDao;
import fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDaoImpl;
import fr.humanbooster.fx.enquetes.service.PartenerSiteService;

public class PartenerSiteServiceImpl implements PartenerSiteService{

	private PartnerSiteDao pDao= new PartnerSiteDaoImpl();
	
	@Override
	public Set<PartnerSite> findAll() {
		pDao.openCurrentSessionWithTransaction();
		Set<PartnerSite> p = pDao.findAll();
		pDao.closeCurrentSessionwithTransaction();
		return p;
	}

}
