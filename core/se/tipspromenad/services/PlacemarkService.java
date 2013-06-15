package se.tipspromenad.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Placemark;
import se.tipspromenad.entities.Question;
import se.tipspromenad.services.dao.PlacemarkDao;

@Component
public class PlacemarkService {

	private static Logger logger = Logger.getLogger(PlacemarkService.class);
	
	@Autowired
	private PlacemarkDao placemarkDao;
	
	
	public Placemark getPlacemarkByGameAndQuestionId(Long gameId, Long questionId) {
		logger.debug("Retrieving placemarks for game with id = " + gameId + " and question with id = " + questionId);
		
		Placemark placemark = placemarkDao.getPlacemarkByGameAndQuestionId(gameId, questionId);
		return placemark;
	}
	
	public List<Placemark> getPlacemarksByGameId(Long gameId) {
		logger.debug("Retrieving placemark list for game with id = " + gameId);
		
		List<Placemark> placemarks = placemarkDao.getPlacemarksByGameId(gameId);
		logger.debug("Totally " + placemarks.size() + " placemarks retrieved");
		return placemarks;
	}
	
	public Placemark savePlacemark(Long id, Long gameId, Long questionId, Double latitude, Double longitude) {
		logger.debug("Saving placemark with id = " + id + ", gameId = " + gameId + ", questionId = " + questionId + ", latitude = " + latitude + " and longitude = " + longitude);
		
		Placemark placemark;
		if (id == null) {
			placemark = new Placemark();
			placemark.setQuestion(new Question(questionId));
			placemark.setGame(new Game(gameId));
			placemark.setLatitude(latitude);
			placemark.setLongitude(longitude);
			placemarkDao.createPlacemark(placemark);
		} else {
			placemark = placemarkDao.getPlacemark(id);
			placemark.setLatitude(latitude);
			placemark.setLongitude(longitude);
			placemarkDao.updatePlacemark(placemark);
		}
		return placemark;
	}
	
	public void removePlacemark(Long id) {
		logger.debug("Removing placemark with id = " + id);
		placemarkDao.removePlacemark(id);
	}
	
}
