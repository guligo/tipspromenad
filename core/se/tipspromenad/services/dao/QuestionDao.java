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
			.createQuery("select q from Game g join g.questions as q where g.id = :gameId")
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
		commonDao.getEntityManager()
			.createNativeQuery("alter table games_questions add sequence int null")
			.executeUpdate();
	}
	
	public Integer getSequence(Long gameId, Long questionId) {
		return Integer.parseInt(
			(String) commonDao.getEntityManager()
				.createNativeQuery("select sequence from games_questions where games_id = :gameId and questions_id = :questionId")
				.setParameter("gameId", gameId)
				.setParameter("questionId", questionId)
				.getSingleResult()
		);
	}
	
	public Integer getSequence(Long gameId) {
		return (Integer) commonDao.getEntityManager()
			.createNativeQuery("select max(sequence) from games_questions where games_id = :gameId")
			.setParameter("gameId", gameId)
			.getSingleResult();
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
