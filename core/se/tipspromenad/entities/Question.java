package se.tipspromenad.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import se.tipspromenad.entities.enums.QuestionType;

/**
 * Represents entity for questions.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Entity
@Table(name = "questions")
public class Question extends se.tipspromenad.entities.Entity {
	
	public static final int MIN_TEXT_LENGTH = 5;
	public static final int MAX_TEXT_LENGTH = 500;
	
	@Column(nullable = true, length = MAX_TEXT_LENGTH)
	private String text;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Answer> answers;
	@Column(nullable = false)
	private QuestionType type;
	@Transient
	private Integer sequence;
	
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
	
	public QuestionType getType() {
		return type;
	}
	
	public void setType(QuestionType type) {
		this.type = type;
	}
	
	public Integer getSequence() {
		return sequence;
	}
	
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
}
