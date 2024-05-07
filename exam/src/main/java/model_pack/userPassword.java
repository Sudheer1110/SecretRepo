package model_pack;

public class userPassword {
	private int userId;
	private String userPassword;

	public userPassword(int userId, String userPassword) {
		this.userId = userId;
		this.userPassword = userPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
