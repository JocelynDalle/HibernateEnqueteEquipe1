package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Survey;

public interface SurveyDao {

	public Survey createSurvey(Survey survey);

	public Survey updateSurvey(Survey survey);

	public boolean deleteSurvey(int idSurvey);

	public Survey findById(int idSurvey);

	public void closeCurrentSessionwithTransaction();

	public void closeCurrentSession();

	public Session openCurrentSessionWithTransaction();

	public Session openCurrentSession();

	public Set<Survey> findAll();

}
