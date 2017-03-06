package fr.humanbooster.fx.enquetes.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.humanbooster.fx.enquetes.business.Question;
import fr.humanbooster.fx.enquetes.dao.QuestionDao;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	@Autowired
	private SessionFactory sf;

	// Create
	@Override
	public Question createQuestion(Question question) {
		sf.getCurrentSession().save(question);
		return question;
	}

	// Update
	@Override
	public Question updateQuestion(Question question) {
		sf.getCurrentSession().saveOrUpdate(question);
		return question;
	}

	// Delete
	@Override
	public boolean deleteQuestion(int idQuestion) {
		Question question = this.findById(idQuestion);
		if (question == null)
			return false;
		sf.getCurrentSession().delete(question);
		return true;
	}

	@Override
	public Question findById(int idQuestion) {

		return sf.getCurrentSession().byId(Question.class).load(idQuestion);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Question> findAll() {
		List<Question> lsQuestion = sf.getCurrentSession().createQuery("from Question").getResultList();
//		Set<Question> setQuestion = new TreeSet<Question>();
//		setQuestion.addAll(lsQuestion);
		return lsQuestion;

	}

}
