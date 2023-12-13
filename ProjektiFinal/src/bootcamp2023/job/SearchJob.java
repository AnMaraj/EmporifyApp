package bootcamp2023.job;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bootcamp2023.job.exception.DateNotValidException;
import bootcamp2023.job.exception.EmailNotValidException;
import bootcamp2023.job.exception.NiptNonValidException;

public class SearchJob {

	String RESET = "\u001B[0m";
	String RED = "\u001B[31m";
	String GREEN = "\u001B[32m";
	String YELLOW = "\u001B[33m";
	// Kompanite mund te postojne free deri ne 2 pozicione
	private static final int FREE_JOB_LIMIT = 2;
	private static final int COST_PER_JOB = 20;
	private static final int MIN_YEARS_FOR_APPLICANTS = 2;
	private static int countId = 1;

	private static int countJobs = 1;
	private List<Employee> listofEmployee;
	private List<Company> listOfCompanies;
	private List<Job> listOfPublishedJobs;
	private Map<Integer, Job> saveJobs;
	private Map<Job, List<Employee>> listOfAplications;
	private Map<LocalDate, Job> timePost;
	private Map<Company, Job> jobsOfCompany;
	private Map<Company, List<Job>> jobsPerCompany;
	private static Map<String, String> registeredCompanies;

	private Map<Job, Company> punetPerKOmpani;

	public SearchJob() {
		this.listofEmployee = new ArrayList<>();
		this.listOfCompanies = new ArrayList<>();
		this.listOfPublishedJobs = new ArrayList<>();
		this.saveJobs = new HashMap<>();
		this.listOfAplications = new HashMap<>();
		this.timePost = new HashMap<>();
		this.jobsOfCompany = new HashMap<>();
		this.jobsPerCompany = new HashMap<>();
		this.punetPerKOmpani = new HashMap<>();
		this.registeredCompanies = new HashMap<>();
	}

	// regjistrohu si punekerkues
	/**
	 * 
	 * The method takes two parameters and register a person as a person who is
	 * searching for a job in our app
	 * 
	 * @param name  The name of the Employee
	 * @param email The email of the Employee that must be verified
	 */
	public void registerAsEmployee(String name, String email, int yearsOfExp) throws EmailNotValidException {
		Employee emp = new Employee(name, email, yearsOfExp);
		listofEmployee.add(emp);
		System.out.println("'" + name + "' me email '" + email + "' regjistrimi juaj u krye me sukses!");
	}

	/**
	 * The method takes two parameters and register a company as a juridical person
	 * in our app
	 * 
	 * @param companyName the name of the company that will be register in the app
	 * @param nipt        the company nipt - a unique number that identifies
	 *                    companies in QKB
	 * @throws NiptNonValidException
	 */
	public void registerAsEmployer(String companyName, String nipt) throws NiptNonValidException {
		Company kompani = new Company(companyName, nipt);
		listOfCompanies.add(kompani);
		System.out.println("Personi juridik '" + companyName + "' me nipt '" + nipt + "' u regjistrua me sukses!");
	}

	// verifiko nese ekziston si kompani e regjistruar
	/**
	 * The boolean method return true if the company that gets as a input is
	 * registerd or not
	 * 
	 * @param companyName the name of the company that will be register in the app
	 * @param nipt        the company nipt - a unique number that identifies
	 *                    companies in QKB
	 * 
	 * @return true if the company is already registered in the app false if it is
	 *         not registered
	 */
	boolean validCompany(String nipt, String name) {
		if (listOfCompanies.size() != 0 || !listOfCompanies.isEmpty() || listOfCompanies != null) {
			for (int i = 0; i < listOfCompanies.size(); i++) {
				if (listOfCompanies.get(i).getName().equals(name) && listOfCompanies.get(i).getNipt().equals(nipt)) {
					return true;
				}
			}
			return false;

		} else {
			System.out.println("Nuk ka asnje kompani te regjistruar");

			return true;
		}
	}

