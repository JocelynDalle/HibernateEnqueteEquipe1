package fr.humanbooster.fx.enquetes.service;

import java.util.Set;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;

public interface SurveyService {

	public Survey modifySurvey(Survey survey);
	
	public Question addQuestionToSurvey(Question question, Survey survey);
	
	public Boolean deleteSurvey(Survey survey);

	public Set<Survey> findAllSurvey();
}
