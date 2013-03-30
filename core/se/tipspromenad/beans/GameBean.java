package se.tipspromenad.beans;

import se.tipspromenad.controllers.GameController;

/**
 * Data transfer bean between {@link GameController#saveGame(Long, String)} and
 * view.
 * 
 * @author guligo
 */
public class GameBean extends DataTransferBean {

	private Long   id;
	private String name;

	public GameBean() {
		// used for serialization purposes only
	}

	public GameBean(Long id, String name) {
		this.id   = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