	// kontrollo nese fillimisht eshte regjistruar si punekerkues

	/**
	 * The boolean method return true if the person that gets as a input is
	 * registerd or not
	 * 
	 * @param name  the name of the person that is register in the app
	 * @param email the email of the employee
	 * 
	 * @return true if the person has been log in once false if it is not registered
	 */
	boolean validEmployee(String name, String email) {
		if (listofEmployee.size() != 0 || !listofEmployee.isEmpty() || listofEmployee != null) {
			for (int i = 0; i < listofEmployee.size(); i++) {
				if (listofEmployee.get(i).getName().equals(name) && listofEmployee.get(i).getEmail().equals(email)) {
					return true;
				}
			}
			System.out.println("Nuk ekziston punekerkues i regjistruar me emrin " + name + " dhe email " + email);
			return false;

		} else {
			System.out.println("Nuk ka asnje punekerkues te regjistruar");

			return true;
		}
	}

	/**
	 * Kontrollo qe nese ky user ekziston ath nuk eshtenevoja te regjistrohet serish
	 * 
	 * @param emp objekti Employee qe verifikohet nese ekziston apo jo
	 * @return metoda booleane kthen true nese punekerkuesi eshte registruar njehere
	 *         dhe false nese ai nuk eshte regjistruar si punekerkues ne app tone
	 */
	public boolean employeeExists(Employee emp) {
		return listofEmployee.contains(emp);
	}

	/**
	 * Kontrollon nese kompania ekziston tashme
	 * 
	 * @param company objekti Company qe verifikohet nese kompania ekziston apo jo e
	 *                regjistruar ne app tone
	 * @return kthen true nese ekziston kthen false nese nuk rezulton e regjistsruar
	 */
	public boolean companyExists(Company company) {
		return listOfCompanies.contains(company);
	}

	/**
	 * The boolean method return true if the job is alreadey posted or not
	 * 
	 * @param id the id is a unique paramter of the job that defers job from one
	 *           another
	 * @return true if the job is posted in the app false if it is not posted
	 */
	boolean validJob(long id) {
		if (listOfPublishedJobs.size() != 0 || !listOfPublishedJobs.isEmpty() || listOfPublishedJobs != null) {
			for (int i = 0; i < listOfPublishedJobs.size(); i++) {
				if (listOfPublishedJobs.get(i).getId() == id) {
					System.out.println(listOfPublishedJobs.get(i).getId());
					return true;
				}
			}
			return false;

		} else {
			System.out.println("Nuk ka asnje job te regjistruar");

			return true;
		}
	}

	/**
	 * The method create a new job and posts it in our application
	 *
	 * @param name     the name of the company that posts the job
	 * @param nipt     the the identifier of the compnay
	 * @param title    the title of the job that the company will post
	 * @param location the type of the job, if it is REMOTE or OFFICE
	 * @param deadline the last date that the company will accept applications
	 * @throws NiptNonValidException
	 * @throws DateNotValidException
	 */
	public void postJobs(String name, String nipt, String title, OnsiteOrRemote location, LocalDate deadline,
			String description) throws NiptNonValidException, DateNotValidException { // OK
		Company company = null;
		for (Company existingCompany : listOfCompanies) {
			if (existingCompany.getName().equalsIgnoreCase(name) && existingCompany.getNipt().equalsIgnoreCase(nipt)) {
				company = existingCompany;  //merr kompanine , sipas asaj vazhdon iterimin
				break;
			}
		}
		if (company == null) {
			try {
				company = new Company(name, nipt);
				listOfCompanies.add(company);
			} catch (NiptNonValidException e) {
				System.out.println("Kujdes: " + e.getMessage());
				return;
			}
		}

		try {
			Job newJob = new Job(title, location, deadline, description);
			listOfPublishedJobs.add(newJob);
			saveJobs.put((int) newJob.getId(), newJob);

			jobsOfCompany.put(company, newJob);
			jobsPerCompany.computeIfAbsent(company, k -> new ArrayList<>()).add(newJob);

			System.out.println("Job posted successfully!");
		} catch (DateNotValidException e) {
			System.out.println("Kujdes: " + e.getMessage());
		}
	}

