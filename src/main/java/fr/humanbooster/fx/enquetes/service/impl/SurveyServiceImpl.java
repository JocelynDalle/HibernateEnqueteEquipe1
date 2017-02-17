package fr.humanbooster.fx.enquetes.service.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.dao.SurveyDao;
import fr.humanbooster.fx.enquetes.dao.impl.SurveyDaoImpl;
import fr.humanbooster.fx.enquetes.service.SurveyService;

public class SurveyServiceImpl implements SurveyService{

	private SurveyDao sDao = new SurveyDaoImpl();
	
	@Override
	public Survey modifySurvey(Survey survey) {
		sDao.openCurrentSessionWithTransaction();
		Survey s = sDao.updateSurvey(survey);
		sDao.closeCurrentSessionwithTransaction();
		return s;
	}

	@Override
	public Question addQuestionToSurvey(Question question, Survey survey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteSurvey(Survey survey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Survey> findAllSurvey() {
		sDao.openCurrentSessionWithTransaction();
		Set<Survey> s = sDao.findAll();
		sDao.closeCurrentSessionwithTransaction();
		return s;
	}

}
