package fr.humanbooster.fx.enquetes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import fr.humanbooster.fx.enquetes.business.Criteria;
import fr.humanbooster.fx.enquetes.business.PartnerSite;
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
import fr.humanbooster.fx.enquetes.service.PartenerSiteService;
import fr.humanbooster.fx.enquetes.service.SurveyService;

public class SurveyServiceImpl implements SurveyService {

	private SurveyDao sDao = new SurveyDaoImpl();
	private QuestionDao qDao = new QuestionDaoImpl();
	private CriteriaDao cDao = new CriteriaDaoImpl();
	private PartenerSiteService pss = new PartenerSiteServiceImpl();

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
		List<Question> questions;
		Set<Criteria> criterias;
		if (survey != null) {
			questions = survey.getLsQuestion();
			criterias = survey.getSetCriteria();
			for (Question q : questions) {
				qDao.deleteQuestion(q.getId());
			}
			for (Criteria c : criterias) {
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

	@Override
	public Set<Survey> filterSurveys(String name, Date start, Date end) {
		sDao.openCurrentSessionWithTransaction();
		Set<Survey> filtered = new TreeSet<>();
		Set<Survey> surveys = sDao.findAll();
		for (Survey survey : surveys) {
			if (name == null) {
				if (start != null && end != null) {
					if (survey.getDate().after(start) && survey.getDate().before(end)) {
						filtered.add(survey);
					}
				} else if (start != null) {
					if (survey.getDate().after(start)) {
						filtered.add(survey);
					}
				} else if (end != null) {
					if (survey.getDate().before(end)) {
						filtered.add(survey);
					}
				} else {
					filtered.add(survey);
				}
			} else if (!name.equals("")) {
				if (start != null && end != null) {
					if (survey.getName().contains(name) && survey.getDate().after(start)
							&& survey.getDate().before(end)) {
						filtered.add(survey);
					}
				} else if (start != null) {
					if (survey.getName().contains(name) && survey.getDate().after(start)) {
						filtered.add(survey);
					}
				} else if (end != null) {
					if (survey.getName().contains(name) && survey.getDate().before(end)) {
						filtered.add(survey);
					}
				} else {
					if (survey.getName().contains(name)) {
						filtered.add(survey);
					}
				}
			} else {
				if (start != null && end != null) {
					if (survey.getDate().after(start) && survey.getDate().before(end)) {
						filtered.add(survey);
					}
				} else if (start != null) {
					if (survey.getDate().after(start)) {
						filtered.add(survey);
					}
				} else if (end != null) {
					if (survey.getDate().before(end)) {
						filtered.add(survey);
					}
				} else {
					filtered.add(survey);
				}
			}
		}
		sDao.closeCurrentSessionwithTransaction();
		return filtered;
	}

	@Override
	public Survey addPartnerToSurvey(int idPartner, int idSurvey) {
		PartnerSite partner = pss.findById(idPartner);
		SurveyInternet survey = (SurveyInternet) this.findById(idSurvey);
		survey.getLsPartnerSite().add(partner);
		survey = (SurveyInternet) this.modifySurvey(survey);
		return survey;
	}

	@Override
	public Survey deletePartnerFromSurvey(int idPartner, int idSurvey) {
		sDao.openCurrentSessionWithTransaction();
		SurveyInternet survey = (SurveyInternet) this.findById(idSurvey);
		PartnerSite partner = pss.findById(idPartner);
		if(survey != null) {
			Set<PartnerSite> partners = survey.getLsPartnerSite();
			if(partners != null && partner != null) {
				System.out.println("remove partner " + partner.getId() + ":" + partners.remove(partner));
				survey.setLsPartnerSite(partners);
				System.out.println("Survey modifié=" + modifySurvey(survey));
			}
		}
		return survey;
	}

}
