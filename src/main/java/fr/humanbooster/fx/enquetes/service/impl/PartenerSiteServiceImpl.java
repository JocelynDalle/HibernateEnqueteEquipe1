package fr.humanbooster.fx.enquetes.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.humanbooster.fx.enquetes.business.PartnerSite;
import fr.humanbooster.fx.enquetes.dao.PartnerSiteDao;
import fr.humanbooster.fx.enquetes.service.PartenerSiteService;

@Service
@Transactional
public class PartenerSiteServiceImpl implements PartenerSiteService{

	@Autowired
	private PartnerSiteDao pDao;
	
	@Override
	@Transactional(readOnly=true)
	public Set<PartnerSite> findAll() {
		Set<PartnerSite> p = pDao.findAll();
		return p;
	}

	@Override
	public PartnerSite create(String name, String url) {
		PartnerSite partnerSite = new PartnerSite();
		if(name != null) {
			partnerSite.setName(name);
		}
		if(url != null) {
			partnerSite.setUrl(url);
		}
		partnerSite = pDao.createPartnerSite(partnerSite);
		return partnerSite;
	}

	@Override
	@Transactional(readOnly=true)
	public PartnerSite findById(int idPartnerSite) {
		PartnerSite partnerSite = pDao.findById(idPartnerSite);
		return partnerSite;
	}

}
