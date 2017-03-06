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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.humanbooster.fx.enquetes.business.Criteria;
import fr.humanbooster.fx.enquetes.dao.CriteriaDao;

@Repository
public class CriteriaDaoImpl implements CriteriaDao {

	@Autowired
	private SessionFactory sf;

	// Create
	@Override
	public Criteria createCriteria(Criteria criteria) {
		sf.getCurrentSession().save(criteria);
		return criteria;
	}

	// Update
	@Override
	public Criteria updateCriteria(Criteria criteria) {
		sf.getCurrentSession().saveOrUpdate(criteria);
		return criteria;
	}

	// Delete
	@Override
	public boolean deleteCriteria(int idCriteria) {
		Criteria criteria = this.findById(idCriteria);
		if (criteria == null)
			return false;
		sf.getCurrentSession().delete(criteria);
		return true;
	}

	@Override
	public Criteria findById(int idCriteria) {

		return sf.getCurrentSession().byId(Criteria.class).load(idCriteria);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Criteria> findAll() {
		List<Criteria> lsCriteria = sf.getCurrentSession().createQuery("from Criteria").getResultList();
		Set<Criteria> setCriteria = new TreeSet<Criteria>();
		setCriteria.addAll(lsCriteria);
		return setCriteria;

	}

}

