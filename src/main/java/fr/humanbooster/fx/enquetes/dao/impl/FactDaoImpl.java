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

import fr.humanbooster.fx.enquetes.business.Fact;
import fr.humanbooster.fx.enquetes.dao.FactDao;

@Repository
public class FactDaoImpl implements FactDao {
	
	@Autowired
	private SessionFactory sf;

	// Create
	@Override
	public Fact createFact(Fact fact) {
		sf.getCurrentSession().save(fact);
		return fact;
	}

	// Update
	@Override
	public Fact updateFact(Fact fact) {
		sf.getCurrentSession().saveOrUpdate(fact);
		return fact;
	}

	// Delete
	@Override
	public boolean deleteFact(int idFact) {
		Fact fact = this.findById(idFact);
		if (fact == null)
			return false;
		sf.getCurrentSession().delete(fact);
		return true;
	}

	@Override
	public Fact findById(int idFact) {

		return sf.getCurrentSession().byId(Fact.class).load(idFact);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Fact> findAll() {
		List<Fact> lsFact = sf.getCurrentSession().createQuery("from Fact").getResultList();
		Set<Fact> setFact = new TreeSet<Fact>();
		setFact.addAll(lsFact);
		return setFact;

	}

}

