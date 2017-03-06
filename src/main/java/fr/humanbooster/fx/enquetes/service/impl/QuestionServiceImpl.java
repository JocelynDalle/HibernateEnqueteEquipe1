package fr.humanbooster.fx.enquetes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.dao.QuestionDao;
import fr.humanbooster.fx.enquetes.dao.SurveyDao;
import fr.humanbooster.fx.enquetes.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao qDao;
	@Autowired
	private SurveyDao sDao;

	@Override
	public Question modifyQuestion(String idQuestion, String wording) {
		Question question = qDao.findById(Integer.parseInt(idQuestion));
		question.setWording(wording);
		Question q = qDao.updateQuestion(question);
		return q;
	}

	@Override
	public Boolean deleteQuestion(String idQuestion) {
		boolean b = qDao.deleteQuestion(Integer.parseInt(idQuestion));
		return b;
	}

	@Override
	public Question addQuestionToSurvey(String wording, String idSurvey) {

		Survey survey = sDao.findById(Integer.parseInt(idSurvey));

		Question question = new Question();
		question.setWording(wording);
		question.setSurvey(survey);

		qDao.createQuestion(question);

		return question;
	}

	@Override
	@Transactional(readOnly=true)
	public Question findQuestionById(String idQuestion) {
		int idQuestionInt = Integer.parseInt(idQuestion);
		Question q = qDao.findById(idQuestionInt);
		return q;
	}

}
