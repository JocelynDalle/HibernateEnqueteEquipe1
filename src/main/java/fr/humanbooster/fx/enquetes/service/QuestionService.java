package fr.humanbooster.fx.enquetes.service;

import fr.humanbooster.fx.enquetes.business.Question;

public interface QuestionService {
	
	public Question modifyQuestion(Question question);
	
	public Question deleteQuestion(Question question);
}
