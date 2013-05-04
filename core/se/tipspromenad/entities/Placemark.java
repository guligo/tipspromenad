package se.tipspromenad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents entity for question placemark.
 * 
 * @author guligo
 */
@Entity
@Table(name = "placemarks")
public class Placemark extends se.tipspromenad.entities.Entity {
	
	@OneToOne
	private Question question; 
	@Column(nullable = false)
	private Double latitude;
	@Column(nullable = false)
	private Double longitude;
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
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
