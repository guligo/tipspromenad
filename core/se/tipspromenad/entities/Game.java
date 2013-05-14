package se.tipspromenad.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.User;

/**
 * Database entity for {@link Game}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "games")
public class Game extends se.tipspromenad.entities.Entity {

	public static final int MIN_NAME_LENGTH = 5;
	public static final int MAX_NAME_LENGTH = 50;

	@ManyToOne
	private User creator;
	@Column(nullable = false)
	private Date creationDate;
	@Column(nullable = false, length = MAX_NAME_LENGTH)
	private String name;
	@Column(nullable = false)
	private Date date;
	@JsonIgnore
	@OrderBy("id")
	@ManyToMany
	private Set<Question> questions;
	
	public Game() {
		// for serialization purpose
	}
	
	public Game(Long id) {
		setId(id);
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = (User) creator;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
