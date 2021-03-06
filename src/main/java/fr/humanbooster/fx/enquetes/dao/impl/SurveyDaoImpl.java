package fr.humanbooster.fx.enquetes.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import fr.humanbooster.fx.enquetes.business.Survey;
import fr.humanbooster.fx.enquetes.dao.SurveyDao;

public class SurveyDaoImpl implements SurveyDao {

	private Session session;
	private Transaction transaction;

	// Create
	@Override
	public Survey createSurvey(Survey survey) {
		session.save(survey);
		return survey;
	}

	// Update
	@Override
	public Survey updateSurvey(Survey survey) {
		session.saveOrUpdate(survey);
		return survey;
	}

	// Delete
	@Override
	public boolean deleteSurvey(int idSurvey) {
		Survey survey = this.findById(idSurvey);
		if (survey == null)
			return false;
		session.delete(survey);
		return true;
	}

	@Override
	public Survey findById(int idSurvey) {

		return session.byId(Survey.class).load(idSurvey);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Survey> findAll() {
		List<Survey> lsSurvey = session.createQuery("from Survey").getResultList();
		Set<Survey> setSurvey = new TreeSet<Survey>();
		setSurvey.addAll(lsSurvey);
		return setSurvey;

	}

	@Override

	public Session openCurrentSession() {
		session = getSessionFactory().openSession();
		return session;

	}

	@Override

	public Session openCurrentSessionWithTransaction() {
		session = getSessionFactory().openSession();
		transaction = session.beginTransaction();
		return session;
	}

	@Override

	public void closeCurrentSession() {
		session.close();
	}

	@Override

	public void closeCurrentSessionwithTransaction() {
		transaction.commit();
		session.close();

	}

	private SessionFactory getSessionFactory() {

		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml")
				.build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

		return sessionFactory;

	}

}
