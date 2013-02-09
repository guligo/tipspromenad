package co.vrings.entities.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import co.vrings.entities.Entry;
import co.vrings.entities.User;

/**
 * Database entity for {@link Entry}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "entries")
@Inheritance(strategy = InheritanceType.JOINED)
public class EntryImpl extends EntityImpl implements Entry {
	
	@ManyToOne(optional = false)
	private UserImpl user;
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private int sequence;
	@Column(nullable = false)
	private boolean isPrivate;
	
	@Override
	@JsonIgnore
	public User getUser() {
		return user;
	}
	
	@Override
	public void setUser(User user) {
		this.user = (UserImpl) user;
	}
	
	@Override
	public Date getDate() {
		return date;
	}
	
	@Override
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public int getSequence() {
		return sequence;
	}
	
	@Override
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	@Override
	public boolean getIsPrivate() {
		return isPrivate;
	}
	
	@Override
	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getUsername() {
		return user.getUsername();
	}
	
}
