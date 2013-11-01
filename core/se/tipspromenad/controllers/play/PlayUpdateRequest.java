package se.tipspromenad.controllers.play;

import se.tipspromenad.controllers.RequestBean;

/**
 * See {@link PlayController#updatePlay(request)}.
 * 
 * @author guligo
 */
public class PlayUpdateRequest extends RequestBean {
	
	private static final long serialVersionUID = 1L;
	
	private Long   id;
	private String end;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
}
