package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Criteria;

public interface CriteriaDao {

	public Criteria createCriteria(Criteria criteria);

	public Criteria updateCriteria(Criteria criteria);

	public boolean deleteCriteria(int idCriteria);

	public Criteria findById(int idCriteria);

	public Set<Criteria> findAll();

	public Session openCurrentSession();

	public Session openCurrentSessionWithTransaction();

	public void closeCurrentSession();

	public void closeCurrentSessionwithTransaction();

}
