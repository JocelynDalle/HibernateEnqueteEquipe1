package fr.humanbooster.fx.enquetes.service;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;

public interface QuestionService {
	
	public Question modifyQuestion(Question question);
	
	public Boolean deleteQuestion(int idQuestion);
	
	public Question addQuestionToSurvey(Question question, Survey survey);
}
