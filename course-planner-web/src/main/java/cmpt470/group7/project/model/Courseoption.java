package cmpt470.group7.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "courseoption") 
public class Courseoption {		
	@EmbeddedId
	private CourseoptionId courseoptionId; 
	
	@Column
	private String campusId;
	
	public Courseoption() {}
	public Courseoption(CourseoptionId courseoptionId, String campusId) {
		super();
		this.courseoptionId = courseoptionId;
		this.campusId = campusId;
	}
	public CourseoptionId getCourseoptionId() {
		return courseoptionId;
	}
	public void setCourseoptionId(CourseoptionId courseoptionId) {
		this.courseoptionId = courseoptionId;
	}
	public String getCampusId() {
		return campusId;
	}
	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

}

