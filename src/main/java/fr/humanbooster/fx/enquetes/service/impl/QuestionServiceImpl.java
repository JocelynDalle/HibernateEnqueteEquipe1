package fr.humanbooster.fx.enquetes.service.impl;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.dao.QuestionDao;
import fr.humanbooster.fx.enquetes.dao.impl.QuestionDaoImpl;
import fr.humanbooster.fx.enquetes.service.QuestionService;

public class QuestionServiceImpl implements QuestionService{

	private QuestionDao qDao = new QuestionDaoImpl();
	
	@Override
	public Question modifyQuestion(Question question) {
		qDao.openCurrentSessionWithTransaction();
		Question q = qDao.updateQuestion(question);
		qDao.closeCurrentSessionwithTransaction();
		return q;
	}

	@Override
	public Boolean deleteQuestion(int idQuestion) {
		qDao.openCurrentSessionWithTransaction();
		Boolean b = qDao.deleteQuestion(idQuestion);
		qDao.closeCurrentSessionwithTransaction();
		return b;
	}

	@Override
	public Question addQuestionToSurvey(Question question, Survey survey) {
		Question q = new Question();
		return null;
	}

}
