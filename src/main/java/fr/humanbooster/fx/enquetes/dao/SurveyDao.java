package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Survey;

public interface SurveyDao {

	public Survey createSurvey(Survey survey);

	public Survey updateSurvey(Survey survey);

	public boolean deleteSurvey(int idSurvey);

	public Survey findById(int idSurvey);

	public Set<Survey> findAll();

}
