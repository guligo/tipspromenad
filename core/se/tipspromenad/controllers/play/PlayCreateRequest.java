package se.tipspromenad.controllers.play;

import se.tipspromenad.controllers.RequestBean;

/**
 * See {@link PlayController#createPlay(request)}.
 * 
 * @author guligo
 * @author pavelefimov
 */
public class PlayCreateRequest extends RequestBean {
	
	private static final long serialVersionUID = 1L;
	
	private Long   userId;
	private String start;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
}
