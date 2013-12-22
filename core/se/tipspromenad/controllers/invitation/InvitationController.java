package se.tipspromenad.controllers.invitation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.Invitation;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.InvitationService;

/**
 * MVC paradigm controller responsible for actions around friends page.
 * 
 * @author guligo
 */
@Controller
public class InvitationController {
	
	private final static Logger logger = Logger.getLogger(InvitationController.class);	
	
	@Autowired
	private InvitationService invitationService;
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.INVITATION_LIST_ACTION)
	public @ResponseBody InvitationGetListResponse getInvitationList(@PathVariable Long gameId) {
		InvitationGetListResponse response = new InvitationGetListResponse();
		response.setFbUserIds(new ArrayList<String>());
		
		try {
			List<Invitation> invitations = invitationService.getInvitationListByGameId(gameId);
			if (invitations != null) {
				for (Invitation invitation : invitations) {
					response.getFbUserIds().add(invitation.getFbUserId());
				}
			}
		} catch (Exception e) {
			response.addError(InvitationError.UNEXPECTED_ERROR);
			logger.error("Error on getting invitation list for game with gameId = " + gameId);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.INVITATION_SAVE_ACTION)
	public @ResponseBody InvitationSaveResponse saveInvitation(@RequestBody InvitationSaveRequest request) {
		InvitationSaveResponse response = new InvitationSaveResponse();
		
		try {
			invitationService.saveInvitation(request.getGameId(), request.getFbUserId());
		} catch (Exception e) {
			response.addError(InvitationError.UNEXPECTED_ERROR);
			logger.error("Error on saving invitation for game with gameId = " + request.getGameId() + " and fbUserId = " + request.getFbUserId(), e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.INVITATION_REMOVE_ACTION)
	public @ResponseBody void removeInvitation(@PathVariable Long gameId, @PathVariable String fbUserId) {
		invitationService.removeInvitation(gameId, fbUserId);
	}
	
}
