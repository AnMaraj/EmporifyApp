package bootcamp2023.job;

import java.time.LocalDate;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import bootcamp2023.job.exception.DateNotValidException;
import bootcamp2023.job.exception.EmailNotValidException;
import bootcamp2023.job.exception.InvalidPassword;
import bootcamp2023.job.exception.InvalidUsername;
import bootcamp2023.job.exception.NiptNonValidException;

public class Test3 {
	static Scanner scanner = new Scanner(System.in);
	static SearchJob searchJob = new SearchJob();
	static String RESET = "\u001B[0m";
	static String RED = "\u001B[31m";
	static String GREEN = "\u001B[32m";
	static String YELLOW = "\u001B[33m";

	public static void main(String[] args) throws NiptNonValidException, DateNotValidException, EmailNotValidException,
			InvalidUsername, InvalidPassword {

		// Display login options
		// System.out.println("Choose a role to log in:");
		System.out.println("\n|=====================================================|");
		System.out.println(RED + "|=================== Emporify System =================|" + RESET);
		System.out.println("|=====================================================|");
		System.out.println(GREEN + "\n|----Who are you-----------|" + RESET);
		System.out.println(GREEN + "1.-----Employee------------|" + RESET);
		System.out.println(GREEN + "2.-----Company-------------|" + RESET);
		System.out.println(GREEN + "3.-----User----------------|" + RESET);

		System.out.print("Enter your choice: ");

		while (true) {
			// Get user role choice
			int userRole;
			try {
				userRole = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Inputi jo i sakte!Ju lutem vendosni nje numer.");
				scanner.nextLine();
				continue;
			}
			// Validate user role
			if (userRole == 1) {
				// Log in as Kompani
				System.out.println("Log in as Employee");
				registerEmployee();
			} else if (userRole == 2) {
				// Log in as Employee
				System.out.println("Log in as Company");
				registerEmployer();
			} else if (userRole == 3) {
				System.out.println("Log in as User");
				saveCurrentUser();
			} else {
				System.out.println("Invalid choice. Choose 1 -3.");
			}
		}
	}

	 public static void saveCurrentUser() throws NiptNonValidException, InvalidUsername, InvalidPassword {
	       // try (Scanner scanner = new Scanner(System.in)) {
	            while (true) {
	                System.out.print("Vendosni emrin tuaj: ");
	                String userName = scanner.next();

	                System.out.print("Vendosni paswordin: ");
	                String password = scanner.next();
	                try {
	                    if (UserApplication.EMPORIFY.getUsername().equalsIgnoreCase(userName)
	                            && UserApplication.EMPORIFY.getPassword().equals(password)) {
	                        System.out.println("You log in!");
	                            System.out.println("Zgjidhni nje nga veprimet qe doni te kryeni: ");
	                            int option;
	                            do {
	        						System.out.println(GREEN + "\"|================User Options:=================|" + RESET);
	        						System.out.println("1. Kompania qe ka postuar me shume pozicione pune.");
	        						System.out.println("2. Pozicioni i punes me i publikuar.");
	        						System.out.println("3. Pozicioni i punes i publikuar me rralle");
	        						System.out.println("4. Kostoja e seciles Kompani nse kakaluar me shume se 3 Job posted.");
	        						System.out
	        								.println("5. Fitmi ne muaj nga tarifa e Kompanive qe kane postuar me shume se 3 Jobs");
	        						System.out.println("6. Log Out");
	        						System.out.println(GREEN + "|====================================================|" + RED);
	        						System.out.print("Enter your choice: ");
	        						option = scanner.nextInt();
	                            switch (option) {
	                                case 1:
	                                    companyWithMostJobs();
	                                    break;
	                                case 2:
	                                    punaMeEPublikuar();
	                                    break;
	                                case 3:
	                                    punaMePakEPOstuar();
	                                    break;
	                                case 4:
	                                    kostojaKompanive();
	                                    break;
	                                case 5:
	                                    calkulateFitiminperMuaj();
	                                    break;
	                                case 6:
	                                    System.out.println("Logged Out as User");
	                                    System.out.println("Faleminderit qe perdoret EMPORIFY!");
	                                    return;
	                                default:
	                                    System.out.println("Invalid option. Please choose 1 - 6.");
	                            }
	                        }while(option != 6);
	                            break;
	                     
	                    }else {
	                        System.out.println("Invalid username or password. Try again!");
	                    }
	                } catch (InvalidUsername | InvalidPassword e) {
	                    System.out.println("Username or password is not correct. Try again!");
	                }
	            }
	        }

