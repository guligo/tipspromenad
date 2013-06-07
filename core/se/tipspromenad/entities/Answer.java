package se.tipspromenad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class Answer extends se.tipspromenad.entities.Entity {
	
	public static final int MIN_TEXT_LENGTH = 10;
	public static final int MAX_TEXT_LENGTH = 300;
	
	@Column(nullable = false, length = MAX_TEXT_LENGTH)
	private String text;
	@Column(nullable = false)
	private Boolean correct;
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Boolean getCorrect() {
		return correct;
	}
	
	public void setCorret(Boolean correct) {
		this.correct = correct;
	}
	
}
