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

import fr.humanbooster.fx.enquetes.business.Fact;
import fr.humanbooster.fx.enquetes.dao.FactDao;

public class FactDaoImpl implements FactDao {
	
	
	private Session session;
	private Transaction transaction;

	// Create
	@Override
	public Fact createFact(Fact fact) {
		session.save(fact);
		return fact;
	}

	// Update
	@Override
	public Fact updateFact(Fact fact) {
		session.saveOrUpdate(fact);
		return fact;
	}

	// Delete
	@Override
	public boolean deleteFact(int idFact) {
		Fact fact = this.findById(idFact);
		if (fact == null)
			return false;
		session.delete(fact);
		return true;
	}

	@Override
	public Fact findById(int idFact) {

		return session.byId(Fact.class).load(idFact);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Fact> findAll() {
		List<Fact> lsFact = session.createQuery("from Fact").getResultList();
		Set<Fact> setFact = new TreeSet<Fact>();
		setFact.addAll(lsFact);
		return setFact;

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

