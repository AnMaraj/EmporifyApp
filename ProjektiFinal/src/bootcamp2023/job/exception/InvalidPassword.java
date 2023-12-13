package bootcamp2023.job.exception;

public class InvalidPassword extends RuntimeException{

	public InvalidPassword(String message) {
		super(message);
	}
}
