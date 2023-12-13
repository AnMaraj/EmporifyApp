package bootcamp2023.job;

import bootcamp2023.job.exception.InvalidPassword;
import bootcamp2023.job.exception.InvalidUsername;

public enum UserApplication {

	EMPORIFY("EMPORIFY", "TEAMS");

	private String username;
	private String password;

	private UserApplication(String name, String password) throws InvalidUsername, InvalidPassword  {
		if (name == null || name.trim().isEmpty()) {
			throw new InvalidUsername("Name cannot be null or empty");
		}

		if (password == null || password.trim().isEmpty()) {
			throw new InvalidPassword("Password cannot be null or empty");
		}

		this.username = name;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean checkCredentials(String enteredUsername, String enteredPassword) {
		return username.equals(enteredUsername) && password.equals(enteredPassword);
	}
}
