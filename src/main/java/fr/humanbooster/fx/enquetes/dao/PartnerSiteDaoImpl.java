package fr.humanbooster.fx.enquetes.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import fr.humanbooster.fx.enquetes.business.PartnerSite;
import fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao;

public class PartnerSiteDaoImpl implements PartnerSiteDao {

	private Session session;
	private Transaction transaction;

	// Create
	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#createPartnerSite(fr.humanbooster.fx.enquetes.business.PartnerSite)
	 */
	@Override
	public PartnerSite createPartnerSite(PartnerSite partnerSite) {
		session.save(partnerSite);
		return partnerSite;
	}

	// Update
	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#updatePartnerSite(fr.humanbooster.fx.enquetes.business.PartnerSite)
	 */
	@Override
	public PartnerSite updatePartnerSite(PartnerSite partnerSite) {
		session.saveOrUpdate(partnerSite);
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
		session.delete(partnerSite);
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#findById(int)
	 */
	@Override
	public PartnerSite findById(int idPartnerSite) {

		return session.byId(PartnerSite.class).load(idPartnerSite);
	}

	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Set<PartnerSite> findAll() {
		List<PartnerSite> lsPartnerSite = session.createQuery("from PartnerSite").getResultList();
		Set<PartnerSite> setPartnerSite = new TreeSet<PartnerSite>();
		setPartnerSite.addAll(lsPartnerSite);
		return setPartnerSite;

	}

	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#openCurrentSession()
	 */
	@Override

	public Session openCurrentSession() {
		session = getSessionFactory().openSession();
		return session;

	}

	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#openCurrentSessionWithTransaction()
	 */
	@Override

	public Session openCurrentSessionWithTransaction() {
		session = getSessionFactory().openSession();
		transaction = session.beginTransaction();
		return session;
	}

	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#closeCurrentSession()
	 */
	@Override

	public void closeCurrentSession() {
		session.close();
	}

	/* (non-Javadoc)
	 * @see fr.humanbooster.fx.enquetes.dao.impl.PartnerSiteDao#closeCurrentSessionwithTransaction()
	 */
	@Override

	public void closeCurrentSessionwithTransaction() {
		transaction.commit();
		session.close();

	}

	private SessionFactory getSessionFactory() {

		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml")
				.build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

		return sessionFactory;

	}

}

