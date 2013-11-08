package se.tipspromenad.controllers.game;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import se.tipspromenad.controllers.ResponseBean;

/**
 * Response object of {@link GameController#saveGame(Long, String, String)}.
 * 
 * @author eigogul
 */
@SuppressWarnings("deprecation")
@JsonWriteNullProperties(value = false)
public class GameSaveResponse extends ResponseBean<GameError> {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
