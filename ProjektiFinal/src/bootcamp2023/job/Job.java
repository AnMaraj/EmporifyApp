package bootcamp2023.job;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bootcamp2023.job.exception.DateNotValidException;

public class Job implements Comparable<Job> {
	private static long idCounter = 1;
    private long id;
    private String title;
    private OnsiteOrRemote location;
    private LocalDate deadline;
    private String description;
    private List<Employee> applicants;
    private Company company;

    public Job(String title, OnsiteOrRemote location, LocalDate deadline, String description) throws DateNotValidException {
        this.id = idCounter++;
        this.title = title;
        this.location = location;
        setDeadline(deadline);
        this.description = description;
        this.applicants = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
		this.title = title;
	}
    public void setLocation(OnsiteOrRemote location) {
		this.location = location;
	}
    public void setDeadline(LocalDate deadline) throws DateNotValidException {
    	if(deadline.compareTo(LocalDate.now()) < 0) {
			throw new DateNotValidException("Data nuk mund te jete me e vogel se data e sotme.");
		}
		this.deadline = deadline;	}

    public OnsiteOrRemote getLocation() {
		return location;
	}
    public LocalDate getDeadline() {
		return deadline;
	}
    
    public String getDescription() {
		return description;
	}
    
    public void setDescription(String description) {
		this.description = description;
	}
    
    public List<Employee> getApplicants() {
		return applicants;
	}
    
    public void addApplicant(Employee applicant) {
        applicants.add(applicant);
    }

    public void removeApplicant(Employee applicant) {
        applicants.remove(applicant);
    }
    
    public List<Employee> publikoAPlikantet(){
    	List<Employee> list = new ArrayList<>();
    	for(Employee emp : applicants) {
    		list.add(emp);
    	}
    	return list;
    }

    @Override
    public String toString() {
        return "| '"  +  title + "' " + " me id '"+ id  + "' '"+ location + "' '"+ description +  "' " + deadline + "'"  + " +applicants=" + applicants.size()+ "|";
//                "id=" + id +
//                ", title='" + title + '\'' +
//                "\n, location=" + location + " description: " + description +
//                ", deadline=" + deadline; //+
//                ", applicants=" + applicants.size() + " Aplikantet jane: " +  publikoAPlikantet() + " Description: " +description +
//                '}';
    }

	@Override
	public int compareTo(Job otherJob) {
		 return this.deadline.compareTo(otherJob.deadline);
	}
}
