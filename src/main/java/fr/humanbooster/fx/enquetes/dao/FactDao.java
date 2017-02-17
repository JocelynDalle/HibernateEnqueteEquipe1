package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Fact;

public interface FactDao {

	Fact createFact(Fact fact);

	Fact updateFact(Fact fact);

	boolean deleteFact(int idFact);

	Fact findById(int idFact);

	Set<Fact> findAll();

	Session openCurrentSession();

	Session openCurrentSessionWithTransaction();

	void closeCurrentSession();

	void closeCurrentSessionwithTransaction();

}
