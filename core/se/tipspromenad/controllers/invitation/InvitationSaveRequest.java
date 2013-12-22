package se.tipspromenad.controllers.invitation;

import se.tipspromenad.controllers.RequestBean;

/**
 * See {@link InvitationController#saveInvitation(InvitationSaveRequest)}.
 * 
 * @author guligo
 */
public class InvitationSaveRequest extends RequestBean {
	
	private static final long serialVersionUID = 1L;
	
	private Long   gameId;
	private String fbUserId;
	
	public Long getGameId() {
		return gameId;
	}
	
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
	public String getFbUserId() {
		return fbUserId;
	}
	
	public void setFbUserId(String fbUserId) {
		this.fbUserId = fbUserId;
	}
	
}
