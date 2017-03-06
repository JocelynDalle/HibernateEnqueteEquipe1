package fr.humanbooster.fx.enquetes.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.dao.QuestionDao;

public class QuestionDaoImpl implements QuestionDao {

	private Session session;
	private Transaction transaction;

	// Create
	@Override
	public Question createQuestion(Question question) {
		session.save(question);
		return question;
	}

	// Update
	@Override
	public Question updateQuestion(Question question) {
		session.saveOrUpdate(question);
		return question;
	}

	// Delete
	@Override
	public boolean deleteQuestion(int idQuestion) {
		Question question = this.findById(idQuestion);
		if (question == null)
			return false;
		session.delete(question);
		return true;
	}

	@Override
	public Question findById(int idQuestion) {

		return session.byId(Question.class).load(idQuestion);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Question> findAll() {
		List<Question> lsQuestion = session.createQuery("from Question").getResultList();
//		Set<Question> setQuestion = new TreeSet<Question>();
//		setQuestion.addAll(lsQuestion);
		return lsQuestion;

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
