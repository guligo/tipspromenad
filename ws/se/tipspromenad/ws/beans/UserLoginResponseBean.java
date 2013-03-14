package se.tipspromenad.ws.beans;

public class UserLoginResponseBean extends ResponseBean {

	public final static int STATUS_OK = 0;
	public final static int STATUS_NOK = 1;

	private int status;
	private Long userId;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
