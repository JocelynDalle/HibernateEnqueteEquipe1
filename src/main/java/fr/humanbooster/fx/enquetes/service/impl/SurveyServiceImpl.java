package fr.humanbooster.fx.enquetes.service.impl;

import java.util.Set;
import java.util.TreeSet;

import fr.humanbooster.fx.enquetes.business.Criteria;
import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.business.SurveyPhone;
import fr.humanbooster.fx.enquetes.dao.CriteriaDao;
import fr.humanbooster.fx.enquetes.dao.QuestionDao;
import fr.humanbooster.fx.enquetes.dao.SurveyDao;
import fr.humanbooster.fx.enquetes.dao.impl.CriteriaDaoImpl;
import fr.humanbooster.fx.enquetes.dao.impl.QuestionDaoImpl;
import fr.humanbooster.fx.enquetes.dao.impl.SurveyDaoImpl;
import fr.humanbooster.fx.enquetes.service.SurveyService;

public class SurveyServiceImpl implements SurveyService{

	private SurveyDao sDao = new SurveyDaoImpl();
	private QuestionDao qDao = new QuestionDaoImpl();
	private CriteriaDao cDao = new CriteriaDaoImpl(); 
	
	@Override
	public Survey modifySurvey(Survey survey) {
		sDao.openCurrentSessionWithTransaction();
		Survey s = sDao.updateSurvey(survey);
		sDao.closeCurrentSessionwithTransaction();
		return s;
	}


	@Override
	public Boolean deleteSurvey(int idSurvey) {
		Boolean result = false;
		sDao.openCurrentSessionWithTransaction();
		qDao.openCurrentSessionWithTransaction();
		cDao.openCurrentSessionWithTransaction();
		Survey survey = sDao.findById(idSurvey);
		Set<Question> questions;
		Set<Criteria> criterias;
		if(survey != null) {
			questions = survey.getLsQuestion();
			criterias = survey.getSetCriteria();
			for(Question q : questions) {
				qDao.deleteQuestion(q.getId());
			}
			for(Criteria c : criterias) {
				cDao.deleteCriteria(c.getId());
			}
			result = sDao.deleteSurvey(idSurvey);
		}
		cDao.closeCurrentSessionwithTransaction();
		qDao.closeCurrentSessionwithTransaction();
		sDao.closeCurrentSessionwithTransaction();
		return result;
	}

	@Override
	public Set<Survey> findAllSurvey() {
		sDao.openCurrentSessionWithTransaction();
		Set<Survey> s = sDao.findAll();
		sDao.closeCurrentSessionwithTransaction();
		return s;
	}


	@Override
	public Survey findById(int idSurvey) {
		sDao.openCurrentSessionWithTransaction();
		Survey survey = sDao.findById(idSurvey);
		sDao.closeCurrentSessionwithTransaction();
		return survey;
	}
	
	@Override
	public SurveyInternet createSurveyInternet(SurveyInternet surveyInternet) {
		sDao.openCurrentSessionWithTransaction();
		SurveyInternet _surveyInternet = (SurveyInternet) sDao.createSurvey(surveyInternet);
		sDao.closeCurrentSessionwithTransaction();
		return _surveyInternet;
	}


	@Override
	public SurveyPhone createSurveyPhone(SurveyPhone surveyPhone) {
		sDao.openCurrentSessionWithTransaction();
		SurveyPhone _surveyPhone = (SurveyPhone) sDao.createSurvey(surveyPhone);
		sDao.closeCurrentSessionwithTransaction();
		return _surveyPhone;
	}

}
