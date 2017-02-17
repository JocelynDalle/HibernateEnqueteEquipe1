package fr.humanbooster.fx.enquetes.service;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;

public interface QuestionService {
	
	public Question modifyQuestion(Question question);
	
	public Boolean deleteQuestion(String idQuestion);
	
	public Question addQuestionToSurvey(String wording, String idSurvey);
}
