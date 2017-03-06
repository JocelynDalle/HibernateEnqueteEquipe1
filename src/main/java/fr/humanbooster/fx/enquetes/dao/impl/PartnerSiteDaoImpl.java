package fr.humanbooster.fx.enquetes.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.humanbooster.fx.enquetes.business.PartnerSite;
import fr.humanbooster.fx.enquetes.dao.PartnerSiteDao;

@Repository
public class PartnerSiteDaoImpl implements PartnerSiteDao {

	@Autowired
	private SessionFactory sf;

	// Create
	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#createPartnerSite(fr.humanbooster.fx.enquetes.business.PartnerSite)
	 */
	@Override
	public PartnerSite createPartnerSite(PartnerSite partnerSite) {
		sf.getCurrentSession().save(partnerSite);
		return partnerSite;
	}

	// Update
	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#updatePartnerSite(fr.humanbooster.fx.enquetes.business.PartnerSite)
	 */
	@Override
	public PartnerSite updatePartnerSite(PartnerSite partnerSite) {
		sf.getCurrentSession().saveOrUpdate(partnerSite);
		return partnerSite;
	}

	// Delete
	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#deletePartnerSite(int)
	 */
	@Override
	public boolean deletePartnerSite(int idPartnerSite) {
		PartnerSite partnerSite = this.findById(idPartnerSite);
		if (partnerSite == null)
			return false;
		sf.getCurrentSession().delete(partnerSite);
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#findById(int)
	 */
	@Override
	public PartnerSite findById(int idPartnerSite) {

		return sf.getCurrentSession().byId(PartnerSite.class).load(idPartnerSite);
	}

	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Set<PartnerSite> findAll() {
		List<PartnerSite> lsPartnerSite = sf.getCurrentSession().createQuery("from PartnerSite").getResultList();
		Set<PartnerSite> setPartnerSite = new TreeSet<PartnerSite>();
		setPartnerSite.addAll(lsPartnerSite);
		return setPartnerSite;

	}

}

