package cmpt470.group7.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "course") 
public class Course {
	@Id
	@Column
	private String courseId;
	@Column
	private String title;
	@Column
	private String descrption;
	@Column
	private String wqb;
	@Column
	private int credit;	
	/*
	 @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	 @JoinTable(
	            name="courseoption",
	            joinColumns = @JoinColumn( name="courseId"),
	            inverseJoinColumns = {@JoinColumn( name="classNo"),@JoinColumn( name="semesterId")}
	            //inverseJoinColumns = @JoinColumn( name="classId")
	    )
	 private Set<Class> classes;*/
	
	
	public Course(){}
	public Course(String courseId, String title, String description, String wqb) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.descrption = description;
		this.wqb = wqb;
	}
	
	public Course(String courseId, String title, String descrption, String wqb,
			int credit) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.descrption = descrption;
		this.wqb = wqb;
		this.credit = credit;
	}
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
		return descrption;
	}
	public void setDescription(String descrption) {
		this.descrption = descrption;
	}
	public String getWqb() {
		return wqb;
	}
	public void setWqb(String wqb) {
		this.wqb = wqb;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
}
