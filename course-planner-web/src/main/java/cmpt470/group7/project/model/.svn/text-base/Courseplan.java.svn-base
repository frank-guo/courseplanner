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
@Table(name = "courseplan") 
public class Courseplan {		
	@EmbeddedId
	private CourseplanId courseplanId;
	
	public Courseplan() {}

	public Courseplan(CourseplanId courseplanId) {
		super();
		this.courseplanId = courseplanId;
	}

	public CourseplanId getCourseplanId() {
		return courseplanId;
	}

	public void setCourseplanId(CourseplanId courseplanId) {
		this.courseplanId = courseplanId;
	}



}

