package se.tipspromenad.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

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
	
}
