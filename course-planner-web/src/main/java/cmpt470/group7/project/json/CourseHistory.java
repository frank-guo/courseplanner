package cmpt470.group7.project.json;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class CourseHistory {

	@JsonProperty
	private Integer studentId;

	@JsonProperty
	private List<CourseGrade> courseGrades;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public List<CourseGrade> getCourseGrades() {
		return courseGrades;
	}

	public void setCourseGrades(List<CourseGrade> courseGrades) {
		this.courseGrades = courseGrades;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@JsonAutoDetect
	public static class CourseGrade {

		@JsonProperty
		private String courseId;

		@JsonProperty
		private float grade;

		public String getCourseId() {
			return courseId;
		}

		public void setCourseId(String courseId) {
			this.courseId = courseId;
		}

		public float getGrade() {
			return grade;
		}

		public void setGrade(float grade) {
			this.grade = grade;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this,
					ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

}
