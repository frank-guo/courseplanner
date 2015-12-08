package cmpt470.group7.project.json;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class TeachesJSON {
	
	@JsonProperty(required = true)
	private String instructorId;

	@JsonProperty(required = true)
	private String courseId;

	@JsonProperty(required = true)
	private String sectionId;

	@JsonProperty(required = true)
	private Integer semesterId;



	public String getInstructorId() {
		return instructorId;
	}



	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
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



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
