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

	@Override
	public PartnerSite create(String name, String url) {
		pDao.openCurrentSessionWithTransaction();
		PartnerSite partnerSite = new PartnerSite();
		if(name != null) {
			partnerSite.setName(name);
		}
		if(url != null) {
			partnerSite.setUrl(url);
		}
		partnerSite = pDao.createPartnerSite(partnerSite);
		pDao.closeCurrentSessionwithTransaction();
		return partnerSite;
	}

	@Override
	public PartnerSite findById(int idPartnerSite) {
		pDao.openCurrentSessionWithTransaction();
		PartnerSite partnerSite = pDao.findById(idPartnerSite);
		pDao.closeCurrentSessionwithTransaction();
		return partnerSite;
	}

}
