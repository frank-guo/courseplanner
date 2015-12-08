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
@Table(name = "teaches") 
public class Teaches {		
	@EmbeddedId
	private TeachesId teachesId; 
		
	public Teaches() {}

	public Teaches(TeachesId teachesId) {
		super();
		this.teachesId = teachesId;
	}

	public TeachesId getTeachesId() {
		return teachesId;
	}

	public void setTeachesId(TeachesId teachesId) {
		this.teachesId = teachesId;
	}

	

}

