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

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.dao.SurveyDao;

@Repository
public class SurveyDaoImpl implements SurveyDao {

	@Autowired
	private SessionFactory sf;

	// Create
	@Override
	public Survey createSurvey(Survey survey) {
		sf.getCurrentSession().save(survey);
		return survey;
	}

	// Update
	@Override
	public Survey updateSurvey(Survey survey) {
		sf.getCurrentSession().saveOrUpdate(survey);
		return survey;
	}

	// Delete
	@Override
	public boolean deleteSurvey(int idSurvey) {
		Survey survey = this.findById(idSurvey);
		if (survey == null)
			return false;
		sf.getCurrentSession().delete(survey);
		return true;
	}

	@Override
	public Survey findById(int idSurvey) {

		return sf.getCurrentSession().byId(Survey.class).load(idSurvey);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Survey> findAll() {
		List<Survey> lsSurvey = sf.getCurrentSession().createQuery("from Survey").getResultList();
		Set<Survey> setSurvey = new TreeSet<Survey>();
		setSurvey.addAll(lsSurvey);
		return setSurvey;

	}

}
