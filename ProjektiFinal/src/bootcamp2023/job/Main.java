//package bootcamp2023.job;
//
//import java.time.LocalDate;
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//import bootcamp2023.job.exception.DateNotValidException;
//import bootcamp2023.job.exception.EmailNotValidException;
//import bootcamp2023.job.exception.NiptNonValidException;
//
//public class Main {
//	static Scanner scanner = new Scanner(System.in);
//	static String RESET = "\u001B[0m";
//	static String RED = "\u001B[31m";
//	static String GREEN = "\u001B[32m";
//	static String YELLOW = "\u001B[33m";
//
//	public static void main(String[] args) throws EmailNotValidException, NiptNonValidException, DateNotValidException {
//
//		SearchJob searchJob = new SearchJob();
//		while (true) {
//		//	firstMenu();
//			printMenu();
//			int choice;
//			try {
//				choice = scanner.nextInt();
//			} catch (InputMismatchException e) {
//				System.out.println("Inputi jo i sakte!Ju lutem vendosni nje numer.");
//				scanner.nextLine();
//				continue;
//			}
//
//			switch (choice) {
//			case 1:
//				registerEmployee(searchJob);
//				break;
//			case 2:
//				registerEmployer(searchJob);
//				break;
//			case 3:
//				postJob(searchJob);
//				break;
//			case 4:
//				displayAllJobs(searchJob);
//				break;
//			case 5:
//				applyForJob(searchJob);
//				break;
//			case 6:
//				cancelApplication(searchJob);
//				break;
//			case 7:
//				deleteJob(searchJob);
//				break;
//			case 8:
//				modifyJob(searchJob);
//				break;
//			case 9:
//				displayJobAccordingLastDate(searchJob);
//				break;
//			case 10:
//				displayRemoteJobs(searchJob);
//				break;
//			case 11:
//				displayOfficeJobs(searchJob);
//				break;
//			case 12:
//				characteristicsOfAJobPosition(searchJob);
//				break;
//			case 13:
//				getJobById(searchJob);
//				break;
//			case 14:
//				getNumberOfKompany(searchJob);
//				break;
//			case 15:
//				getNumberOfPnekerkues(searchJob);
//				break;
//			case 16:
//				numriJobs(searchJob);
//				break;
//			case 17:
//				displaysJobsPerCompany(searchJob);
//				break;
//
//			case 18:
//				ktheApliantsPerCompany(searchJob);
//				break;
//			case 19:
//				vazhdojneIntervisten(searchJob);
//				break;
//			case 0:
//				System.out.println("Faleminderit qe perdoret app tone Emporify. Goodbye!");
//				System.exit(0);
//				break;
//			default:
//				System.out.println("KUJDES! Vendosni nje nga numrat e shfaqura ne 'Menu'.");
//			}
//		}
//	}
//
//	
//	private static void printMenu() {
//		System.out.println("\n|=====================================================|");
//		System.out.println(RED + "|=================== Emporify System =================|" + RESET);
//		System.out.println("|=====================================================|");
//		System.out.println("1. Log in as Employee"); //
//		System.out.println("2. Log in as Company");
//		System.out.println("3. Post a Job");
//		System.out.println("4. Display All Jobs with Applicants");
//		System.out.println("5. Apply for a Job");
//		System.out.println("6. Cancel Application");
//		System.out.println("7. Delete Job");
//		System.out.println("8. Modify Job");
//		
//		System.out.println("9. Display Jobs in Ascending oorder to Last Date");
//		
//		System.out.println("10. Display Remote Jobs");
//		System.out.println("11. Display Office Jobs");
//		
//		System.out.println("12. Characteristics of a Job Position");
//		System.out.println("13. Get Job by id");
//		
//		System.out.println("14. Display all the Companies in App");
//		System.out.println("15. Display the Employees in Application");
//		System.out.println("16. Number of Jobs per Company");
//		System.out.println("17. Display jobs per Company");
//		System.out.println("18. Display Aplicants for a specific position");
//		System.out.println("19. Aplikantet qe kalojne fazen e pare.");
//		System.out.println("0.  Exit");
//		System.out.println("|=====================================================|");
//		System.out.print("Enter your choice: ");
//	}
//
//	// regjistrimi si Punekerkues
//	private static void registerEmployee(SearchJob searchJob) {
//		System.out.print("Vendosni emrin tuaj: ");
//		String name = scanner.next(); // Antonela
//		System.out.println("Vendosni vitet e eksperiences: ");
//		int yearsOfExp = scanner.nextInt();
//		System.out.println("Vendosni email-in:");
//		while (true) {
//			try {
//				String email = scanner.next(); // anto@gmail.com
//				if (searchJob.employeeExists(new Employee(name, email, yearsOfExp))) {
//					System.out.println("Personi ekziston me keto te dhena, nuk mund te regjistrohet serish");
//					return;
//				} else {
//					searchJob.registerAsEmployee(name, email, yearsOfExp);
//					break;
//				}
//
//			} catch (EmailNotValidException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	// regjistrimi si Kompani
//	private static void registerEmployer(SearchJob searchJob) throws NiptNonValidException {
//		System.out.print("Vendosni emrin e Kompanise: ");
//		String name = scanner.next();
//		System.out.print("Vendosni nipt-in e Kompanise: ");
//		while (true) {
//			try {
//				String companyNipt = scanner.next();
//			if (searchJob.companyExists(new Company(name, companyNipt))) {
//				System.out.println("Person Juridik me keto te dhena ekziston, nuk mund te regjistrohet serish");
//				return;
//			} else {
//
//				searchJob.registerAsEmployer(name, companyNipt);
//				break;
//			}
//		}catch (NiptNonValidException e) {
//			e.printStackTrace();
//		}
//	}
//	}
//
//	// Postimi i nje pozicioni pune
//	private static void postJob(SearchJob searchJob) throws NiptNonValidException, DateNotValidException {
//		System.out.println("A jeni regjistruar me pare si person juridik?");
//		String answer = scanner.next();
//		if (answer.equalsIgnoreCase("po")) {
//			System.out.println("Vendosni nipt-in nese ekziston si Kompani");
//			String nipt = scanner.next();
//			System.out.println("Vendosni emrin e Kompanise");
//			String companyName = scanner.next();
//			while (searchJob.validCompany(nipt, companyName)) {
//				System.out.print("Vendosni pozicionin e punes qe do te postoni: ");
//				String title = scanner.next();
//				System.out.print("Tipi i punes (ONSITE/REMOTE): ");
//				OnsiteOrRemote location = OnsiteOrRemote.valueOf(scanner.next().toUpperCase());
//				System.out.print("Vendosni deadline si ne formatin (YYYY-MM-DD): ");
//				LocalDate deadline = LocalDate.parse(scanner.next());
//				System.out.print("Vendosni pershkrimin e punes qe do te postoni: ");
//				scanner.next();
//				String description = scanner.nextLine();
//				searchJob.postJobs(companyName, nipt, title, location, deadline, description);
//				System.out.println("Deshironi te postoni pozicion tjeter? ");
//				answer = scanner.next();
//				if (!answer.equals("po")) {
//					return;
//				}
//			}
//			System.out.println("Person juridik me kete nipt apo emer nuk ekziston. Regjistrohuni si fillim!.");
//		} else {
//			System.out.println("Shtypni 2 per tu regjistruar fillimisht");
//		}
//	}
//
//	private static void displayAllJobs(SearchJob searchJob) {
//		System.out.println("===== All Jobs =====");
//		searchJob.getAllJobs();
//	}
//
//	private static void getJobById(SearchJob searchJob) {
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
//	}
//
//	private static void applyForJob(SearchJob searchJob) throws EmailNotValidException {
//		System.out.println("A jeni regjistruar me pare si punekerkues?");
//		String answer = scanner.next();
//		if (answer.equalsIgnoreCase("po")) {
//			System.out.println("Vendosni emrin per tu verifikuar");
//			String emri = scanner.next();
//			System.out.println("Vendosni email-in tuaj");
//			String email = scanner.next();
//			System.out.println("Vendosni vitet e eksperiences: ");
//			int yearsOfExp = scanner.nextInt();
//			while (searchJob.validEmployee(emri, email)) {
//				System.out.print("Vendosni id e job position qe po kerkoni te aplikoni : ");
//				long jobId = scanner.nextLong();
//				if (searchJob.validJob(jobId)) {
//					searchJob.applyForJob(emri, email, yearsOfExp, jobId);
//				} else {
//					System.out.println("Pozicioni i punes me id: " + jobId + " nuk ekziston.");
//
//				}
//				System.out.println("Deshironi te aplikoni per pozicion tjeter? ");
//				answer = scanner.next();
//				if (!answer.equalsIgnoreCase("po")) {
//					return;
//				}
//			}
//			System.out.println("Person i regjistruar me kete emer nuk ekziston. Regjistrohuni si fillim.");
//		} else {
//			System.out.println("Shtypni 1 per tu regjistruar fillimisht");
//		}
//
//	}
//
//	// une qe kam aplikuar dua ta anulloj
//	private static void cancelApplication(SearchJob searchJob) {
//		System.out.println("Vendosni emrin tuaj: ");
//		String myName = scanner.next();
//		System.out.print("Vendosni titullin  e punes");
//		String title = scanner.next();
//		System.out.print("Vendosni id e job position: ");
//		long jobId = scanner.nextLong();
//
//		searchJob.cancelApplication(myName, title, jobId);
//	}
//
//	private static void deleteJob(SearchJob searchJob) {
//		System.out.print("Vendosni emrin e Kompanise");
//		String employerName = scanner.next();
//		System.out.print("Vendosni id e job position: ");
//		long jobId = scanner.nextLong();
//
//		searchJob.deleteJobs(employerName, jobId);
//	}
//
//	private static void modifyJob(SearchJob searchJob) {
//		System.out.print("Vendosni id e job position: ");
//		int jobId = scanner.nextInt();
//		System.out.print("Vendosni titullin e ri te punes: ");
//		String newTitle = scanner.next();
//		System.out.print("Vendosni tipin e punes (ONSITE/REMOTE): ");
//		OnsiteOrRemote newLocation = OnsiteOrRemote.valueOf(scanner.next().toUpperCase());
//		System.out.print("Vendosni deadline (YYYY-MM-DD): ");
//		LocalDate newDeadline = LocalDate.parse(scanner.next());
//
//		searchJob.updateJob(jobId, newTitle, newLocation, newDeadline);
//	}
//
//	private static void displayJobAccordingLastDate(SearchJob searchJob) { /// U BE
//		System.out.println("Shfaqi punet sipas dates se fundit");
//		searchJob.jobsInAscendingOrder();
//	}
//
//	private static void displayRemoteJobs(SearchJob searchJob) { // SAKTE
//		List<Job> remoteJobs = searchJob.remoteJobs();
//		System.out.println("===== Remote Jobs =====");
//		remoteJobs.forEach(System.out::println);
//	}
//
//	private static void displayOfficeJobs(SearchJob searchJob) {// SAKTE
//		List<Job> officeJobs = searchJob.officeJobs();
//		System.out.println("===== Office Jobs =====");
//		officeJobs.forEach(System.out::println);
//	}
//
//	private static void characteristicsOfAJobPosition(SearchJob searchJob) { // SAKTE
//		System.out.print("Vendosni titullin e pozicionit te punes: ");
//		String title = scanner.next();
//		System.out.print("Vendosni id e job position: ");
//		long jobId = scanner.nextLong();
//
//		searchJob.characteristicsOfAJobPosition(title, jobId);
//	}
//
//	private static void getNumberOfKompany(SearchJob searchJob) {
//		searchJob.displayAllEmployees();
//
//	}
//
//	private static void getNumberOfPnekerkues(SearchJob searchJob) {
//		searchJob.displayAllEmployers();
//	}
//
//	private static void numriJobs(SearchJob searchJob) throws NiptNonValidException {
////		System.out.print("Vendosni emrin e Kompanise: ");
////		String employerName = scanner.next();
////		System.out.println("Vendosni niptin e kompanise: ");
////		String nipt = scanner.next();
////		Map<Company, Integer> map = searchJob.numberOfJobsPerCompany(new Company(employerName, nipt));
//		//Map<String, Integer> map = searchJob.numberOfJobsPerCompany();
//
//		//System.out.println(map);
//	}
//
//	private static void displaysJobsPerCompany(SearchJob searchJob) throws NiptNonValidException {
//		System.out.print("Vendosni emrin e Kompanise: ");
//		String employerName = scanner.next();
//		System.out.println("Vendosni niptin e kompanise: ");
//		String nipt = scanner.next();
//		List jobs = searchJob.printJobsForCompany(new Company(employerName, nipt));
//		System.out.println(jobs);
//	}
//
//	private static void ktheApliantsPerCompany(SearchJob searchJob) {
//		System.out.print("Vendosni titullin e pozicionit te punes: ");
//		String title = scanner.next();
//		System.out.print("Vendosni id e job position: ");
//		long jobId = scanner.nextLong();
//
//		List empl = searchJob.getApplicantsPerJob(title, jobId);
//		System.out.println(empl);
//	}
//
//	private static void vazhdojneIntervisten(SearchJob searchJob) {
//		System.out.print("Vendosni titullin e pozicionit te punes: ");
//		String title = scanner.next();
//		System.out.print("Vendosni id e job position: ");
//		long jobId = scanner.nextLong();
//		List empl = searchJob.vazhdojneMeIntervistenAplikantet(title, jobId);
//		System.out.println(empl);
//	}
//
//}
