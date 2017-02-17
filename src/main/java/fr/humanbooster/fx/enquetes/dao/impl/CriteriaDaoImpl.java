package fr.humanbooster.fx.enquetes.dao.impl;

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

import fr.humanbooster.fx.enquetes.business.Criteria;
import fr.humanbooster.fx.enquetes.dao.CriteriaDao;

public class CriteriaDaoImpl implements CriteriaDao {

	private Session session;
	private Transaction transaction;

	// Create
	@Override
	public Criteria createCriteria(Criteria criteria) {
		session.save(criteria);
		return criteria;
	}

	// Update
	@Override
	public Criteria updateCriteria(Criteria criteria) {
		session.saveOrUpdate(criteria);
		return criteria;
	}

	// Delete
	@Override
	public boolean deleteCriteria(int idCriteria) {
		Criteria criteria = this.findById(idCriteria);
		if (criteria == null)
			return false;
		session.delete(criteria);
		return true;
	}

	@Override
	public Criteria findById(int idCriteria) {

		return session.byId(Criteria.class).load(idCriteria);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Criteria> findAll() {
		List<Criteria> lsCriteria = session.createQuery("from Criteria").getResultList();
		Set<Criteria> setCriteria = new TreeSet<Criteria>();
		setCriteria.addAll(lsCriteria);
		return setCriteria;

	}

	@Override

	public Session openCurrentSession() {
		session = getSessionFactory().openSession();
		return session;

	}

	@Override

	public Session openCurrentSessionWithTransaction() {
		session = getSessionFactory().openSession();
		transaction = session.beginTransaction();
		return session;
	}

	@Override

	public void closeCurrentSession() {
		session.close();
	}

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

