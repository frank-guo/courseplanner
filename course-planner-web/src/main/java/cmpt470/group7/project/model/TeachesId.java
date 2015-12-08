package cmpt470.group7.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class TeachesId implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Column(name = "instructorId")
    private String instructorId;
	
	@Column(name = "courseId")
    private String courseId;

    @Column(name = "sectionId")
    private String sectionId;
    
    @Column(name = "semesterId")
    private Integer semesterId;
    


    public TeachesId() {}

	



	public TeachesId(String instructorId, String courseId, String sectionId,
			Integer semesterId) {
		super();
		this.instructorId = instructorId;
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

	public Integer getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(Integer semesterId) {
		this.semesterId = semesterId;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}
	
	
}


