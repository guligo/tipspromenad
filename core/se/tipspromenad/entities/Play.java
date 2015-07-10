package se.tipspromenad.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents single play of a game by user.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Entity
@Table(name = "plays")
public class Play extends se.tipspromenad.entities.Entity {
	
	@ManyToOne
	private User user;
	@Column(nullable = true)
	private Date start;
	@Column(nullable = true)
	private Date end;
	@OneToMany
	private Set<Answer> answers;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public Set<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	
}
