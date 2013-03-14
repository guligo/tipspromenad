package se.tipspromenad.ws.beans;

import java.io.Serializable;

/**
 * Represents request entity for WS call.
 * 
 * @author eigogul
 */
public class RequestBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long sessionId;

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

}
