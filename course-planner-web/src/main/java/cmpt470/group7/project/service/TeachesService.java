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
import cmpt470.group7.project.dao.TeachesDaoImpl;
import cmpt470.group7.project.model.Course;
import cmpt470.group7.project.model.Courseoption;
import cmpt470.group7.project.model.Instructor;
import cmpt470.group7.project.model.Teaches;
import cmpt470.group7.project.model.TeachesId;

@SuppressWarnings("unused")
@Service
public class TeachesService {
	@Autowired
	private TeachesDaoImpl teachesDao;

	
	@Transactional
	public void add(Teaches teaches) {
		teachesDao.add(teaches);
	}

	@Transactional
	public void edit(Teaches teaches) {
		teachesDao.edit(teaches);
	}

	@Transactional
	public void delete(TeachesId teachesId) {
		teachesDao.delete(teachesId);
	}

	@Transactional
	public Teaches getTeachesById(TeachesId teachesId) {
		return teachesDao.getTeachesById(teachesId);
	}

	@Transactional
	public List<Teaches> getInstructorByCourseOption(String courseId,
			String sectionId, Integer semesterId) {
		return teachesDao.getInstructorByCourseOption(courseId,sectionId, semesterId);
	}
	
}
