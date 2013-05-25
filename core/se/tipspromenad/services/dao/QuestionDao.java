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
		return commonDao.getEntityManager()
			.createQuery("from Question q join q.games qg where qg.id = :gameId")
			.setParameter("gameId", gameId)
			.getResultList();
	} 
	
	@SuppressWarnings("unchecked")
	public boolean sequenceColumnExist() {
		List<String> result = commonDao.getEntityManager()
			.createNativeQuery("select table_catalog from information_schema.columns where table_schema = 'tipspromenad' and table_name = 'games_questions' and column_name = 'sequence'")
			.getResultList();
		if (result == null || result.size() == 0) {
			return false;
		}
		return true;
	}
	
	public void createSequenceColumn() {
		// ALTER TABLE `prefix_topic` ADD `topic_last_update` DATETIME NOT NULL;
	}
	
	public void setSequence(Long gameId, Long questionId, Long sequence) {
		commonDao.getEntityManager()
			.createNativeQuery("update games_questions set sequence = :sequence where games_id = :gameId and questions_id = :questionId")
			.setParameter("gameId", gameId)
			.setParameter("questionId", questionId)
			.setParameter("sequence", sequence)
			.executeUpdate();
	}
	
}
