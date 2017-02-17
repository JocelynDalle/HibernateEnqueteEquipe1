package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Survey;

public interface SurveyDao {

	Survey createSurvey(Survey survey);

	Survey updateSurvey(Survey survey);

	boolean deleteSurvey(int idSurvey);

	Survey findById(int idSurvey);

	void closeCurrentSessionwithTransaction();

	void closeCurrentSession();

	Session openCurrentSessionWithTransaction();

	Session openCurrentSession();

	Set<Survey> findAll();

}
