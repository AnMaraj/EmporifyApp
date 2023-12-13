package bootcamp2023.job.exception;

public class InvalidUsername extends RuntimeException{

	public InvalidUsername(String message) {
		super(message);
	}
}