	/**
	 * The method returns all jobs posted in our applications
	 */
	public void getAllJobs() { // OK
		if (!listOfPublishedJobs.isEmpty() && listOfPublishedJobs != null) {
			Iterator<Job> lir = listOfPublishedJobs.iterator();

			while (lir.hasNext()) {
				System.out.println(lir.next());
			}
		}
	}

	/**
	 * The method return the specific job based on id
	 * 
	 * @param jobId unique parameter of the job
	 * 
	 * @return the job if it corresponds with the id as a parameter or null the the
	 *         job with this id doesn't exists
	 * 
	 */
//	public Job getJobByIds(long jobId) { // OK
//		Job job = null;
//		Company komp = null;
//		if (!jobsOfCompany.isEmpty() || jobsOfCompany != null) {
//			for (Map.Entry<Company, Job> jobsMap : jobsOfCompany.entrySet()) {
//				if (jobsMap.getValue().getId() == jobId) {
//					job = jobsMap.getValue();
//					komp = jobsMap.getKey();
//				}
//			}
//			if (job != null) {
//				System.out.println("Job with id: '" + job.getId() + "' eshte '" + job.getTitle()
//						+ "' postuar nga kompania '" + komp.getName() + "'");
//			}
//		}
//		return null;
//	}

	/**
	 * The method displays all employees that are registered in application
	 */
	public void displayAllEmployees() { // sakte
		if (listOfCompanies.size() != 0 || !listOfCompanies.isEmpty() || listOfCompanies != null) {
			System.out.println("Lista ka: " + listOfCompanies.size() + " persona fizik te regjistruar");
			for (int i = 0; i < listOfCompanies.size(); i++) {
				System.out.println((i + 1) + "." + listOfCompanies.get(i).getName());
			}
		}
	}

	/**
	 * The method displays all employers that are registered in application
	 */
	public void displayAllEmployers() { // sakte
		if (listofEmployee.size() != 0 || !listofEmployee.isEmpty() || listofEmployee != null) {
			System.out.println("Lista ka: " + listofEmployee.size() + " punekerkues te regjistruar");
			for (int i = 0; i < listofEmployee.size(); i++) {
				System.out.println(
						"Emri: " + listofEmployee.get(i).getName() + "\nEmail: " + listofEmployee.get(i).getEmail()
								+ "\nYears Of Experience" + listofEmployee.get(i).getYearsOfExp());
				System.out.println("----------");
			}
		}
	}

	/**
	 * The method is created for applying in a specific Job
	 * 
	 * @param employeeName the name of employee that want to apply in a specific job
	 * @param email        the email of the applicants
	 * @param jobId        the identifier of the job that is posted in app
	 * @throws EmailNotValidException
	 */
	public void applyForJob(String employeeName, String email, int yearsOfExp, long jobId)
			throws EmailNotValidException { // ok
		// Employee emp = new Employee(employeeName, email, yearsOfExp);
		Job selectedJob = null;
		for (Job job : listOfPublishedJobs) {
			if (job.getId() == jobId) {
				selectedJob = job;
				break; // puna u gjet, nuk eshte nevoja te iterojme me tej
			}
		}
		if (selectedJob != null) {
			if (!selectedJob.getApplicants().contains(new Employee(employeeName, email, yearsOfExp))) {
				selectedJob.addApplicant(new Employee(employeeName, email, yearsOfExp));

				List<Employee> applicants = listOfAplications.getOrDefault(selectedJob, new ArrayList<>());
				applicants.add(new Employee(employeeName, email, yearsOfExp));
				listOfAplications.put(selectedJob, applicants);
				System.out.println("Aplikimi per pozicionin: " + selectedJob.getTitle() + " me id: "
						+ selectedJob.getId() + " u krye me sukses!");
				sendThankYouMessage(email);
			} else {
				System.out.println("Ju keni aplikuar tashme per kete pune.");
			}
		} else {
			System.out.println("NUK ekziston asnje pune me id: " + jobId);
		}

	}

