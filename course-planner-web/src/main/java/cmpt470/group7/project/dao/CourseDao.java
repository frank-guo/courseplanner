package cmpt470.group7.project.dao;

import java.util.List;

import cmpt470.group7.project.model.Course;

public interface CourseDao {
	public void add(Course course);
	public void edit(Course course);
	public void delete(String courseId);
	public Course getCourse(String courseId);
	public List<Course> getAllCourse();
}
