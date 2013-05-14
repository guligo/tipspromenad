package se.tipspromenad.services.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.Question;

/**
 * Link between business logic and database.
 * 
 * @author guligo
 */
@Component
@Transactional
public class QuestionDao {
	
	@Autowired
	private CommonDao commonDao;
	
	public void createQuestion(Question question) {
		commonDao.createEntity(question);
	}
	
	public Question getQuestion(Long id) {
		return (Question) commonDao.getEntity(Question.class, id);
	}
	
	public void updateQuestion(Question question) {
		commonDao.updateEntity(question);
	}
	
	public void removeQuestion(Long id) {
		commonDao.removeEntity(Question.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> getQuestionsByGameId(Long gameId) {
		return commonDao.getEntityManager().createQuery("from Question q join q.games qg where qg.id = :gameId")
			.setParameter("gameId", gameId)
			.getResultList();
	}
	
}
