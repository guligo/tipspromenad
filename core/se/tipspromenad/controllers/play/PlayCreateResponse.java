package se.tipspromenad.controllers.play;

import se.tipspromenad.controllers.ResponseBean;

/**
 * See {@link PlayController#createPlay(request)}.
 * 
 * @author guligo
 */
public class PlayCreateResponse extends ResponseBean<PlayError> {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
