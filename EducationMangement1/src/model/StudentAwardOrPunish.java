package model;

import java.io.Serializable;
import java.sql.Date;

public class StudentAwardOrPunish {
    private int id;
    private String studentId;
    private String type; // 奖励或处分
    private String title;
    private String description;
    private Serializable date;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Serializable getDate() {
		return date;
	}
	public void setDate(Serializable date2) {
		this.date = date2;
	}
    

    // getter/setter
}