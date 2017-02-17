package fr.humanbooster.fx.enquetes.service;

import java.util.Set;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.business.SurveyPhone;

public interface SurveyService {

	public Survey modifySurvey(Survey survey);
	
	public Boolean deleteSurvey(int idSurvey);
	
	public Set<Survey> findAllSurvey();
	
	public Survey findById(int idSurvey);
	
	public SurveyInternet createSurveyInternet(SurveyInternet surveyInternet);
	
	public SurveyPhone createSurveyPhone(SurveyPhone surveyPhone);
}
