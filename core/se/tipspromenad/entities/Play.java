package se.tipspromenad.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents single play of a game by user.
 * 
 * @author guligo
 */
@Entity
@Table(name = "plays")
public class Play extends se.tipspromenad.entities.Entity {
	
	@ManyToOne
	private User user;
	@OneToMany
	private Set<Answer> answers;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	
}
