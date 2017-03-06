package fr.humanbooster.fx.enquetes.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Question;

public interface QuestionDao {

	public Question createQuestion(Question question);

	public Question updateQuestion(Question question);

	public boolean deleteQuestion(int idQuestion);

	public Question findById(int idQuestion);

	public List<Question> findAll();

	public Session openCurrentSession();

	public Session openCurrentSessionWithTransaction();

	public void closeCurrentSession();

	public void closeCurrentSessionwithTransaction();

}
