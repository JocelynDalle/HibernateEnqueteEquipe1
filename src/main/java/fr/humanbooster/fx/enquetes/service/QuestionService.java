package fr.humanbooster.fx.enquetes.service;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;

public interface QuestionService {
	
	
	public Boolean deleteQuestion(String idQuestion);
	
	public Question addQuestionToSurvey(String wording, String idSurvey);
	
	public Question findQuestionById(String idQuestion);

	public Question modifyQuestion(String idQuestion, String wording);
}
