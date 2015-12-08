package cmpt470.group7.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class CourseoptionId implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "courseId")
    private String courseId;

    @Column(name = "sectionId")
    private String sectionId;
    
    @Column(name = "semesterId")
    private int semesterId;

    public CourseoptionId() {}
	public CourseoptionId(String courseId, String sectionId, int semesterId) {
		super();
		this.courseId = courseId;
		this.sectionId = sectionId;
		this.semesterId = semesterId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
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
}

