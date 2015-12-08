package cmpt470.group7.project.json;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Course information json definition, used in
 * {@link cmpt470.group7.project.controller.RestfulController}
 * 
 * @author yanshi
 * 
 */
@JsonAutoDetect
public class CourseInfo {

	@JsonProperty(required = true)
	private String courseId;

	@JsonProperty(required = true)
	private String title;

	@JsonProperty
	private String description;

	@JsonProperty
	private String wqb;

	@JsonProperty(required = true)
	private Integer credit;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
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

	public String getWqb() {
		return wqb;
	}

	public void setWqb(String wqb) {
		this.wqb = wqb;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
