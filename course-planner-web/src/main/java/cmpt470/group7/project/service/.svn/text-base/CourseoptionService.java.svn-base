package cmpt470.group7.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpt470.group7.project.dao.CourseDao;
import cmpt470.group7.project.dao.CourseoptionDao;
import cmpt470.group7.project.dao.CourseoptionDaoImpl;
import cmpt470.group7.project.model.Course;
import cmpt470.group7.project.model.Courseoption;
import cmpt470.group7.project.model.CourseoptionId;

@SuppressWarnings("unused")
@Service
public class CourseoptionService {
	@Autowired
	private CourseoptionDaoImpl courseoptionDao;
	
	@Transactional
	public void add(Courseoption co) {
		courseoptionDao.add(co);
	}

	@Transactional
	public void edit(Courseoption co) {
		courseoptionDao.edit(co);
	}

	@Transactional
	public void delete(Courseoption co) {
		courseoptionDao.delete(co);;
	}

	@Transactional
	public Courseoption getCourseoption(CourseoptionId coId) {
		return courseoptionDao.getCourseoption(coId);
	}

	
	@Transactional
	public List<Courseoption> getAllCourseoption() {
		return courseoptionDao.getAllCourseoption();
	}
	
	@Transactional
	public List<Courseoption> getCourseoptionBySemester(int semester) {
		return courseoptionDao.getCourseoptionBySemester(semester);
	}

	@Transactional
	public List<Courseoption> getCourseoptionBySemesterAndCoursId(int semester,
			String courseId) {
		return courseoptionDao.getCourseoptionBySemesterAndCourseId(semester, courseId);
	}
}
