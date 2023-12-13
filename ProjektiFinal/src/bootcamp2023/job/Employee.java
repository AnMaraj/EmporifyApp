package bootcamp2023.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bootcamp2023.job.exception.EmailNotValidException;

public class Employee {
	// punekerkuesi
	private static final String LOCAL_PART_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*$";
	private static final String DOMAIN_PART_REGEX = "^(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	private String name;
	private String email;
	private int yearsOfExp;
	private List<Job> jobsApplied;

	public Employee(String name, String email, int years) throws EmailNotValidException {
		this.name = name;
		setEmail(email);
		this.yearsOfExp = years;
		this.jobsApplied = new ArrayList<>();
	
		// this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws EmailNotValidException {

		if (!isValidEmail(email)) {
			throw new EmailNotValidException("Kujdes, email nuk eshte i sakte");
		}
		this.email = email;
	}
	
	public int getYearsOfExp() {
		return yearsOfExp;
	}
	
	public void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}
	
	public List<Job> getJobsApplied() {
		return jobsApplied;
	}

	public void addJob(Job job) {
		jobsApplied.add(job);
	}

	public void removeJob(Job job) {
		jobsApplied.remove(job);
	}
	

	public static boolean isValidEmail(String email) {
		int atIndex = email.indexOf('@'); // nje numer

		if (atIndex < 0 || atIndex == 0 || atIndex == email.length() - 1) {
			return false;
		}
		String localPart = email.substring(0, atIndex); // pjesen e pare para @
		String domainPart = email.substring(atIndex + 1);
		return localPart.matches(LOCAL_PART_REGEX) && domainPart.matches(DOMAIN_PART_REGEX);
	}

	@Override
	public String toString() {
		return "|'" + name + ", ' Vitet e eksperiences: " + yearsOfExp + ", Email: '" + email + "|";
		

	}

	@Override
	public int hashCode() {
		return Objects.hash(email, name, yearsOfExp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name) && yearsOfExp == other.yearsOfExp;
	}

	/**
	 * Nese eshte krijuar nje objekt Employee me x te dhena ath mos e krijo serish
	 * @throws EmailNotValidException 
	 * */


}
