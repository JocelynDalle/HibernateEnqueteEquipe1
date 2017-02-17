package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Criteria;

public interface CriteriaDao {

	Criteria createCriteria(Criteria criteria);

	Criteria updateCriteria(Criteria criteria);

	boolean deleteCriteria(int idCriteria);

	Criteria findById(int idCriteria);

	Set<Criteria> findAll();

	Session openCurrentSession();

	Session openCurrentSessionWithTransaction();

	void closeCurrentSession();

	void closeCurrentSessionwithTransaction();

}
