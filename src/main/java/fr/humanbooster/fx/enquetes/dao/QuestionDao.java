package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Question;

public interface QuestionDao {

	Question createQuestion(Question question);

	Question updateQuestion(Question question);

	boolean deleteQuestion(int idQuestion);

	Question findById(int idQuestion);

	Set<Question> findAll();

	Session openCurrentSession();

	Session openCurrentSessionWithTransaction();

	void closeCurrentSession();

	void closeCurrentSessionwithTransaction();

}
