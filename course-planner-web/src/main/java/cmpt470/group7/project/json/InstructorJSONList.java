package cmpt470.group7.project.json;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonAutoDetect
public class InstructorJSONList {

	@JsonProperty
	private List<InstructorJSON> instructorJSONs;


	public List<InstructorJSON> getInstructorJSONs() {
		return instructorJSONs;
	}

	public void setInstructorJSONs(List<InstructorJSON> instructorJSONs) {
		this.instructorJSONs = instructorJSONs;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}