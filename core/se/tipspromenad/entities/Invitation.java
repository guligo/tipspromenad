package se.tipspromenad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents database entity for FB invitations.
 * 
 * @author eigogul
 */
@Entity
@Table(name = "invitations")
public class Invitation extends se.tipspromenad.entities.Entity {

	@ManyToOne
	private Game game;
	@Column(nullable = false)
	private String fbUserId;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getFbUserId() {
		return fbUserId;
	}

	public void setFbUserId(String fbUserId) {
		this.fbUserId = fbUserId;
	}

}
