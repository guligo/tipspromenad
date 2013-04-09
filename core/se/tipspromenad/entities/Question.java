package se.tipspromenad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	private Game game;
	@Column(nullable = false, length = MAX_TEXT_LENGTH)
	private String text;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
