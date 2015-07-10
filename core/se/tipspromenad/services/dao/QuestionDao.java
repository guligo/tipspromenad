package se.tipspromenad.services.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.Answer;
import se.tipspromenad.entities.Question;

/**
 * Link between business logic and database.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Component
@Transactional
public class QuestionDao {
	
	private static Logger logger = Logger.getLogger(QuestionDao.class);
	
	@Autowired
	private CommonDao commonDao;
	
	public Long createQuestion(Question question) {
		return commonDao.createEntity(question);
	}
	
	public Question getQuestion(Long id) {
		return (Question) commonDao.getEntity(Question.class, id);
	}
	
	public void updateQuestion(Question question) {
		commonDao.updateEntity(question);
	}
	
	public void removeQuestion(Long id) {
		// FIXME: Query is wrong! It would require gameId as well.
		int status = commonDao.getEntityManager()
			.createNativeQuery("delete from games_questions where questions_id = :id")
			.setParameter("id", id)
			.executeUpdate();
		logger.info("Status of removal = " + status);
		if (status == 1) {
			commonDao.removeEntity(Question.class, id);
		}
	}
	
	public void createAnswer(Answer answer) {
		commonDao.createEntity(answer);
	}
	
	public void updateAnswer(Answer answer) {
		commonDao.updateEntity(answer);
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> getQuestionsByGameId(Long gameId) {
		return commonDao.getEntityManager()
			.createQuery("select q from Game g join g.questions as q where g.id = :gameId")
			.setParameter("gameId", gameId)
			.getResultList();
	} 
	
	public Integer getSequence(Long gameId, Long questionId) {
		return (Integer) commonDao.getEntityManager()
			.createNativeQuery("select sequence from games_questions where games_id = :gameId and questions_id = :questionId")
			.setParameter("gameId", gameId)
			.setParameter("questionId", questionId)
			.getSingleResult();
	}
	
	public Integer getSequence(Long gameId) {
		return (Integer) commonDao.getEntityManager()
			.createNativeQuery("select max(sequence) from games_questions where games_id = :gameId")
			.setParameter("gameId", gameId)
			.getSingleResult();
	}
	
	public void setSequence(Long gameId, Long questionId, Integer sequence) {
		logger.debug("Setting sequence = " + sequence + " for gameId = " + gameId + " and questionId = " + questionId);
		
		commonDao.getEntityManager()
			.createNativeQuery("update games_questions set sequence = :sequence where games_id = :gameId and questions_id = :questionId")
			.setParameter("gameId", gameId)
			.setParameter("questionId", questionId)
			.setParameter("sequence", sequence)
			.executeUpdate();
	}
	
	public void moveUp(Long gameId, Long questionId) {
		// get current sequence
		Integer currentSeq = getSequence(gameId, questionId);
		
		// get new sequence and corresponding question id
		Integer targetSeq = (Integer) commonDao.getEntityManager()
			.createNativeQuery("select max(sequence) from games_questions where sequence < :sequence")
			.setParameter("sequence", currentSeq)
			.getSingleResult();
		
		Long targetQuestionId;
		try {
			targetQuestionId = ((BigInteger) commonDao.getEntityManager()
				.createNativeQuery("select questions_id from games_questions where games_id = :gameId and sequence = :sequence")
				.setParameter("gameId", gameId)
				.setParameter("sequence", targetSeq)
				.getSingleResult())
				.longValue();
		} catch (NoResultException e) {
			targetQuestionId = null;
		}
		
		if (targetSeq != null && targetQuestionId != null) {
			setSequence(gameId, questionId, targetSeq);
			setSequence(gameId, targetQuestionId, currentSeq);
		}
	}
	
	public void moveDown(Long gameId, Long questionId) {
		// get current sequence
		Integer currentSeq = getSequence(gameId, questionId);
		
		// get new sequence and corresponding question id
		Integer targetSeq = (Integer) commonDao.getEntityManager()
			.createNativeQuery("select min(sequence) from games_questions where sequence > :sequence")
			.setParameter("sequence", currentSeq)
			.getSingleResult();
		
		Long targetQuestionId;
		try {
			targetQuestionId = ((BigInteger) commonDao.getEntityManager()
    			.createNativeQuery("select questions_id from games_questions where games_id = :gameId and sequence = :sequence")
    			.setParameter("gameId", gameId)
    			.setParameter("sequence", targetSeq)
    			.getSingleResult())
    			.longValue();
		} catch (NoResultException e) {
			targetQuestionId = null;
		}
		
		if (targetSeq != null && targetQuestionId != null) {
			setSequence(gameId, questionId, targetSeq);
			setSequence(gameId, targetQuestionId, currentSeq);
		}
	}
	
}
