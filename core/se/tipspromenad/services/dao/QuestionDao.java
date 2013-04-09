package se.tipspromenad.services.dao;

import org.springframework.beans.factory.annotation.Autowired;

import se.tipspromenad.entities.Question;

/**
 * Link between business logic and database.
 * 
 * @author guligo
 */
public class QuestionDao {
	
	@Autowired
	private CommonDao commonDao;
	
	public void removeQuestion(Long id) {
		commonDao.removeEntity(Question.class, id);
	}
	
}
