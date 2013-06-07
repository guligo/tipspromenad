package se.tipspromenad.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents entity for questions.
 * 
 * @author guligo
 */
@Entity
@Table(name = "questions")
public class Question extends se.tipspromenad.entities.Entity {
	
	public static final int MIN_TEXT_LENGTH = 5;
	public static final int MAX_TEXT_LENGTH = 500;
	
	@Column(nullable = false, length = MAX_TEXT_LENGTH)
	private String text;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Answer> answers;
	
	public Question() {
		// for serialization purpose
	}
	
	public Question(Long id) {
		setId(id);
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}
