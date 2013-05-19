package se.tipspromenad.controllers.question;

import se.tipspromenad.controllers.RequestBean;

public class PlacemarkSaveRequest extends RequestBean {
	
	private static final long serialVersionUID = 1L;
	
	private Long   id;
	private Long   gameId;
	private Long   questionId;
	private Double latitude;
	private Double longitude;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getGameId() {
		return gameId;
	}
	
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
	public Long getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
