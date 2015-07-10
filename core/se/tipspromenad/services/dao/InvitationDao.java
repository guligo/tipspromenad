package se.tipspromenad.services.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.Invitation;

/**
 * Persistence layer for {@link Invitation} entity.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Component
@Transactional
public class InvitationDao {
	
	@Autowired
	private CommonDao commonDao;
	
	public Invitation getInvitation(Long id) {
		return (Invitation) commonDao.getEntity(Invitation.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Invitation getInvitation(Long gameId, String fbUserId) {
		List<Invitation> invitation = commonDao.getEntityManager().createQuery("from Invitation i where i.game.id = :gameId and i.fbUserId = :fbUserId")
			.setParameter("gameId", gameId)
			.setParameter("fbUserId", fbUserId)
			.getResultList();
		if (invitation != null && invitation.size() > 0) {
			assert(invitation.size() == 1);
			return invitation.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Invitation> getInvitationListByGameId(Long gameId) {
		List<Invitation> invitations = commonDao.getEntityManager().createQuery("from Invitation i where i.game.id = :gameId")
			.setParameter("gameId", gameId)
			.getResultList();
		return invitations;
	}
	
	public void saveInvitation(Invitation invitation) {
		commonDao.createEntity(invitation);
	}
	
	public void removeInvitation(Long id) {
		commonDao.removeEntity(Invitation.class, id);
	}
	
}
