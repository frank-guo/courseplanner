package cmpt470.group7.project.dao;

import java.util.List;

import cmpt470.group7.project.model.Student;

public interface StudentDao {
	public void add(Student student);
	public void edit(Student student);
	public void delete(int studentId);
	public Student getStudent(int studentId);
	public List<Student> getAllStudent();
}
