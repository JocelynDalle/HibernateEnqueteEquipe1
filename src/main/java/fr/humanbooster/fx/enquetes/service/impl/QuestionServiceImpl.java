package fr.humanbooster.fx.enquetes.service.impl;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.dao.QuestionDao;
import fr.humanbooster.fx.enquetes.dao.SurveyDao;
import fr.humanbooster.fx.enquetes.dao.impl.QuestionDaoImpl;
import fr.humanbooster.fx.enquetes.dao.impl.SurveyDaoImpl;
import fr.humanbooster.fx.enquetes.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

	private QuestionDao qDao = new QuestionDaoImpl();
	private SurveyDao sDao = new SurveyDaoImpl();

	@Override
	public Question modifyQuestion(String idQuestion, String wording) {
		qDao.openCurrentSessionWithTransaction();
		Question question = qDao.findById(Integer.parseInt(idQuestion));
		question.setWording(wording);
		Question q = qDao.updateQuestion(question);
		qDao.closeCurrentSessionwithTransaction();
		return q;
	}

	@Override
	public Boolean deleteQuestion(String idQuestion) {
		qDao.openCurrentSessionWithTransaction();
		boolean b = qDao.deleteQuestion(Integer.parseInt(idQuestion));
		qDao.closeCurrentSessionwithTransaction();
		return b;
	}

	@Override
	public Question addQuestionToSurvey(String wording, String idSurvey) {

		sDao.openCurrentSessionWithTransaction();
		Survey survey = sDao.findById(Integer.parseInt(idSurvey));
		sDao.closeCurrentSessionwithTransaction();

		Question question = new Question();
		question.setWording(wording);
		question.setSurvey(survey);

		qDao.openCurrentSessionWithTransaction();
		qDao.createQuestion(question);
		qDao.closeCurrentSessionwithTransaction();

		return question;
	}

	@Override
	public Question findQuestionById(String idQuestion) {
		int idQuestionInt = Integer.parseInt(idQuestion);
		qDao.openCurrentSessionWithTransaction();
		Question q = qDao.findById(idQuestionInt);
		qDao.closeCurrentSessionwithTransaction();
		return q;
	}

}
