package cmpt470.group7.project.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import cmpt470.group7.project.model.Student;

public interface StudentService {
	public void add(Student student);
	public void edit(Student student);
	@Secured ({"ROLE_ADMIN"})
	public void delete(int studentId);
	public Student getStudent(int studentId);
	@SuppressWarnings("rawtypes")
	public List getAllStudent();
}