	// Method to display options for Employer
	private static void registerEmployer() throws NiptNonValidException, DateNotValidException, EmailNotValidException {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Vendosni emrin e Kompanise: ");
		String name = scanner.nextLine();
		System.out.print("Vendosni nipt-in e Kompanise: ");
		while (true) {
			try {
				String companyNipt = scanner.nextLine();
				if (!searchJob.companyExists(new Company(name, companyNipt))) {

					searchJob.registerAsEmployer(name, companyNipt);
					// System.out.println("Person Juridik me keto te dhena ekziston, nuk mund te
					// regjistrohet serish");
					// return;
				}
				// searchJob.registerAsEmployer(name, companyNipt);
				System.out.println("Zgjidhni nje nga veprimet qe doni te kryeni: ");
				int option;

				do {
					System.out.println(GREEN + "\"|================Employer Options:=================|" + RESET);
					// System.out.println("1. Register as Company");
					System.out.println("1. Post a Job");
					System.out.println("2. Delete");
					System.out.println("3. Modify Job");
					System.out.println("4. Display All Jobs with Applicants");
					System.out.println("5. Display Aplicants for a specific position");
					System.out.println("6. Aplikantet qe kalojne fazen e pare.");
					System.out.println("7. Kthe numrin e punekerkueseve");
					System.out.println("8. Kthe numrin e aplikanteve");
					System.out.println("9. Kthesa aplikante ka per secilen kompani.");
					System.out.println("10. Log Out");
					System.out.println(GREEN + "|====================================================|" + RED);
					System.out.print("Enter your choice: ");

					option = scanner.nextInt();

					switch (option) {
					case 1:
						postJob(name, companyNipt);
						break;
					case 2:
						deleteJob();
						break;
					case 3:
						modifyJob(searchJob);
						break;
					case 4:
						displaysJobsPerCompany(name, companyNipt);
						break;
					case 5:
						ktheApliantsPerCompany(searchJob); // e njejte me 10
						break;
					case 6:
						vazhdojneIntervisten(searchJob); // ata qe filtrohen ne baze te eksperiences
						break;
					case 7:
						getNumberOfPnekerkues(searchJob); // ata qe thjeshte jane rehjistruar si punekerkues por nuk
															// kane aplikuar
						break;
					case 8:
						numriAplikanteve(); //
						break;
					case 9:
						ktheApliantsPerCompany(searchJob); // kthen me emer aplikantet
						break;
					case 10:
						System.out.println("Logged Out as Employer \nFaleminderit qe perdoret EMPORIFY!");
						break;
					default:
						System.out.println("Invalid option. Please choose 1 - 9.");
					}
				} while (option != 10);
				break;

			} catch (NiptNonValidException e) {
				e.printStackTrace();

			}
		}
	}

