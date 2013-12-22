package se.tipspromenad.controllers.invitation;

import java.util.List;

import se.tipspromenad.controllers.ResponseBean;

/**
 * See {@link InvitationController#getInvitationList(InvitationGetListRequest)}.
 * 
 * @author guligo
 */
public class InvitationGetListResponse extends ResponseBean<InvitationError> {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> fbUserIds;
	
	public List<String> getFbUserIds() {
		return fbUserIds;
	}
	
	public void setFbUserIds(List<String> fbUserIds) {
		this.fbUserIds = fbUserIds;
	}
	
}
