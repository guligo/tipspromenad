package se.tipspromenad.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Invitation;
import se.tipspromenad.services.dao.InvitationDao;

/**
 * Provides business logic for {@link Invitation} entity.
 * 
 * @author guligo
 */
@Component
public class InvitationService {
	
	private final static Logger logger = Logger.getLogger(InvitationService.class);	
	
	@Autowired
	private InvitationDao invitationDao;
	
	public List<Invitation> getInvitationListByGameId(Long gameId) {
		return invitationDao.getInvitationListByGameId(gameId);
	}
	
	public void saveInvitation(Long gameId, String fbUserId) {
		Invitation invitation = new Invitation();
		invitation.setGame(new Game(gameId));
		invitation.setFbUserId(fbUserId);
		
		logger.debug("Saving invitation for game with gameId = " + gameId + " and fbUserId = " + fbUserId);
		invitationDao.saveInvitation(invitation);
	}
	
	public void removeInvitation(Long gameId, String fbUserId) {
		Invitation invitation = invitationDao.getInvitation(gameId, fbUserId);
		
		logger.debug("Removing invitation with id = " + invitation.getId());
		invitationDao.removeInvitation(invitation.getId());
	}
	
}