	// Method to display options for Employee
	private static void registerEmployee() throws EmailNotValidException, NiptNonValidException {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Vendosni emrin tuaj: ");
		String name = scanner.next(); // Antonela
		System.out.print("Vendosni vitet e eksperiences: ");
		int yearsOfExp = scanner.nextInt();
		System.out.print("Vendosni email-in:");
		String email;
		while (true) {
			try {
				email = scanner.next(); // anto@gmail.com
				if (!searchJob.employeeExists(new Employee(name, email, yearsOfExp))) {
					searchJob.registerAsEmployee(name, email, yearsOfExp);
					// return;
				}
				System.out.print("Enter your choice: ");
				int choice;
				do {

					System.out.println(GREEN + "|\n==================Employee Options:================|" + RESET);
					System.out.println("1. Apply for a Job");
					System.out.println("2. Cancel Application for a job you have applied");
					System.out.println("3. Display Jobs in Ascending order to Last Date");
					System.out.println("4. Display Remote Jobs");
					System.out.println("5. Display Office Jobs");
					System.out.println("6. Characteristics of a Job Position");
					//System.out.println("7. Get job with a specific id and the company name");
					System.out.println("7. Display Companies that have posted in EMPORIFY");
					System.out.println("8. Display All Jobs");
					System.out.println("9. Search Job with their title");
					System.out.println("10. Display Job position according to Company");
					System.out.println("11. Log out");
					// System.out.println("10. Display jobs per Company");
					// System.out.println("11. Display all the Companies in App");
					System.out.println(GREEN + "|=====================================================|" + RESET);
					choice = scanner.nextInt();

					switch (choice) {
					case 1:
						applyForJob(name, email, yearsOfExp);
						break;
					case 2:
						cancelApplication(name);
						break;

					case 3:
						displayJobAccordingLastDate(searchJob);
						break;
					case 4:
						displayRemoteJobs(searchJob);
						break;
					case 5:
						displayOfficeJobs(searchJob);
						break;
					case 6:
						characteristicsOfAJobPosition(searchJob);
						break;
//					case 7:
//						getJobAndCompanyWithJobId(searchJob);
//						break;
					case 7:
						getNumberOfKompany(searchJob);
						break;
					case 8:
						displayAllJobs(searchJob);
						break;
					case 9:
						kerkoPuneSipasTitullit();
						break;
					case 10:
						printoPunetSIpasKompanise();
						break;
					case 11:
						System.out.println("Logged out as Employee. \\nFaleminderit qe perdoret EMPORIFY!");
						break;
					default:
						System.out.println("Invalid option. Please choose 1 - 10");
					}
				} while (choice != 11);
				break;

			} catch (EmailNotValidException e) {
				e.printStackTrace();
				
			}
		}

	}

	private static void postJob(String name, String nipt) throws NiptNonValidException, DateNotValidException {
		System.out.print("Vendosni pozicionin e punes qe do te postoni: ");
		String title = scanner.next();
		System.out.print("Tipi i punes (ONSITE/REMOTE): ");
		OnsiteOrRemote location = OnsiteOrRemote.valueOf(scanner.next().toUpperCase());
		System.out.print("Vendosni deadline si ne formatin (YYYY-MM-DD): ");
		LocalDate deadline = LocalDate.parse(scanner.next());
		System.out.print("Vendosni pershkrimin e punes qe do te postoni: ");
		scanner.next();
		String description = scanner.nextLine();
		searchJob.postJobs(name, nipt, title, location, deadline, description);

		// new Company(name, nipt).addJob(new Job(title, location, deadline,
		// description));
		// System.out.println("Deshironi te postoni pozicion tjeter? ");

	}

	private static void deleteJob() {
		System.out.print("Vendosni id e job position: ");
		long jobId = scanner.nextLong();
		searchJob.deleteJobs(jobId);
	}

	private static void modifyJob(SearchJob searchJob) {
		System.out.print("Vendosni id e job position: ");
		int jobId = scanner.nextInt();
		System.out.print("Vendosni titullin e ri te punes: ");
		String newTitle = scanner.next();
		System.out.print("Vendosni tipin e punes (ONSITE/REMOTE): ");
		OnsiteOrRemote newLocation = OnsiteOrRemote.valueOf(scanner.next().toUpperCase());
		System.out.print("Vendosni deadline (YYYY-MM-DD): ");
		LocalDate newDeadline = LocalDate.parse(scanner.next());

		searchJob.updateJob(jobId, newTitle, newLocation, newDeadline);
	}

