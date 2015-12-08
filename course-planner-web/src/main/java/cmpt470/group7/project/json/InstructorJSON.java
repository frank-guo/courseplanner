package cmpt470.group7.project.json;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class InstructorJSON {
	
	@JsonProperty(required = true)
	private String instructorId;

	@JsonProperty
	private String name;

	@JsonProperty
	private float rating;

	@JsonProperty
	private float easiness;
	
	@JsonProperty
	private float helpfulness;
	
	@JsonProperty
	private Integer votes;
	
	@JsonProperty
	private int hot;	

	
	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
	}






	public float getRating() {
		return rating;
	}






	public void setRating(float rating) {
		this.rating = rating;
	}






	public float getEasiness() {
		return easiness;
	}






	public void setEasiness(float easiness) {
		this.easiness = easiness;
	}






	public float getHelpfulness() {
		return helpfulness;
	}






	public void setHelpfulness(float helpfulness) {
		this.helpfulness = helpfulness;
	}






	public Integer getVotes() {
		return votes;
	}






	public void setVotes(Integer votes) {
		this.votes = votes;
	}



	public void setHot(int hot) {
		this.hot = hot;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
