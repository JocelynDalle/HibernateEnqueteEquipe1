package fr.humanbooster.fx.enquetes.service.impl;

import java.util.Set;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.business.SurveyPhone;
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
	public Boolean deleteSurvey(int idSurvey) {
		sDao.openCurrentSessionWithTransaction();
		Boolean result = sDao.deleteSurvey(idSurvey);
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

}
