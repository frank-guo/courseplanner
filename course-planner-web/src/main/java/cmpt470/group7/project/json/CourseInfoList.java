package cmpt470.group7.project.json;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Used to respond "/course/semester/{semester}.json" GET request
 */
@JsonAutoDetect
public class CourseInfoList {

	@JsonProperty
	private List<CourseInfo> courseInfos;



	public List<CourseInfo> getCourseInfos() {
		return courseInfos;
	}



	public void setCourseInfos(List<CourseInfo> courseInfos) {
		this.courseInfos = courseInfos;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