	/**
	 * The method cancel the application that a person has done.
	 * 
	 * @param employeName the name of the applicants that has applyied before in
	 *                    this job
	 * @param jobTitle    the title of the job from which the applicant want to
	 *                    cancel
	 * @param id          the job identifier
	 */
	public void cancelApplication(String employeName, String jobTitle, long id) {
		Employee em = null;
		Iterator<Map.Entry<Job, List<Employee>>> iterator = listOfAplications.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Job, List<Employee>> entry = iterator.next();
			Job job = entry.getKey();
			List<Employee> applicants = entry.getValue();

			for (Employee iterate : applicants) {
				if (job.getId() == id && job.getTitle().equalsIgnoreCase(jobTitle)
						&& iterate.getName().equals(employeName)) {
					em = iterate;
					applicants.remove(em);
					job.removeApplicant(em);
					System.out.println(employeName + " aplikimi juaj per pozicionin e punes ne : " + jobTitle
							+ " u anullua me sukses!");
					if (applicants.isEmpty()) {
						iterator.remove();
					}

					return;
				}
			}
		}

		System.out.println(employeName + " nuk u gjet asnje aplikim per pozicionin: " + jobTitle);
	}

	/**
	 * The method delete a specific job that has posted in the application
	 * 
	 * @param employerName name of the company that has posted that job
	 * @param jobId        the identifier of the job the company will delete
	 * 
	 */
	public void deleteJobs(long jobId) { // funksionon
		Job jobToBeRemoved = null;

		for (Map.Entry<Integer, Job> entry : saveJobs.entrySet()) {
			if (entry.getValue().getId() == jobId) {
				jobToBeRemoved = entry.getValue();
				break;
			}
		}

		if (jobToBeRemoved != null) {
			listOfPublishedJobs.remove(jobId);
			saveJobs.remove(jobId);
			timePost.remove(jobId);
			jobsOfCompany.remove(jobId);
			jobsPerCompany.entrySet().removeIf(entry -> entry.getValue().contains(jobId));

			System.out.println(
					"Job with id: " + jobId + " & title " + jobToBeRemoved.getTitle() + " removed successfully!");
		} else {
			System.out.println("Job with id: " + jobId + " does not exist.");
		}
	}

	/**
	 * The method modify the existing Job
	 * 
	 * @param jobId       identifier the job that want to modify
	 * @param newTitle    the new name that the job will have
	 * @param newLocation the new type of the job
	 * @param newDeadline the new deadline of the job
	 * 
	 */
	public void updateJob(int jobId, String newTitle, OnsiteOrRemote newLocation, LocalDate newDeadline) { // funksionon
		Job jobToBeModified = null;
		if (!saveJobs.isEmpty() || saveJobs != null) {
			for (Map.Entry<Integer, Job> jobs : saveJobs.entrySet()) {
				if (jobs.getKey() == jobId) {
					int keyId = jobs.getKey();
					jobToBeModified = jobs.getValue();
				}
			}
			if (jobToBeModified != null) {
				listOfPublishedJobs.remove(jobToBeModified);
				timePost.remove(jobToBeModified);
				jobToBeModified.setTitle(newTitle);
				jobToBeModified.setLocation(newLocation);
				try {
					jobToBeModified.setDeadline(newDeadline);
				} catch (DateNotValidException e) {
					e.printStackTrace();
				}
				System.out.println("Job modified: " + jobToBeModified);
				saveJobs.put(jobId, jobToBeModified);
				listOfPublishedJobs.add(jobToBeModified);
				timePost.put(LocalDate.now(), jobToBeModified);
				System.out.println(
						"Job with id: " + jobId + " & title " + jobToBeModified.getTitle() + " u modifikua me sukses!");
			} else {
				System.out.println("Nuk ekziston pune me nje id te tille");
			}
		}

	}

	/**
	 * The method will order the jobs in the ascending order
	 * 
	 * @return the method will return a list with jobs
	 * 
	 */
	public List<Job> jobsInAscendingOrder() { // DONE
		int num = 1;
		List<Job> newList = new ArrayList<>();
		for (Job job : listOfPublishedJobs) {
			newList.add(job);
		}
		Collections.sort(newList, Comparator.comparing(Job::getDeadline));
		for (Job job : newList) {
			System.out.println(num++ + ". " + job);
		}
		return newList;
	}

	/**
	 * The method will return all jobs with type remote
	 * 
	 * @return returns a list of remote's jobs
	 */
	public List<Job> remoteJobs() { // ok
		return listOfPublishedJobs.stream().filter(job -> job.getLocation() == OnsiteOrRemote.REMOTE).toList();
	}

	/**
	 * The method will return all jobs with type Office
	 * 
	 * @return returns a list of office's jobs
	 */
	public List<Job> officeJobs() { // ok
		return listOfPublishedJobs.stream().filter(job -> job.getLocation() == OnsiteOrRemote.ONSITE).toList();
	}

	/**
	 * The method will display all characteristics of a job
	 * 
	 * @param title title of the job that have characteristics
	 * @param jobId identifier of the job
	 */
	public void characteristicsOfAJobPosition(String title, long jobId) { // ok
		Optional<Job> specificJob = listOfPublishedJobs.stream()
				.filter(job -> job.getId() == jobId && job.getTitle().equalsIgnoreCase(title)).findFirst();

		if (specificJob.isPresent()) {
			Job job = specificJob.get();
			System.out.println("|-------Karakteristikat e pozicionit " + title + " -------|");
			System.out.println(GREEN + "\n\tTitulli punes:" + RESET + job.getTitle() + GREEN
					+ "\n\tPershkrimi i punes: " + RESET + job.getDescription() + GREEN
					+ "\n\tDeadline per te aplikuar: " + RESET + job.getDeadline());
			// System.out.println("|-------------------------------------------------|");
		} else {
			System.out.println("Ndodhi nje gabim. Pozicioni i punes qe kerkoni nuk u gjet.");
		}
	}

	// nese kalon deadline nuk mund te regjistrohesh me te
	// nje kompani mund te postoj deri ne 2 pozicione, nga dy e siper duhet te
	// paguaj

	// jobs per company // Ikub -- 3 TCT -- 2
	public Map<String, Integer> numberOfJobsPerCompany(Company com) { // NUK FUNKSIONON
		Map<String, Integer> companyIntegerMap = new HashMap<>();
		for (Map.Entry<Job, Company> entry : punetPerKOmpani.entrySet()) {
			Company company = entry.getValue();
			System.out.println("Kompania:" + company);
			if (companyIntegerMap.containsKey(company)) {
				companyIntegerMap.put(company.getName(), companyIntegerMap.get(company) + 1);
			} else {
				companyIntegerMap.put(company.getName(), 1);
			}
		}

		return companyIntegerMap;
	}

	public List<Job> printJobsForCompany(Company company) { // i printon double
		List<Job> jobsInComp = new ArrayList<>();
		Job job = null;
		Company komp = null;
		if (!jobsPerCompany.isEmpty() || jobsPerCompany != null) {
			for (Map.Entry<Company, List<Job>> jobsMap : jobsPerCompany.entrySet()) {
				komp = jobsMap.getKey();
				if (komp != null && komp.equals(company)) {
					List<Job> jobsIn = jobsMap.getValue();

					if (jobsIn != null) {
						for (Job j : jobsIn) {
							jobsInComp.add(j);
						}
					}
				}
			}

		} else {
			System.out.println(company.getName() + " nuk ka asnje pune te publikuar");
		}
		return jobsInComp;
	}

	/**
	 * This method should send a thanks message to applicants
	 */
	public void sendThankYouMessage(String recipientEmail) {
		System.out.println(RED + "\t\t|-----------To: " + recipientEmail + "-------------------|" + RESET);
		// System.out.println("Subject: Thank You for Your Job Application");
		System.out.println(RED + "|---Thank you for applying! We appreciate your interest in our Company---|" + RESET);
		System.out.println();
	}

	// kthe listen e aplikanteve per nje pozicion
	public List<Employee> getApplicantsPerJob(String jobTitle, long id) { // FUNKSIONON
		List<Employee> applicants = new ArrayList<>();
		if (!listOfAplications.isEmpty() || listOfAplications != null) {
			for (Map.Entry<Job, List<Employee>> list : listOfAplications.entrySet()) {
				Job job = list.getKey();
				if (job != null && job.getTitle().equals(jobTitle) && job.getId() == id) {
					List<Employee> empls = list.getValue();
					if (empls != null) {
						for (Employee em : empls) {
							if (em != null) {
								applicants.add(em);
							}
						}
					}
				}
			}
		}
		return applicants;
	}

	// kthen nje liste te aplikanteve qe do te vazhdojne nefazen e dyte - intervista

	public List<Employee> vazhdojneMeIntervistenAplikantet(String jobTitle, long id) { // funksionon
		List<Employee> aplikantetFiltruar = getApplicantsPerJob(jobTitle, id);
		List<Employee> lisaEmp = new ArrayList<>();
		if (aplikantetFiltruar != null) {
			for (Employee emp : aplikantetFiltruar) {
				if (emp.getYearsOfExp() > MIN_YEARS_FOR_APPLICANTS) {
					lisaEmp.add(emp);
				}
			}

		} else {
			System.out.println("Lista e aplikanteve eshte bosh");

		}
		return lisaEmp;
	}

	public List<Map.Entry<String, String>> searchJob(String title) {
		List<Job> jobTitles = new ArrayList<>();
		int count = 0;
		List<Map.Entry<String, String>> matchingJobs = new ArrayList<>();
		for (Map.Entry<Company, List<Job>> findJobs : jobsPerCompany.entrySet()) {
			Company company = findJobs.getKey();
			List<Job> companyJobs = findJobs.getValue();

			for (Job job : companyJobs) {
				if (job.getTitle().equalsIgnoreCase(title)) {
					matchingJobs.add(new AbstractMap.SimpleEntry<>(company.getName(), job.getTitle()));
					count++;
				}
			}
		}
		System.out.println("Numri i jobs me titull " + title + " eshte: " + count);
		return matchingJobs;
	}

	// Ikub - Java
	public void printJobsPerCompany() {
		for (Map.Entry<Company, List<Job>> entry : jobsPerCompany.entrySet()) {
			Company company = entry.getKey();
			List<Job> jobs = entry.getValue();

			for (Job job : jobs) {
				System.out.println(company.getName() + " has posted '" + job.getTitle()
						+ ".' Aplikimi vazhdon deri ne date: " + job.getDeadline());
			}
		}
	}

	// USERI
	public Company findCompanyWithMostJobs() {
		Company companyWithMostJobs = null;
		int maxJobCount = 0;

		// Iterojme nder kompanite dhe gjejme kompanine qe ka postuarme shume pune
		for (Map.Entry<Company, List<Job>> entry : jobsPerCompany.entrySet()) {
			int jobCount = entry.getValue().size();
			if (jobCount > maxJobCount) {
				maxJobCount = jobCount;
				companyWithMostJobs = entry.getKey();
			}
		}

		return companyWithMostJobs;
	}

	// Kompania -numrine aplikanteve per nje pozicion
	public int getNumberOfApplicantsForJob(String jobTitle, long jobId) { // sakte
		for (Job job : listOfPublishedJobs) {
			if (job.getTitle().equals(jobTitle) && job.getId() == jobId) {
				return job.getApplicants().size();
			}
		}
		return -1; // nuk ekziston

	}

	public List<Employee> getApplicantsForJob(String jobTitle, long jobId) {
		for (Job job : listOfPublishedJobs) {
			if (job.getTitle().equals(jobTitle) && job.getId() == jobId) {
				return job.getApplicants();
			}
		}
		return null;
	}

	// Useri - per punet qe eshte postuar me shume
	public String getMostPostedJobPosition() {
		Map<String, Integer> jobPositionCounts = new HashMap<>();

		for (Job job : listOfPublishedJobs) {
			String jobPosition = job.getTitle();
			jobPositionCounts.put(jobPosition, jobPositionCounts.getOrDefault(jobPosition, 0) + 1);
		}

		String mostPostedJobPosition = null;
		int maxJobPositionCount = 0;

		for (Map.Entry<String, Integer> entry : jobPositionCounts.entrySet()) {
			if (entry.getValue() > maxJobPositionCount) {
				maxJobPositionCount = entry.getValue();
				mostPostedJobPosition = entry.getKey();
			}
		}

		return mostPostedJobPosition;
	}

	// PER USERIN
	public String getRarelyPostedJobPosition() {
		Map<String, Integer> jobPositionCounts = new HashMap<>();

		for (Job job : listOfPublishedJobs) {
			String jobPosition = job.getTitle();
			jobPositionCounts.put(jobPosition, jobPositionCounts.getOrDefault(jobPosition, 0) + 1);
		}

		String rarelyPostedJobPosition = null;
		int minJobPositionCount = Integer.MAX_VALUE;

		for (Map.Entry<String, Integer> entry : jobPositionCounts.entrySet()) {
			if (entry.getValue() < minJobPositionCount) {
				minJobPositionCount = entry.getValue();
				rarelyPostedJobPosition = entry.getKey();
			}
		}

		return rarelyPostedJobPosition;
	}

	// how many days till the deadline
	public long daysRemainingForJob(String jobTitle, long jobId) {
		for (Job job : listOfPublishedJobs) {
			if (job.getTitle().equals(jobTitle) && job.getId() == jobId) {
				LocalDate currentDate = LocalDate.now();
				LocalDate deadline = job.getDeadline();

				if (currentDate.isAfter(deadline)) {
					return 0;
				}

				return ChronoUnit.DAYS.between(currentDate, deadline);
			}
		}

		return -1;
	}

	// PER USERIN DHE PER KOMPANINE
	public int calculateCostForCompany(Company company) {
		int numberOfJobs = 0;

		// Count the number of jobs posted by the company
		for (Map.Entry<Company, List<Job>> entry : jobsPerCompany.entrySet()) {
			if (entry.getKey().equals(company)) {
				numberOfJobs = entry.getValue().size();
				break;
			}
		}

		int additionalJobs = Math.max(0, numberOfJobs - 2);
		int cost = additionalJobs * 20;

		return cost;
	}

	public Map<Company, Integer> calculateEarningsAndCountCompanies(Month specificMonth) {
		Map<Company, Integer> earningsPerCompany = new HashMap<>();

		for (Map.Entry<Company, List<Job>> entry : jobsPerCompany.entrySet()) {
			Company company = entry.getKey();
			List<Job> jobs = entry.getValue();

			long jobsInMonth = jobs.stream().filter(job -> job.getDeadline().getMonth() == specificMonth).count(); 

			if (jobsInMonth > 0) {
				int additionalJobs = (int) Math.max(0, jobsInMonth - 2);
				int earnings = additionalJobs * COST_PER_JOB;

				earningsPerCompany.put(company, earnings);
			}
		}

		return earningsPerCompany;
	}

	public List<Job> getAllApplicationsByJobSeeker(Employee jobSeeker) {
		return jobSeeker.getJobsApplied();
	}

	public List<Job> getAllJobsByCompany(Company company) {
		return jobsPerCompany.getOrDefault(company, List.of());
	}

}
