package cmpt470.group7.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Instructor") 
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // auto increase
	@Column(name = "instructorId")
	private String instructorId;
	@Column(name = "name")
	private String name;
	@Column(name = "rating")
	private float rating;
	@Column(name = "easiness")
	private float easiness;
	@Column(name = "helpfulness")
	private float helpfulness;
	@Column(name = "votes")
	private int votes;
	@Column(name = "hot")
	private int hot;
	
	public Instructor(String instructorId, String name, float rating,
			float easiness, float helpfulness, int votes, int hot) {
		super();
		this.instructorId = instructorId;
		this.name = name;
		this.rating = rating;
		this.easiness = easiness;
		this.helpfulness = helpfulness;
		this.votes = votes;
		this.hot = hot;
	}

	public Instructor() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}
	
}
	
	
	
	