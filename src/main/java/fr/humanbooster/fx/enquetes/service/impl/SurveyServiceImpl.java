package fr.humanbooster.fx.enquetes.service.impl;

import fr.humanbooster.fx.enquetes.dao.impl.SurveyDaoImpl;

public class SurveyServiceImpl {

	static SurveyDaoImpl sDao = new SurveyDaoImpl();
	
	public static void main(String[] args) {
		
		sDao.openCurrentSessionWithTransaction();
		sDao.findAll();
		sDao.closeCurrentSessionwithTransaction();
		
	}
	
}
