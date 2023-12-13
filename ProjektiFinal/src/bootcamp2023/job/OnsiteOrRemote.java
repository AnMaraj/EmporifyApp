package bootcamp2023.job;

public enum OnsiteOrRemote {
	ONSITE("Onsite"), REMOTE("Remote");

	private String name;

	private OnsiteOrRemote(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
