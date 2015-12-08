package cmpt470.group7.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpt470.group7.project.dao.CourseDao;
import cmpt470.group7.project.dao.CourseDaoImpl;
import cmpt470.group7.project.dao.CourseoptionDao;
import cmpt470.group7.project.dao.CourseoptionDaoImpl;
import cmpt470.group7.project.dao.InstructorDaoImpl;
import cmpt470.group7.project.model.Course;
import cmpt470.group7.project.model.Courseoption;
import cmpt470.group7.project.model.Instructor;

@SuppressWarnings("unused")
@Service
public class InstructorService {
	@Autowired
	private InstructorDaoImpl instructorDao;

	
	@Transactional
	public void add(Instructor insructor) {
		instructorDao.add(insructor);
	}

	@Transactional
	public void edit(Instructor insructor) {
		instructorDao.edit(insructor);
	}

	@Transactional
	public void delete(String insructorId) {
		instructorDao.delete(insructorId);
	}

	@Transactional
	public Instructor getInsructorById(String insructorId) {
		return instructorDao.getInstructorById(insructorId);
	}
	
}
