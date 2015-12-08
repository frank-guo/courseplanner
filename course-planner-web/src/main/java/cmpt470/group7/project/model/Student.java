package cmpt470.group7.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student") 
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // auto increase
	@Column(name = "studentId")
	private int studentId;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "programName")
	private String programName;
	@Column(name = "email")
	private String email;
	
	
	public Student(){}
	public Student(int studentId, String firstname, String lastname, String programName, String email) {
		super();
		this.studentId = studentId;
		this.firstname = firstname;
		this.lastname = lastname;		
		this.programName = programName;	
		this.email = email;	
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}	
}
