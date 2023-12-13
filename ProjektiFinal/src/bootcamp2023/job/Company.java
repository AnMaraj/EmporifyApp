package bootcamp2023.job;

import java.util.Objects;
import java.util.Scanner;

import bootcamp2023.job.exception.NiptNonValidException;

public class Company {

	static Scanner scanner = new Scanner(System.in);
	private String name;
	private String nipt; // 12 karaktere 8 numra dhe 2 shkronja kapitale ne fillim dhe ne fund N12354625L
	//private List<Job> jobsInCompany;

	public Company(String name, String nipt) throws NiptNonValidException {
		this.name = name;
		setNipt(nipt);
		//this.jobsInCompany = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getNipt() {
		return nipt;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNipt(String nipt) throws NiptNonValidException {
		if (isValidNiptFormat(nipt)) {
			this.nipt = nipt;
		} else {
			throw new NiptNonValidException("Nipt NUK eshte i sakte. \nDuhet te permbaje 10 karakteere");
		}
	}

//	public List<Job> getJobsInCompany() {
//		return jobsInCompany;
//	}
//
//	public void addJob(Job job) {
//		jobsInCompany.add(job);
//	}
//
//	public void removeJob(Job job) {
//		jobsInCompany.remove(job);
//	}
//
//	public List<Job> publikoPunet() {
//		List<Job> list = new ArrayList<>();
//		for (Job job : getJobsInCompany()) {
//			list.add(job);
//		}
//		return list;
//	}

	@Override
	public String toString() {
		return "Employeer{" + "name='" + name + '\'' + "nipt= " + nipt;
	}

	private static boolean isValidNiptFormat(String nipt) {
		if (nipt == null || nipt.length() != 10) {
			return false;
		}
		char firstChar = nipt.charAt(0);
		char lastChar = nipt.charAt(9);

		if (!Character.isLetter(firstChar) || !Character.isLetter(lastChar)) {
			return false;
		}

		String numbers = nipt.substring(1, 9);
		try {
			Integer.parseInt(numbers);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash( name,  nipt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Company company = (Company) obj;
		return Objects.equals(name, company.name) && Objects.equals(nipt, company.nipt);
	}

//	public static void main(String[] args) throws NiptNonValidException, DateNotValidException {
//		Company com = new Company("Ikub", "L12345678L");
//		String type = OnsiteOrRemote.ONSITE.getName();
//		OnsiteOrRemote location = OnsiteOrRemote.valueOf(type.toUpperCase());
//
//		String date = "2023-12-12";
//		System.out.print("Vendosni deadline si ne formatin (YYYY-MM-DD): ");
//		LocalDate deadline = LocalDate.parse(scanner.next());
//
//		com.addJob(new Job("Java", location, deadline, "Java eshte gjuhe programimi"));
//		com.addJob(new Job("Python", location, deadline, "Python eshte gjuhe programimi"));
//		com.addJob(new Job("Java", location, deadline, "Java eshte gjuhe programimi"));
//		System.out.println("Kompania Ikub");
//		System.out.println(com);
//		Company com2 = new Company("TCT", "L12345678L");
//		com2.addJob(new Job("Java", location, deadline, "Java eshte gjuhe programimi"));
//		com2.addJob(new Job("Python", location, deadline, "Python eshte gjuhe programimi"));
//		//com2.addJob(new Job("Java", location, deadline, "Java eshte gjuhe programimi"));
//
//		System.out.println("Kompania TCT");
//		System.out.println(com2);
//		
//		
//		List lis = com.getJobsInCompany();
//		System.out.println("Ikub ka keto jobs: \n" + lis + "\n");
//		System.out.println("Numri i jobs ne IKUB: " +   lis.size());
//		List lis2 = com2.getJobsInCompany();
//		System.out.println("TCT ka keto jobs: " + lis2);
//		System.out.println("Numri i jobs ne TCT: " +   lis2.size());
//
//		List li = com.publikoPunet();
//		System.out.println(li);
//		
//	}
}
