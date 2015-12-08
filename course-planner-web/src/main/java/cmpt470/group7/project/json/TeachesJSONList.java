package cmpt470.group7.project.json;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonAutoDetect
public class TeachesJSONList {

	@JsonProperty
	private List<TeachesJSON> teachesJSONs;



	public List<TeachesJSON> getTeachesJSONs() {
		return teachesJSONs;
	}



	public void setTeachesJSONs(List<TeachesJSON> teachesJSONs) {
		this.teachesJSONs = teachesJSONs;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}