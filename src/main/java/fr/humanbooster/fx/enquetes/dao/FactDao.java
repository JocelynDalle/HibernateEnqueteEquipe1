package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Fact;

public interface FactDao {

	public Fact createFact(Fact fact);

	public Fact updateFact(Fact fact);

	public boolean deleteFact(int idFact);

	public Fact findById(int idFact);

	public Set<Fact> findAll();

}