	private static void displaysJobsPerCompany(String name, String nipt) throws NiptNonValidException {
		List jobs = searchJob.printJobsForCompany(new Company(name, nipt));
		int num = 1;
		System.out.println("Punet e publikuara jane: ");
		for (int i = 0; i < jobs.size(); i++) {
			System.out.println(num++ + ". " + jobs.get(i));
		}
	}

	private static void ktheApliantsPerCompany(SearchJob searchJob) {
		System.out.print("Vendosni titullin e pozicionit te punes: ");
		String title = scanner.next();
		System.out.print("Vendosni id e job position: ");
		long jobId = scanner.nextLong();

		List empl = searchJob.getApplicantsPerJob(title, jobId);
		System.out.println(empl);
	}

	private static void vazhdojneIntervisten(SearchJob searchJob) {
		System.out.print("Vendosni titullin e pozicionit te punes: ");
		String title = scanner.next();
		System.out.print("Vendosni id e job position: ");
		long jobId = scanner.nextLong();
		List empl = searchJob.vazhdojneMeIntervistenAplikantet(title, jobId);
		System.out.println(empl);
	}

	private static void getNumberOfPnekerkues(SearchJob searchJob) {
		searchJob.displayAllEmployers();
	}

	/**************************************** Punekerkuesi ********************/
	// regjistrimi si Punekerkues

	private static void applyForJob(String emri, String email, int yearsOfExp) throws EmailNotValidException {
		String answer;
		while (searchJob.employeeExists(new Employee(emri, email, yearsOfExp))) {
			
			System.out.println("Punet e publikuara jane: ");
			displayJobAccordingLastDate(searchJob);
			System.out.print("Vendosni id e job position qe po kerkoni te aplikoni : ");
			long jobId = scanner.nextLong();
			if (searchJob.validJob(jobId)) {
				searchJob.applyForJob(emri, email, yearsOfExp, jobId);
			} else {
				System.out.println("Pozicioni i punes me id: " + jobId + " nuk ekziston.");

			}
			return;
		}

	}

	// une qe kam aplikuar dua ta anulloj
	private static void cancelApplication(String name) {

		System.out.print("Vendosni titullin  e punes: ");
		String title = scanner.next();
		System.out.print("Vendosni id e job position: ");
		long jobId = scanner.nextLong();

		searchJob.cancelApplication(name, title, jobId);
	}

	private static void displayJobAccordingLastDate(SearchJob searchJob) { /// U BE
		System.out.println("Shfaqi punet sipas dates se fundit");
		searchJob.jobsInAscendingOrder();
	}

	private static void displayRemoteJobs(SearchJob searchJob) { // SAKTE
		List<Job> remoteJobs = searchJob.remoteJobs();
		int num = 0;
		System.out.println("===== Remote Jobs =====");
		remoteJobs.forEach(System.out::println);
	}

	private static void displayOfficeJobs(SearchJob searchJob) {// SAKTE
		List<Job> officeJobs = searchJob.officeJobs();
		System.out.println("===== Office Jobs =====");
		officeJobs.forEach(System.out::println);
	}

	private static void characteristicsOfAJobPosition(SearchJob searchJob) { // SAKTE
		System.out.print("Vendosni titullin e pozicionit te punes: ");
		String title = scanner.next();
		System.out.print("Vendosni id e job position: ");
		long jobId = scanner.nextLong();

		searchJob.characteristicsOfAJobPosition(title, jobId);
		long days = searchJob.daysRemainingForJob(title, jobId);
		System.out.println("There are left " + days + " days to deadline.");

	}

