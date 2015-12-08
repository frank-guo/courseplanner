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
import cmpt470.group7.project.model.Course;
import cmpt470.group7.project.model.Courseoption;

@SuppressWarnings("unused")
@Service
public class CourseService {
	@Autowired
	private CourseDaoImpl courseDao;

	
	@Transactional
	public void add(Course course) {
		courseDao.add(course);
	}

	@Transactional
	public void edit(Course course) {
		courseDao.edit(course);
	}

	@Transactional
	public void delete(String courseId) {
		courseDao.delete(courseId);
	}

	@Transactional
	public Course getCourse(String courseId) {
		return courseDao.getCourse(courseId);
	}

	
	@Transactional
	public List<Course> getAllCourse() {
		return courseDao.getAllCourse();
	}
	
	@Transactional
	public List<Courseoption> getCourseBySemester(int semester) {
		return courseDao.getCourseBySemester(semester);
	}
	
	@Transactional
	public List<Courseoption> getCourseBySemester(int semester, String courseId, String campusId) {
		return courseDao.getCourseBySemester(semester, courseId, campusId);
	}
	
	
}
