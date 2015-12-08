package cmpt470.group7.project.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cmpt470.group7.project.model.Instructor;
@Repository
public class InstructorDaoImpl implements InstructorDao {
	@Autowired
	private SessionFactory session;
	
	public void add(Instructor instructor) {
		session.getCurrentSession().save(instructor);
	}

	public void edit(Instructor instructor) {
		session.getCurrentSession().update(instructor);
	}

	public void delete(String instructorId) {
		session.getCurrentSession().delete(getInstructorById(instructorId));
	}

	@Override
	public Instructor getInstructorById(String instructorId) {
		return (Instructor) session.getCurrentSession().get(Instructor.class, instructorId);
	}
}