	private static void getNumberOfKompany(SearchJob searchJob) {
		searchJob.displayAllEmployees();

	}

//	private static void getJobAndCompanyWithJobId(SearchJob searchJob) {
//
//		System.out.print("Vendosni id e job position: ");
//		long jobId = scanner.nextLong();
//
//		if (searchJob.validJob(jobId)) {
//			Job job = searchJob.getJobByIds(jobId);
//
//		} else {
//			System.out.println("Nuk ka Pozicion te publikuar me kete id");
//		}
//
//	}

	private static void displayAllJobs(SearchJob searchJob) {
		System.out.println("===== All Jobs =====");
		searchJob.getAllJobs();
	}

	private static void viewJobApplicants() {
		System.out.println("Viewing job applicants:");
		// Logic to view job applicants
	}

	private static void kerkoPuneSipasTitullit() {
		System.out.println("Vendosni titullin e punes qe deshironi te beni kerkimin");
		String title = scanner.next();

		List<Map.Entry<String, String>> list = searchJob.searchJob(title);
		System.out.println("Posted by: ");
		System.out.println(list);
	}

	private static void printoPunetSIpasKompanise() {
		searchJob.printJobsPerCompany();
	}

	// E USER -> 3
	private static void companyWithMostJobs() {
		// Company com = searchJob.findCompanyWithMostJobs();
		// System.out.println("Kompania me me shume postime per pune eshte: " + com);
		Company companyWithMostPostedJobs = searchJob.findCompanyWithMostJobs();

		if (companyWithMostPostedJobs != null) {
			System.out.println("Company with the most posted jobs: " + companyWithMostPostedJobs.getName());
		} else {
			System.out.println("No companies or jobs found.");
		}
	}

	// KOmpania merr numrin e aplikanteve per nje pozicion
	public static void numriAplikanteve() {
		System.out.print("Vendosni titullin e pozicionit te punes: ");
		String title = scanner.next();
		System.out.print("Vendosni id e job position: ");
		long jobId = scanner.nextLong();
		int number = searchJob.getNumberOfApplicantsForJob(title, jobId);
		System.out.println("Numri i aplikanteve per pozicionin '" + title + "' eshte:" + number);
	}

	// per USERIN
	public static void punaMeEPublikuar() {
		String job = searchJob.getMostPostedJobPosition();
		System.out.println("Puna me e postuar eshte: " + job);
	}

	// PER USERIN
	public static void punaMePakEPOstuar() {
		String job = searchJob.getRarelyPostedJobPosition();
		System.out.println("Puna e postuar me pak eshte: " + job);
	}

	public static void kostojaKompanive() throws NiptNonValidException {
		System.out.print("Vendosni emrin e Kompanise: ");
		String companyName = scanner.next();
		System.out.print("Vendosni nipt-in e Kompanise: ");
		String companyNipt = scanner.next();
		int kosto = searchJob.calculateCostForCompany(new Company(companyName, companyNipt));
		System.out.println("Kostoja per kompanine " + companyName + " eshte " + kosto);
	}

	// USERIIIII
	public static void calkulateFitiminperMuaj() {
		Month specificMonth = Month.DECEMBER;
		/* Specify the desired month */;
		Map<Company, Integer> earningsPerCompany = searchJob.calculateEarningsAndCountCompanies(specificMonth);

		for (Map.Entry<Company, Integer> entry : earningsPerCompany.entrySet()) {
			System.out.println("Company: " + entry.getKey().getName() + ", Earnings: $" + entry.getValue());
		}
	}

	public static void aplikimetQeKamBere(String emri, String email, int yearsOfExp) throws EmailNotValidException { // nuk
																														// funksionon
		// Employee emp = new Employee(emri, email, yearsOfExp);
		List<Job> applications = searchJob.getAllApplicationsByJobSeeker(new Employee(emri, email, yearsOfExp));

		System.out.println("Applications made by " + emri + ":");
		for (Job job : applications) {
			System.out.println("- " + job.getTitle());
		}
	}
}
