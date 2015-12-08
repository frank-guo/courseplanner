package cmpt470.group7.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class CourseplanId implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "studentId")
    private int studentId;
	

	@Column(name = "courseId")
    private String courseId;

    @Column(name = "sectionId")
    private String sectionId;
    
    @Column(name = "semesterId")
    private int semesterId;

	public CourseplanId(int studentId, String courseId, String sectionId,
			int semesterId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.sectionId = sectionId;
		this.semesterId = semesterId;
	}

	public CourseplanId() {
		// TODO Auto-generated constructor stub
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public int getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

