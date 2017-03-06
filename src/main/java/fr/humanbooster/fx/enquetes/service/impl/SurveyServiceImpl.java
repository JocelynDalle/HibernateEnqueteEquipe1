package fr.humanbooster.fx.enquetes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.humanbooster.fx.enquetes.business.Criteria;
import fr.humanbooster.fx.enquetes.business.PartnerSite;
import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.business.SurveyInternet;
import fr.humanbooster.fx.enquetes.business.SurveyPhone;
import fr.humanbooster.fx.enquetes.dao.CriteriaDao;
import fr.humanbooster.fx.enquetes.dao.QuestionDao;
import fr.humanbooster.fx.enquetes.dao.SurveyDao;
import fr.humanbooster.fx.enquetes.service.PartenerSiteService;
import fr.humanbooster.fx.enquetes.service.SurveyService;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyDao sDao;
	@Autowired
	private QuestionDao qDao;
	@Autowired
	private CriteriaDao cDao;
	@Autowired
	private PartenerSiteService pss;

	@Override
	public Survey modifySurvey(Survey survey) {
		return sDao.updateSurvey(survey);
	}

	@Override
	public Boolean deleteSurvey(int idSurvey) {
		Boolean result = false;
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
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Survey> findAllSurvey() {
		Set<Survey> s = sDao.findAll();
		return s;
	}

	@Override
	@Transactional(readOnly=true)
	public Survey findById(int idSurvey) {
		return sDao.findById(idSurvey);
	}

	@Override
	public SurveyInternet createSurveyInternet(SurveyInternet surveyInternet) {
		return (SurveyInternet) sDao.createSurvey(surveyInternet);
	}

	@Override
	public SurveyPhone createSurveyPhone(SurveyPhone surveyPhone) {
		return (SurveyPhone) sDao.createSurvey(surveyPhone);
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Survey> filterSurveys(String name, Date start, Date end) {
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
