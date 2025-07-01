package model;

public class StudentRegister {
    private int id;
    private String studentId;
    private String semester;
    private boolean registered;
    private boolean feePaid;
    private String registerDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	public boolean isFeePaid() {
		return feePaid;
	}
	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

    // getter/setter
}