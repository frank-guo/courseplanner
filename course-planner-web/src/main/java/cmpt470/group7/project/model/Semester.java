package cmpt470.group7.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "semester") 
public class Semester {

	@Id
	@Column
	private String semesterId;

	@Column
	private String description;

	public Semester(String semesterId, String description) {
		this.semesterId = semesterId;
		this.description = description;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
