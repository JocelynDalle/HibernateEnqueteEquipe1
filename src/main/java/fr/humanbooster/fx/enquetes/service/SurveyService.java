package fr.humanbooster.fx.enquetes.service;

import java.util.Set;

import fr.humanbooster.fx.enquetes.business.Survey;

public interface SurveyService {

	public Survey modifySurvey(Survey survey);
	
	public Boolean deleteSurvey(int idSurvey);
	
	public Set<Survey> findAllSurvey();
	
	public Survey findById(int idSurvey);
}
