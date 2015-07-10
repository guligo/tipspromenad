package se.tipspromenad.controllers.game;

import se.tipspromenad.controllers.RequestBean;

/**
 * Request object of {@link GameController#saveGame(Long, String, String)}.
 * 
 * @author guligo
 * @author pavelefimov
 */
public class GameSaveRequest extends RequestBean {

	private static final long serialVersionUID = 1L;

	private Long   id;
	private String name;
	private String date;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
